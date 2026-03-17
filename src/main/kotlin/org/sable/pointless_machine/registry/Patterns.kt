package org.sable.pointless_machine.registry

import at.petrak.hexcasting.api.casting.ActionRegistryEntry
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import at.petrak.hexcasting.common.lib.hex.HexActions
import net.minecraft.registry.Registry
import org.sable.pointless_machine.Pointless_machine
import org.sable.pointless_machine.casting.patterns.spells.SummonWither

object Patterns {
    @JvmStatic
    fun register() {
        import("summon_wither", "qdqqqqdqwaadwqaawddewdaa", HexDir.SOUTH_EAST, SummonWither(1))
    }
    @JvmStatic
    fun import(id : String, signature : String, startDir : HexDir, action : SpellAction){
        val proto = HexPattern.fromAngles(signature, startDir);
        Registry.register<ActionRegistryEntry>(
            HexActions.REGISTRY,
            Pointless_machine.MOD_ID + ":" + id,
            ActionRegistryEntry(proto,
            action)
        )
    }
}