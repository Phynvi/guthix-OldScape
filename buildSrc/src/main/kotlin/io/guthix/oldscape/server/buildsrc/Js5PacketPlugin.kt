package io.guthix.oldscape.server.buildsrc

import io.guthix.cache.js5.Js5ArchiveSettings
import io.guthix.cache.js5.Js5ArchiveValidator
import io.guthix.cache.js5.Js5CacheValidator
import io.guthix.cache.js5.container.Js5Container
import io.guthix.cache.js5.container.disk.Js5DiskStore
import io.guthix.cache.js5.util.crc
import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.language.jvm.tasks.ProcessResources
import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.ceil

@Suppress("UnstableApiUsage")
class Js5PacketPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        val processResourceTask = target.getTasksByName("processResources", false).first()
        if(processResourceTask is ProcessResources) {
            processResourceTask.exclude("/cache/*")
        } else {
            throw IllegalStateException("Could not find processResources task in gradle project ${target.name}.")
        }
        val compileCache = target.task("compileCache") {
            val cacheDir = Path.of("${target.projectDir}\\src\\main\\resources\\cache")
            val buildDir = Path.of("${target.buildDir.path}\\resources\\main\\cache")
            it.doLast {
                val packets = readPackets(Js5DiskStore.open(cacheDir))
                packets.forEach {(archiveId, groupPackets) ->
                    val archivePath = buildDir.resolve(archiveId.toString())
                    Files.createDirectory(archivePath)
                    groupPackets.forEach { (groupId, packetData) ->
                        val groupPath = archivePath.resolve(groupId.toString())
                        Files.createFile(groupPath)
                        Files.write(groupPath, packetData.array())
                    }
                }
            }
        }
//        val build = target.getTasksByName("build", false).first()
//        build.actions.addAll(compileCache.actions)
    }

    fun readPackets(diskStore: Js5DiskStore): Map<Int, Map<Int, ByteBuf>> {
        val containers = mutableMapOf<Int, MutableMap<Int, ByteBuf>>()
        val archiveSettingsContainers = containers.getOrPut(Js5DiskStore.MASTER_INDEX) {
            mutableMapOf()
        }
        val archiveSettingsData = mutableMapOf<Int, ByteBuf>()
        val archiveSettingsMap = mutableMapOf<Int, Js5ArchiveSettings>()
        for(archiveId in 0 until diskStore.archiveCount) {
            val archiveContainers = containers.getOrPut(archiveId) { mutableMapOf() }
            val aSettingsData = diskStore.read(diskStore.masterIdxFile, archiveId)
            archiveSettingsData[archiveId] = aSettingsData
            archiveSettingsContainers[archiveId] = createPacket(Js5DiskStore.MASTER_INDEX, archiveId, aSettingsData)
            aSettingsData.readerIndex(0)
            val archiveSettings = Js5ArchiveSettings.decode(Js5Container.decode(aSettingsData))
            aSettingsData.readerIndex(0)
            archiveSettingsMap[archiveId] = archiveSettings
            val archiveIdxFile = diskStore.openArchiveIdxFile(archiveId)
            archiveSettings.groupSettings.forEach { (groupId,  _) ->
                val data = diskStore.read(archiveIdxFile, groupId)
                archiveContainers[groupId] = createPacket(archiveId, groupId, data)
            }
        }
        val archiveValidators = mutableListOf<Js5ArchiveValidator>()
        for((archiveId, archiveSettings) in archiveSettingsMap) {
            val data = archiveSettingsData[archiveId] ?: throw IllegalStateException(
                "Could not find archive data for archive $archiveId."
            )
            val heapData = Unpooled.copiedBuffer(data)
            archiveValidators.add(
                Js5ArchiveValidator(
                    heapData.crc(), archiveSettings.version ?: 0, null, null, null
                )
            )
        }
        val validatorData = Js5CacheValidator(archiveValidators.toTypedArray()).encode()
        archiveSettingsContainers[Js5DiskStore.MASTER_INDEX] = createPacket(
            Js5DiskStore.MASTER_INDEX, Js5DiskStore.MASTER_INDEX, Js5Container(data = validatorData).encode()
        )
        return containers
    }

    private fun createPacket(indexFileId: Int, containerId: Int, data: ByteBuf): ByteBuf {
        val dataSize = data.writerIndex()
        val packetBuf = Unpooled.buffer(HEADER_SIZE +
                dataSize + ceil((dataSize - BYTES_AFTER_HEADER) / BYTES_AFTER_BLOCK.toDouble()).toInt()
        )
        packetBuf.writeByte(indexFileId)
        packetBuf.writeShort(containerId)
        val firstSectorSize = if(dataSize > BYTES_AFTER_HEADER) BYTES_AFTER_HEADER else dataSize
        packetBuf.writeBytes(data, firstSectorSize)
        while(data.isReadable) {
            val dataToRead = data.readableBytes()
            val sectorSize = if(dataToRead > BYTES_AFTER_BLOCK) BYTES_AFTER_BLOCK else dataToRead
            packetBuf.writeByte(BLOCK_HEADER)
            packetBuf.writeBytes(data, sectorSize)
        }
        return packetBuf
    }

    companion object {
        private const val SECTOR_SIZE = 512
        private const val HEADER_SIZE = Byte.SIZE_BYTES + Short.SIZE_BYTES
        private const val BYTES_AFTER_HEADER = SECTOR_SIZE - HEADER_SIZE
        private const val BYTES_AFTER_BLOCK = SECTOR_SIZE - 1
        private const val BLOCK_HEADER = 255
    }
}
