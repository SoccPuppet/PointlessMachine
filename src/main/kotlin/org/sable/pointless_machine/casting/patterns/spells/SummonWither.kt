package org.sable.pointless_machine.casting.patterns.spells

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getBlockPos
import at.petrak.hexcasting.api.casting.iota.Iota
import net.minecraft.entity.EntityType
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d

class SummonWither(val number : Int) : SpellAction{
    override val argc = 1
    override fun execute(args : List<Iota>, env : CastingEnvironment) : SpellAction.Result {
        val target = args.getBlockPos(0, argc)
        env.assertPosInRange(target)
        return SpellAction.Result(
            Spell(target, number),
            0,
            listOf(ParticleSpray.cloud(Vec3d.ofCenter(target), 3.0))
        )
    }

    private data class Spell(val target : BlockPos, val number : Int) : RenderedSpell{
        override fun cast(env: CastingEnvironment) {
            for(i in 1..number) {
                val wither = EntityType.WITHER.create(env.world)
                if (wither !== null) {
                    wither.setInvulTimer(220)
                    wither.setPosition(target.toCenterPos())
                    env.world.spawnEntity(wither)
                }
            }
        }
    }
}