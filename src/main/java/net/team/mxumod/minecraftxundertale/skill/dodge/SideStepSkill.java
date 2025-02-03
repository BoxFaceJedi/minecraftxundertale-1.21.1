package net.team.mxumod.minecraftxundertale.skill.dodge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.skill.Skill;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class SideStepSkill extends Skill<Player> {

    private static final Map<Player, Long> iFrameTracker = new HashMap<>();
    private static final long I_FRAME_DURATION = 250; // in milliseconds (1 second)

    public SideStepSkill() {
        super("Side Step", 0, 10);
    }

    @Override
    public void activate(Player player) {
        // Apply dodge movement
        player.setDeltaMovement(player.getDeltaMovement().normalize().multiply(1.5, 0.0, 1.5));

        // Activate i-frames
        if (player.getDeltaMovement().length() != 0.0) {
            iFrameTracker.put(player, System.currentTimeMillis());
        }
    }

    @SubscribeEvent
    public static void onPlayerHurt(LivingAttackEvent event) {
        // Check if the entity is a player
        if (event.getEntity() instanceof ServerPlayer player) {
            // Check if the player has active i-frames
            Long iFrameStart = iFrameTracker.get(player);
            if (iFrameStart != null) {
                long currentTime = System.currentTimeMillis();
                if (currentTime - iFrameStart < I_FRAME_DURATION) {
                    // Cancel the damage during i-frames
                    event.setCanceled(true);
                    player.serverLevel().playSound(null, player.position().x, player.position().y, player.position().z, SoundEvents.WIND_CHARGE_BURST, SoundSource.PLAYERS, 1.0f, 1.0f);
                } else {
                    // Remove player from the tracker after i-frames expire
                    iFrameTracker.remove(player);
                }
            }
        }
    }
}