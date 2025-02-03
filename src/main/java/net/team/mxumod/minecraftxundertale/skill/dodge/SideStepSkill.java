package net.team.mxumod.minecraftxundertale.skill.dodge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.AttackEntityEvent;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = Minecraftxundertale.MODID, value = Dist.CLIENT)
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
    public static void onPlayerHurt(AttackEntityEvent event) {
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