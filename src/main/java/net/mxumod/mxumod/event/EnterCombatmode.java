package net.mxumod.mxumod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EnterCombatmode {
    public static boolean isCombatmode() {
        return combatmode;
    }

    public static void setCombatmode(boolean combatmode) {
        EnterCombatmode.combatmode = combatmode;
    }

    private static boolean combatmode;

    @SubscribeEvent
    public  static void inCombatmode(PlayerInteractEvent.LeftClickBlock event) {
        Minecraft.getInstance().player.sendSystemMessage(Component.literal("interaction found"));
        if (combatmode) {
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("cancelling event"));
            event.setCanceled(true);
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("event canceled"));
        }
    }
}
