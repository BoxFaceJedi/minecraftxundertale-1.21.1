package net.team.mxumod.minecraftxundertale.skill.dodge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.DEDICATED_SERVER)
public class SideStepSkill extends Skill {
    public SideStepSkill() {
        super("Side Step", 0, 500);
    }

    private static final Map<ServerPlayer, Integer> iFrameTicks = new HashMap<>();
    private static final int I_FRAME_TICKS = 5;

    @SubscribeEvent
    public static void onPlayerHurt(LivingAttackEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (iFrameTicks.containsKey(player)) {
                event.setCanceled(true);
                player.serverLevel().playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0f, 1.0f);
            }
        }
    }

    @Override
    public void executeSkill(ServerPlayer player, @Nullable Object data) {
        if (!player.onGround()) return;
        System.out.println("已使用：Side Step");
        iFrameTicks.put(player, I_FRAME_TICKS);
    }

    @SubscribeEvent
    public static void onServerTick(net.minecraftforge.event.TickEvent.ServerTickEvent event) {
        if (event.phase == net.minecraftforge.event.TickEvent.Phase.END) {
            iFrameTicks.entrySet().removeIf(entry -> {
                int remaining = entry.getValue() - 1;
                if (remaining <= 0) return true;
                else {
                    entry.setValue(remaining);
                    return false;
                }
            });
        }
    }
}