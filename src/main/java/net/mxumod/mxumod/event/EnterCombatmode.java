package net.mxumod.mxumod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
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
    public  static void onLeftClickBlock (PlayerInteractEvent.LeftClickBlock event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public  static void onRightClickBlock (PlayerInteractEvent.RightClickBlock event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public  static void onAttackEntity (AttackEntityEvent event) {
        event.setCanceled(combatmode);
    }
    @SubscribeEvent
    public  static void onRightClickEntityInteract (PlayerInteractEvent.EntityInteract event) {
        event.setCanceled(combatmode);
    }
}
