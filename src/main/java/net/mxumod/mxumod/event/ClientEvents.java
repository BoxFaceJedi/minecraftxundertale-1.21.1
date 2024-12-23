package net.mxumod.mxumod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.networking.ModMessages;
import net.mxumod.mxumod.networking.packet.BlockingC2SPacket;
import net.mxumod.mxumod.networking.packet.MxuTestC2SPacket;
import net.mxumod.mxumod.skill.CameraLock;
import net.mxumod.mxumod.skill.Dodge;
import net.mxumod.mxumod.util.Keybinding;



public class ClientEvents {
    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public  static void onKeyInput(InputEvent.Key event) {
            if (Keybinding.COMBATMODE_KEY.consumeClick()) {
                if (!EnterCombatmode.isCombatmode()) {
                    EnterCombatmode.setCombatmode(true);
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("entering combatmode"));
                }else {
                    EnterCombatmode.setCombatmode(false);
                    Minecraft.getInstance().player.sendSystemMessage(Component.literal("leaving combatmode"));
                }
            }
        }
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END && EnterCombatmode.isCombatmode()) {
                if (Keybinding.LEFTCLICK_KEY.isDown() && !Keybinding.RIGHTCLICK_KEY.isDown()) {
                    ModMessages.sendToServer(new MxuTestC2SPacket());
                }
            }
        }
        @SubscribeEvent
        public static void onMouseRightClick(InputEvent.MouseButton event) {
            if (Keybinding.RIGHTCLICK_KEY.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.LEFTCLICK_KEY.isDown()) {
                ModMessages.sendToServer(new BlockingC2SPacket());
            }
        }
        @SubscribeEvent
        public static void onMouseMiddleClick(InputEvent.MouseButton event) {
            if (Keybinding.MIDDLECLICK_KEY.consumeClick() && EnterCombatmode.isCombatmode()) {
                CameraLock.cameraLockOn(Minecraft.getInstance().player);
            }
        }
        @SubscribeEvent
        public static void onShiftKey(InputEvent.Key event) {
            if (Minecraft.getInstance().options.keyShift.consumeClick() && EnterCombatmode.isCombatmode()) {
                Dodge.dodge(Minecraft.getInstance().player);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public  static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.COMBATMODE_KEY);
            event.register(Keybinding.LEFTCLICK_KEY);
            event.register(Keybinding.RIGHTCLICK_KEY);
            event.register(Keybinding.MIDDLECLICK_KEY);
        }
    }

}
