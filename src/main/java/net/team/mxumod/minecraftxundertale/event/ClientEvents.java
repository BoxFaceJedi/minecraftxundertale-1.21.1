package net.team.mxumod.minecraftxundertale.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.client.events.EnterCombatmode;
import net.team.mxumod.minecraftxundertale.networking.ModMessages;
import net.team.mxumod.minecraftxundertale.networking.packet.BoneSpikeC2SPacket;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.dodge.SideStepSkill;
import net.team.mxumod.minecraftxundertale.util.Keybinding;


public class ClientEvents {
    private static final long DODGE_COOLDOWN_MS = 500;
    private static long lastDodgeTime = 0;

    @Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT)
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

                    }
                }
            }
        }
    }
}
