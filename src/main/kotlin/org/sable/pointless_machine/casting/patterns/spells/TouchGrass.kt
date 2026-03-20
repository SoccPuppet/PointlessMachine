package org.sable.pointless_machine.casting.patterns.spells

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getPlayer
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.misc.MediaConstants
import net.minecraft.block.Blocks
import net.minecraft.server.network.ServerPlayerEntity

object TouchGrass : SpellAction{
    override val argc = 1
    override fun execute(args : List<Iota>, env : CastingEnvironment) : SpellAction.Result {
        val target = args.getPlayer(0, argc)
        env.assertEntityInRange(target)
        return SpellAction.Result(
            Spell(target),
            100 * MediaConstants.DUST_UNIT,
            listOf(ParticleSpray.cloud(target.pos, 3.0))
        )
    }

    private data class Spell(val target : ServerPlayerEntity) : RenderedSpell{
        override fun cast(env: CastingEnvironment) {
            env.world.setBlockState(target.blockPos, Blocks.GRASS_BLOCK.defaultState)
        }
    }
}
