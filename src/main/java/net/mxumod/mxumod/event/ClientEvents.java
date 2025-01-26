package net.mxumod.mxumod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.networking.ModMessages;
import net.mxumod.mxumod.networking.packet.BlockingC2SPacket;
import net.mxumod.mxumod.networking.packet.BoneSpikeC2SPacket;
import net.mxumod.mxumod.networking.packet.MxuTestC2SPacket;
import net.mxumod.mxumod.skill.CameraLock;
import net.mxumod.mxumod.skill.PlayerSkillManager;
import net.mxumod.mxumod.skill.dodge.SideStepSkill;
import net.mxumod.mxumod.util.Keybinding;



public class ClientEvents {

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        private static final Minecraft minecraft = Minecraft.getInstance();
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (minecraft.player != null) {
                if (Keybinding.COMBAT_MODE.consumeClick()) {
                    if (!EnterCombatmode.isCombatmode()) {
                        EnterCombatmode.enterCombatmode();
                    }else {
                        EnterCombatmode.leaveCombatmode();
                    }
                }
                if (Keybinding.DODGE.consumeClick() && EnterCombatmode.isCombatmode()) {
                    if (minecraft.player.getInventory().selected == 0 && minecraft.player.onGround()) {
                       new PlayerSkillManager().activateSkill(new SideStepSkill().getName(), minecraft.player);
                    }
                }
                if (Keybinding.SPECIAL_ATTACK.consumeClick() && EnterCombatmode.isCombatmode()) {
                    if (minecraft.player.getInventory().selected == 0) {
                        ModMessages.sendToServer((new BoneSpikeC2SPacket()));
                    }
                }
                if (Keybinding.ULTIMATE_ATTACK.consumeClick() && EnterCombatmode.isCombatmode()) {
                    minecraft.player.sendSystemMessage(Component.literal("ult attack"));
                }
            }
        }
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (minecraft.player != null) {
                if (event.phase == TickEvent.Phase.END && EnterCombatmode.isCombatmode()) {
                    if (minecraft.player.getInventory().selected == 0) {
                        if (Keybinding.BASIC_ATTACK.isDown() && !Keybinding.BLOCKING.isDown()) {
                            ModMessages.sendToServer(new MxuTestC2SPacket());
                        }
                    }
                    minecraft.player.displayClientMessage(Component.literal(String.valueOf(PlayerSkillManager.getCurrentMana())), true);
                }
            }
        }
        @SubscribeEvent
        public static void onMouseInput(InputEvent.MouseButton event) {
            if (minecraft.player != null) {
                if (minecraft.player.getInventory().selected == 0) {
                    if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                        ModMessages.sendToServer(new BlockingC2SPacket());
                    }
                }
                if (Keybinding.LOCK_ON.consumeClick() && EnterCombatmode.isCombatmode()) {
                    CameraLock.toggleCameraLock(minecraft.player);
                }
            }
        }
        @SubscribeEvent
        public static void onPlayerDeath(LivingDeathEvent event) {
            if (event.getEntity() instanceof Player) {
                EnterCombatmode.leaveCombatmode();
            }
        }
    }

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT,bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public  static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(Keybinding.COMBAT_MODE);
            event.register(Keybinding.DODGE);
            event.register(Keybinding.SPECIAL_ATTACK);
            event.register(Keybinding.ULTIMATE_ATTACK);
            event.register(Keybinding.BASIC_ATTACK);
            event.register(Keybinding.BLOCKING);
            event.register(Keybinding.LOCK_ON);
        }
    }

}
