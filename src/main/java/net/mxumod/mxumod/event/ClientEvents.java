package net.mxumod.mxumod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
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
            if (Keybinding.COMBAT_MODE.consumeClick()) {
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
                if (Keybinding.BASIC_ATTACK.isDown() && !Keybinding.BLOCKING.isDown()) {
                    ModMessages.sendToServer(new MxuTestC2SPacket());
                }
            }
        }
        @SubscribeEvent
        public static void onMouseRightClick(InputEvent.MouseButton event) {
            if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                ModMessages.sendToServer(new BlockingC2SPacket());
            }
        }
        @SubscribeEvent
        public static void onMouseMiddleClick(InputEvent.MouseButton event) {
            if (Keybinding.LOCK_ON.consumeClick() && EnterCombatmode.isCombatmode()) {
                CameraLock.cameraLockOn(Minecraft.getInstance().player);
            }
        }
        @SubscribeEvent
        public static void onShiftKey(InputEvent.Key event) {
            if (Keybinding.DODGE.consumeClick() && EnterCombatmode.isCombatmode()) {
                Dodge.dodge(Minecraft.getInstance().player, 0.1);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public  static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.COMBAT_MODE);
            event.register(Keybinding.DODGE);
            event.register(Keybinding.BASIC_ATTACK);
            event.register(Keybinding.BLOCKING);
            event.register(Keybinding.LOCK_ON);
        }
    }

}
