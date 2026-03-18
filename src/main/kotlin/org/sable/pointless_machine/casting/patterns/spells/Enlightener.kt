package org.sable.pointless_machine.casting.patterns.spells

import at.petrak.hexcasting.api.casting.ParticleSpray
import at.petrak.hexcasting.api.casting.RenderedSpell
import at.petrak.hexcasting.api.casting.castables.SpellAction
import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import at.petrak.hexcasting.api.casting.getEntity
import at.petrak.hexcasting.api.casting.iota.Iota
import at.petrak.hexcasting.api.casting.mishaps.MishapBadEntity
import at.petrak.hexcasting.api.misc.MediaConstants
import net.minecraft.command.argument.EntityAnchorArgumentType
import net.minecraft.entity.decoration.ArmorStandEntity
import net.minecraft.text.Text

object Enlightener : SpellAction{
    override val argc = 1
    override fun execute(args : List<Iota>, env : CastingEnvironment) : SpellAction.Result {
        val target = args.getEntity(0, argc)
        env.assertEntityInRange(target)
        if(target !is ArmorStandEntity){
            throw MishapBadEntity(target, Text.of("an armor stand"))
        }
        return SpellAction.Result(
            Spell(target),
            5 * MediaConstants.DUST_UNIT,
            listOf(ParticleSpray.cloud(target.pos, 3.0))
        )
    }

    private data class Spell(val target : ArmorStandEntity) : RenderedSpell{
        override fun cast(env: CastingEnvironment) {
            val caster = env.castingEntity
            if(caster !== null){
                target.lookAt(EntityAnchorArgumentType.EntityAnchor.EYES, caster.eyePos)
            }
        }
    }
}
