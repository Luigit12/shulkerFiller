package com.lambda.modules

import baritone.api.pathing.goals.GoalXZ
import com.lambda.ShulkerFiller
import com.lambda.client.event.SafeClientEvent
import com.lambda.client.event.events.PacketEvent
import com.lambda.client.event.events.PlayerAttackEvent
import com.lambda.client.mixin.extension.syncCurrentPlayItem
import com.lambda.client.module.Category
import com.lambda.client.plugin.api.PluginModule
import com.lambda.client.util.BaritoneUtils
import com.lambda.client.util.EntityUtils
import com.lambda.client.util.Wrapper.player
import com.lambda.client.util.Wrapper.world
import com.lambda.client.util.combat.CombatUtils
import com.lambda.client.util.combat.CombatUtils.equipBestWeapon
import com.lambda.client.util.graphics.LambdaTessellator
import com.lambda.client.util.items.hotbarSlots
import com.lambda.client.util.items.swapToSlot
import com.lambda.client.util.text.MessageSendHelper
import com.lambda.client.util.threads.runSafe
import com.lambda.client.util.threads.safeListener
import net.minecraft.block.Block
import net.minecraft.block.state.IBlockState
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.EntityLivingBase
import net.minecraft.init.Enchantments
import net.minecraftforge.event.entity.player.PlayerInteractEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import org.lwjgl.input.Mouse
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityList
import net.minecraft.init.Items
import net.minecraft.world.World
import scala.swing.`UIElement$class`.location

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
        //safeListener<PacketEvent.Receive> {
            onEnable {
                MessageSendHelper.sendChatMessage("Enabled ShulkerFiller")
                //System.out.println(Block.getBlockFromName("minecraft:chest"))
                //System.out.println(world?.getLoadedEntityList())
                runSafe { startPathing() }

            }
            onDisable {
                BaritoneUtils.cancelEverything()
                MessageSendHelper.sendChatMessage("Disabled ShulkerFiller")
            }
        }

        private fun SafeClientEvent.startPathing() {
            if (player.let { it.let { it1 -> world.isChunkGeneratedAt(it1.chunkCoordX, player.chunkCoordZ) } }) return

            val entities = world.getLoadedEntityList()
            val gappleEntity = entities.filterIsInstance<EntityItem>().filter { it == Items.GOLDEN_APPLE }

            System.out.println(gappleEntity)

            //[EntityItem['item.tile.obsidian'/1001338, l='MpServer', x=189206.36, y=63.00, z=40704.19]

            BaritoneUtils.cancelEverything()
            //BaritoneUtils.primary?.customGoalProcess?.setGoalAndPath(GoalXZ(10, 10))
        }
//    }
}
