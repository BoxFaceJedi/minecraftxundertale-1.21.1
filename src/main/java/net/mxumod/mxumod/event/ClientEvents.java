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
import net.mxumod.mxumod.networking.packet.BoneSpikeC2SPacket;
import net.mxumod.mxumod.networking.packet.BoneWallC2SPacket;
import net.mxumod.mxumod.networking.packet.BoneBarrageC2SPacket;
import net.mxumod.mxumod.util.CameraLock;
import net.mxumod.mxumod.skill.PlayerSkillManager;
import net.mxumod.mxumod.skill.dodge.SideStepSkill;
import net.mxumod.mxumod.util.Keybinding;



public class ClientEvents {
    private static final long DODGE_COOLDOWN_MS = 500;
    private static long lastDodgeTime = 0;

    @Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        private static final Minecraft minecraft = Minecraft.getInstance();

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if (minecraft.player != null) {
                long currentTime = System.currentTimeMillis();
                if (Keybinding.COMBAT_MODE.consumeClick()) {
                    if (!EnterCombatmode.isCombatmode()) {
                        EnterCombatmode.enterCombatmode();
                    }else {
                        EnterCombatmode.leaveCombatmode();
                    }
                } else if (EnterCombatmode.isCombatmode()) {
                    if (Keybinding.DODGE.consumeClick() && !(currentTime - lastDodgeTime < DODGE_COOLDOWN_MS)) {
                        if (minecraft.player.getInventory().selected == 0 && minecraft.player.onGround()) {
                            new PlayerSkillManager().activateSkill(new SideStepSkill().getName(), minecraft.player);
                        }
                        lastDodgeTime = currentTime;
                    }else if (Keybinding.SPECIAL_ATTACK.consumeClick()) {
                        if (minecraft.player.getInventory().selected == 0) {
                            ModMessages.sendToServer((new BoneSpikeC2SPacket()));
                        }
                    }else if (Keybinding.ULTIMATE_ATTACK.consumeClick()) {
                        minecraft.player.sendSystemMessage(Component.literal("ult attack"));
                    }
                }else {
                    if (Keybinding.SETTINGS.consumeClick()) {

                    }
                }
            }
        }
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (minecraft.player != null) {
                if (event.phase == TickEvent.Phase.END && EnterCombatmode.isCombatmode()) {
                    if (minecraft.player.getInventory().selected == 0) {
                        if (Keybinding.BASIC_ATTACK.isDown() && !Keybinding.BLOCKING.isDown()) {
                            ModMessages.sendToServer(new BoneBarrageC2SPacket());
                        }
                    }
                }
            }
        }
        @SubscribeEvent
        public static void onMouseInput(InputEvent.MouseButton event) throws InterruptedException {
            if (minecraft.player != null) {
                if (minecraft.player.getInventory().selected == 0) {
                    if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                        ModMessages.sendToServer(new BoneWallC2SPacket());
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
            event.register(Keybinding.SETTINGS);
        }
    }

}
