package net.team.mxumod.minecraftxundertale.client.events;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.nbt.CompoundTag;
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
import net.team.mxumod.minecraftxundertale.util.CameraLock;
import net.team.mxumod.minecraftxundertale.util.Keybinding;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class SkillsEvent {
    static Minecraft minecraft = Minecraft.getInstance();
    static int freezeInputTick = 0;

    @SubscribeEvent
    public static void onClientTick(TickEvent.RenderTickEvent event) {
        if (minecraft.player == null) return;
        if (!EnterCombatmode.isCombatmode()) return;
        if (freezeInputTick > 0) {
            freezeInputTick--;

            minecraft.options.keyUp.setDown(false);
            minecraft.options.keyDown.setDown(false);
            minecraft.options.keyLeft.setDown(false);
            minecraft.options.keyRight.setDown(false);
            minecraft.options.keyJump.setDown(false);
            minecraft.options.keyAttack.setDown(false);
            minecraft.options.keyUse.setDown(false);
            return;
        }
        if (Keybinding.BASIC_ATTACK.isDown() && minecraft.player.getInventory().selected == 0) {
            ModMessages.sendToServer(new SkillsC2SPacket("Bone Barrage"));
        }
    }

    @SubscribeEvent
    public static void onMouseInput(InputEvent.MouseButton event) {
        if (minecraft.player == null) return;
        if (freezeInputTick > 0) return;
        if (minecraft.player.getInventory().selected == 0) {
            if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                ModMessages.sendToServer(new SkillsC2SPacket("Bone Wall"));
            }
        }else if (minecraft.player.getInventory().selected == 2) {
            if (Keybinding.BASIC_ATTACK.consumeClick()) {
                if (CameraLock.getTarget() == null) return;
                CompoundTag data = new CompoundTag();
                data.putInt("TargetId", CameraLock.getTarget().getId());
                if (minecraft.options.keyDown.isDown()) {
                    data.putString("Key", "S");
                    freezeInputTick += 60;
                } else if (minecraft.options.keyLeft.isDown())
                    data.putString("Key", "A");
                else if (minecraft.options.keyRight.isDown())
                    data.putString("Key", "D");
                else if (minecraft.options.keyUp.isDown())
                    data.putString("Key", "W");
                else
                    data.putString("Key", "W");
                ModMessages.sendToServer(new SkillsC2SPacket("Telekinesis", data));
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
        if (freezeInputTick > 0) return;
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
            } else if (minecraft.player.getInventory().selected == 2) {
                if (Keybinding.SPECIAL_ATTACK.consumeClick()) {
                    player.moveTo(player.position().add(player.getDeltaMovement().normalize().multiply(15, 0, 15)));
                }
            }
        }
    }
}
