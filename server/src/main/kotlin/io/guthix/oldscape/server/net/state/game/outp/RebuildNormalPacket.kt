/**
 * This file is part of Guthix OldScape.
 *
 * Guthix OldScape is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Guthix OldScape is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */
package io.guthix.oldscape.server.net.state.game.outp

import io.guthix.cache.js5.util.XTEA_KEY_SIZE
import io.guthix.oldscape.server.net.state.game.OutGameEvent
import io.guthix.oldscape.server.net.state.game.VarShortSize
import io.guthix.oldscape.server.world.mapsquare.zone.Zone
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext

class RebuildNormalPacket(private val xteas: List<IntArray>, private val zone: Zone) : OutGameEvent {
    override val opcode = 73

    override val size = VarShortSize

    override fun encode(ctx: ChannelHandlerContext): ByteBuf {
        val buf = ctx.alloc().buffer(STATIC_SIZE + xteas.size * XTEA_KEY_SIZE * Int.SIZE_BYTES)
        buf.writeShortLE(zone.y.value)
        buf.writeShort(zone.x.value)
        buf.writeShort(xteas.size)
        xteas.forEach { xteaKey ->
            xteaKey.forEach { keyPart ->
                buf.writeInt(keyPart)
            }
        }
        return buf
    }

    companion object {
        const val STATIC_SIZE = Short.SIZE_BYTES + Short.SIZE_BYTES + Short.SIZE_BYTES
    }
}