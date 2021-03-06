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
package io.guthix.oldscape.server.world.entity.character.player.interest

import io.guthix.oldscape.server.Cache
import io.guthix.oldscape.server.world.mapsquare.MapSquareUnit
import io.guthix.oldscape.server.world.mapsquare.zone.Zone
import io.guthix.oldscape.server.world.mapsquare.zone.ZoneUnit
import io.guthix.oldscape.server.world.mapsquare.zone.zones

class MapInterest {

    companion object {
        val SIZE = 13.zones

        val RANGE = SIZE / 2.zones

        val UPDATE_RANGE = RANGE - PlayerInterest.RANGE.inZones

        private val ZoneUnit.startMapInterest get() = (this - RANGE).inMapSquares

        private val ZoneUnit.endMapInterest get() = (this + RANGE).inMapSquares

        fun getInterestedXteas(zone: Zone): List<IntArray> {
            val xteas = mutableListOf<IntArray>()
            for(mSquareX in zone.x.startMapInterest..zone.x.endMapInterest) {
                for(mSquareY in zone.y.startMapInterest..zone.y.endMapInterest) {
                    if(onTutorialIsland(mSquareX, mSquareY)) continue
                    val id = (mSquareX.value shl 8) or mSquareY.value
                    val xtea = Cache.mapSquareXteas[id] ?: throw IllegalStateException(
                        "Could not find XTEA for id $id."
                    )
                    xteas.add(xtea)
                }
            }
            return xteas
        }

        private fun onTutorialIsland(mSquareX: MapSquareUnit, mSquareY: MapSquareUnit) =
            ((mSquareX.value == 48 || mSquareX.value == 49) && mSquareY.value == 48)
                || (mSquareX.value == 48 && mSquareX.value == 148)
    }
}



