package org.sable.pointless_machine

import net.fabricmc.api.ModInitializer
import org.sable.pointless_machine.registry.Patterns.register
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class Pointless_machine : ModInitializer {

    override fun onInitialize() {
        LOGGER.info("(In a knife shop) I don't see the point")

        register()
    }

    companion object {
        const val MOD_ID: String = "pointless_machine"
        val LOGGER : Logger = LoggerFactory.getLogger(MOD_ID)
    }
}
