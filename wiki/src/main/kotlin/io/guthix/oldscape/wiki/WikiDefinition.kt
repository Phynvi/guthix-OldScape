/**
 * This file is part of Guthix OldScape.
 *
 * Guthix OldScape is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Guthix OldScape is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Foobar. If not, see <https://www.gnu.org/licenses/>.
 */
package io.guthix.oldscape.wiki

import mu.KotlinLogging
import java.lang.Exception
import java.time.LocalDate
import java.time.Month
import kotlin.reflect.full.createInstance

private val logger = KotlinLogging.logger {}

abstract class WikiDefinition<P : WikiDefinition<P>> {
    open var ids: List<Int>? = null
    open var name: String? = null

    @Suppress("UNCHECKED_CAST")
    open fun parse(page: String, version: Int? = null): P {
        page.reader().readLines().forEach { pageLine ->
            try {
                if(pageLine.startsWith("|")) {
                    parseKeyValueLine(pageLine, version)
                }
            } catch (e: Exception) {
                logger.warn("Could not parse line: $pageLine")
            }
        }
        return this as P
    }

    abstract fun parseKeyValueLine(line: String, version: Int?)

    protected fun String.checkWikiKey(string: String, version: Int?): Boolean {
        val pageCheck = substringBefore("=").replace(" ", "").substring(1)
        val stringCheck = string.replace(" ", "")
        return if(version == null) {
            stringCheck.equals(pageCheck, true)
        } else {
            "$stringCheck$version".equals(pageCheck, true)
        }
    }

    protected fun String.getWikiString(): String? {
        val str = substringAfter("=").removePrefix(" ")
        return if(str.equals("", ignoreCase = true) || str.equals("N/A", ignoreCase = true)) null else str
    }

    protected fun String.getWikiStrings() = getWikiString()
        ?.split(",")?.map { it.replace(' ', ',') }

    protected fun String.getWikiBool() = getWikiString()
        ?.replace(" ", "")
        ?.equals("Yes", ignoreCase = true)

    protected fun String.getWikiInt() = getWikiString()
        ?.replace(",","")
        ?.replace("\\D", "")
        ?.replace(" ", "")
        ?.toInt()

    protected fun String.getWikiDouble() = getWikiString()
        ?.toDouble()

    protected fun String.getWikiNoString(): String? {
        if(contains("No", ignoreCase = true)) return null
        return getWikiString()
    }

    protected fun String.getWikiNoInt() = getWikiNoString()
        ?.toInt()

    protected fun String.getWikiNoDouble() = getWikiNoString()
        ?.toDouble()

    protected fun String.getWikiDate(): LocalDate {
        val splits = split(" ").subList(2, 5)
        return LocalDate.of(
            splits[2].replace("[", "").replace("]", "").toInt(),
            Month.valueOf(splits[1].replace("]", "").toUpperCase()),
            splits[0].replace("[", "").toInt()
        )
    }

    protected fun String.getIds(): List<Int>? = if(getWikiString().equals("Removed", ignoreCase = true)) {
        null
    } else {
        getWikiStrings()?.map { it.toInt() }
    }
}

inline fun <reified P : WikiDefinition<P>> parseWikiString(wikiString: String): List<P> {
    val definitions = mutableListOf<P>()
    if(wikiString.contains("|id1 = ")) {
        var version = 1
        do {
            val def = P::class.createInstance().parse(wikiString, version)
            definitions.add(def)
            version++
        } while(wikiString.contains("|id$version = "))
    } else {
        val def = P::class.createInstance().parse(wikiString, null)
        definitions.add(def)
    }
    return definitions
}

abstract class WikiConfigCompanion {
    abstract val queryString: String
}