package org.sable.pointless_machine.casting.patterns

import at.petrak.hexcasting.api.casting.castables.ConstMediaAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.iota.PatternIota
import at.petrak.hexcasting.api.casting.math.HexDir
import at.petrak.hexcasting.api.casting.math.HexPattern
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents

object GetSelf : ConstMediaAction {
    override val argc = 0

    override fun execute(args: List<Iota>, env: CastingEnvironment): List<Iota> {
        val caster = env.castingEntity
        if(caster !== null && !env.world.isClient){
            env.world.playSound(null, caster.blockPos,
                SoundEvents.ENTITY_BEE_POLLINATE,
                SoundCategory.NEUTRAL,
                5f, 1.0f)
        }
        return listOf(PatternIota(HexPattern.fromAngles("wqqwqqdaqqqa", HexDir.EAST)))
    }
}