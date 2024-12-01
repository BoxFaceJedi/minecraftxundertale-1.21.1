package net.mxumod.mxumod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.util.Keybinding;


public class ClientEvents {
    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {


        @SubscribeEvent
        public  static void onKeyInput(InputEvent.Key event) {
            if (Keybinding.COMBATMODE_KEY.consumeClick()) {
                Minecraft.getInstance().player.sendSystemMessage(Component.literal("enters combat mode"));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public  static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.COMBATMODE_KEY);
        }
    }

}
