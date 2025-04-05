package net.team.mxumod.minecraftxundertale.client.events;

import net.minecraft.client.Minecraft;
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
        if (minecraft.player.getInventory().selected == 0 || minecraft.player.getInventory().selected == 1) {
            if (Keybinding.BLOCKING.isDown() && EnterCombatmode.isCombatmode() && !Keybinding.BASIC_ATTACK.isDown()) {
                ModMessages.sendToServer(new SkillsC2SPacket("Bone Wall"));
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
}
