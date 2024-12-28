package net.mxumod.mxumod.event;

import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.player.Input;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class EnterCombatmode {
    public static boolean isCombatmode() {
        return combatmode;
    }

    public static void setCombatmode(boolean combatmode) {
        EnterCombatmode.combatmode = combatmode;
    }

    private static boolean combatmode;

    @SubscribeEvent
    public static void onLeftClickBlock (PlayerInteractEvent.LeftClickBlock event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public static void onRightClickBlock (PlayerInteractEvent.RightClickBlock event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public static void onAttackEntity (AttackEntityEvent event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public static void onRightClickEntityInteract (PlayerInteractEvent.EntityInteract event) {event.setCanceled(combatmode);}
    @SubscribeEvent
    public static void onOpenInventory (ScreenEvent event) {
        if (event.getScreen() instanceof InventoryScreen && combatmode) {
            event.setCanceled(true);
        }
    }
    @SubscribeEvent
    public static void disabledDropItem(ItemTossEvent event) {
        if (combatmode) {
            event.setCanceled(true);
            event.getPlayer().addItem(event.getEntity().getItem());
        }
    }

}
