package net.team.mxumod.minecraftxundertale.skill.special;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.living.LivingKnockBackEvent;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.WeakHashMap;

@EventBusSubscriber(modid = Minecraftxundertale.MODID, value = Dist.CLIENT)
public class BoneSpikeSkill extends Skill<ServerPlayer> {
    private static final WeakHashMap<LivingEntity, Boolean> attackFlagMap = new WeakHashMap<>();

    static Vec3 posInFront;

    public BoneSpikeSkill() {
        super("Bone Spike", 50, 15);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingDamageEvent.Post event) {
        // Ensure the entity and last damage source are not null
        if (event.getEntity().getLastDamageSource() == null) {
            return;
        }

        // Check if the source is EvokerFangs
        if (event.getEntity().getLastDamageSource().getDirectEntity() instanceof EvokerFangs fangs) {
            // Ensure the fangs' owner is a player and the player is valid
            if (fangs.getOwner() instanceof ServerPlayer player) {
                // Ensure target is valid (should already be LivingEntity, but it's a good safety check)
                if (event.getEntity() instanceof LivingEntity target) {
                    // Mark the entity in the map
                    attackFlagMap.put(target, true); // Mark the entity for special treatment
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKnockback(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();

        // Check if the entity was marked in the attack event
        if (attackFlagMap.getOrDefault(entity, false)) {
            // Cancel knockback
            event.setCanceled(true);

            //apply upward motion
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 1.5, 0.0));

            // Optional: Remove the entity from the map after handling
            attackFlagMap.remove(entity);
        }
    }

    @Override
    public void activate(Player player) {
        ServerLevel level = ((ServerLevel) player.level());
        posInFront = getPositionInFrontOfPlayer((ServerPlayer) player, 1.5);

        EvokerFangs bone_spike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
        bone_spike.setPos(posInFront.x, posInFront.y, posInFront.z);
        bone_spike.setOwner(player);
        level.addFreshEntity(bone_spike);
    }
}