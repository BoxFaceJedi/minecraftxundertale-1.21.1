package net.team.mxumod.minecraftxundertale.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.networking.ModMessages;
import net.team.mxumod.minecraftxundertale.networking.packet.SkillsC2SPacket;
import net.team.mxumod.minecraftxundertale.skill.basic.Telekinesis.TelekinesisData;
import net.team.mxumod.minecraftxundertale.util.CameraLock;
import net.team.mxumod.minecraftxundertale.util.Keybinding;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillsEvent {
    static Minecraft minecraft = Minecraft.getInstance();

    @SubscribeEvent
    public static void onClientTick(TickEvent.RenderTickEvent event) {
        if (Minecraft.getInstance().player == null) return;
        if (!EnterCombatmode.isCombatmode()) return;
        if (Keybinding.BASIC_ATTACK.isDown() && minecraft.player.getInventory().selected == 0) {
            ModMessages.sendToServer(new SkillsC2SPacket("Bone Barrage"));
        }
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton event) {
        if (minecraft.player == null) return;
        if (minecraft.player.getInventory().selected == 0) {
            if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                ModMessages.sendToServer(new SkillsC2SPacket("Bone Wall"));
            }
        }else if (minecraft.player.getInventory().selected == 2) {
            if (Keybinding.BASIC_ATTACK.consumeClick()) {
                if (minecraft.options.keyDown.isDown()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", new TelekinesisData(CameraLock.getTarget(), "S")));
                }else if (minecraft.options.keyLeft.isDown()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", new TelekinesisData(CameraLock.getTarget(), "A")));
                }else if (minecraft.options.keyRight.isDown()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", new TelekinesisData(CameraLock.getTarget(), "D")));
                }else if (minecraft.options.keyUp.isDown()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", new TelekinesisData(CameraLock.getTarget(), "W")));
                }
            }
        }
        if (Keybinding.LOCK_ON.consumeClick() && EnterCombatmode.isCombatmode()) {
            CameraLock.toggleCameraLock(minecraft.player);
        }
    }

    @SubscribeEvent
    public static void onPlayerDeath(LivingDeathEvent event) {
        if (event.getEntity() instanceof Player) {
            EnterCombatmode.leaveCombatmode();
        }
    }

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (minecraft.player == null) return;
        LocalPlayer player = minecraft.player;
        if (Keybinding.COMBAT_MODE.consumeClick()) {
            if (EnterCombatmode.isCombatmode()) {
                EnterCombatmode.leaveCombatmode();
            }else {
                EnterCombatmode.enterCombatmode();
            }
        } else if (EnterCombatmode.isCombatmode()) {
            if (Keybinding.DODGE.consumeClick() && minecraft.player.onGround()) {
                ModMessages.sendToServer(new SkillsC2SPacket("Side Step"));
                player.setDeltaMovement(player.getDeltaMovement().normalize().multiply(1.5, 0.0, 1.5));
            } else if (minecraft.player.getInventory().selected == 0) {
                if (Keybinding.SPECIAL_ATTACK.consumeClick()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Bone Spike"));
                }
            }else if (minecraft.player.getInventory().selected == 2) {
                if (Keybinding.SPECIAL_ATTACK.consumeClick()) {
                    ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", CameraLock.getTarget()));
                }
            }
        }
    }
}
