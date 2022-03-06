package com.lambda.modules

import baritone.api.pathing.goals.GoalXZ
import com.lambda.ShulkerFiller
import com.lambda.client.event.SafeClientEvent
import com.lambda.client.event.events.PlayerAttackEvent
import com.lambda.client.mixin.extension.syncCurrentPlayItem
import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.BaritoneUtils
import com.lambda.client.util.Wrapper.player
import com.lambda.client.util.Wrapper.world
import com.lambda.client.util.combat.CombatUtils
import com.lambda.client.util.combat.CombatUtils.equipBestWeapon
import com.lambda.client.util.items.hotbarSlots
import com.lambda.client.util.items.swapToSlot
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.util.threads.safeListener
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.Enchantments
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Mouse

/**
 * This is a module. First set properties then settings then add listener.
 * **/
internal object ShulkerFillerModule : PluginModule(
    name = "ShulkerFiller",
    category = Category.MISC,
    description = "A module which automatically fills your shulkers",
    pluginMain = ShulkerFiller
) {

    init {
        onEnable {
            startPathing()
            MessageSendHelper.sendChatMessage("Enabled ShulkerFiller")
        }
        onDisable {
            BaritoneUtils.cancelEverything()
            MessageSendHelper.sendChatMessage("Disabled ShulkerFiller")
        }
    }

    private fun startPathing() {
        if (!player?.let { world?.isChunkGeneratedAt(it.chunkCoordX, player!!.chunkCoordZ) }!!) return

        BaritoneUtils.cancelEverything()
        BaritoneUtils.primary?.customGoalProcess?.setGoalAndPath(GoalXZ(189209, 40719))
    }
}