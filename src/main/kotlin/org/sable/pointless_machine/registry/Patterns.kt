package org.sable.pointless_machine.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.Action
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.minecraft.registry.Registry
import org.sable.pointless_machine.Pointless_machine
import org.sable.pointless_machine.casting.patterns.GetOffline
import org.sable.pointless_machine.casting.patterns.spells.SummonWither
import org.sable.pointless_machine.casting.patterns.spells.TouchGrass
import org.sable.pointless_machine.casting.patterns.GetOrigin
import org.sable.pointless_machine.casting.patterns.GetSelf
import org.sable.pointless_machine.casting.patterns.spells.Enlightener

object Patterns {
    @JvmStatic
    fun register() {
        import("summon_wither", "qdqqqqdqwaadwqaawddewdaa", HexDir.SOUTH_EAST, SummonWither(1))
        import("touch_grass", "wwqqadadadaq", HexDir.EAST, TouchGrass)
        import("get_origin", "aqadaqa", HexDir.NORTH_WEST, GetOrigin)
        import("get_self", "wqqwqqdaqqqa", HexDir.EAST, GetSelf)
        import("get_offline", "eqqqwqqw", HexDir.NORTH_EAST, GetOffline)
        import("enlightener", "adaaedqaqeedeeeaa", HexDir.SOUTH_EAST, Enlightener)
    }
    @JvmStatic
    fun import(id : String, signature : String, startDir : HexDir, action : Action){
        val proto = HexPattern.fromAngles(signature, startDir)
        Registry.register<ActionRegistryEntry>(
            HexActions.REGISTRY,
            Pointless_machine.MOD_ID + ":" + id,
            ActionRegistryEntry(proto,
            action)
        )
    }
}