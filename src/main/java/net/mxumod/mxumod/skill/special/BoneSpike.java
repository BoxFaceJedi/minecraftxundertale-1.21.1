package net.mxumod.mxumod.skill.special;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;

import java.util.WeakHashMap;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class BoneSpike {
    private static final WeakHashMap<LivingEntity, Boolean> attackFlagMap = new WeakHashMap<>();

    static Vec3 posInFront;

    public static void boneSpikeAttack(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        posInFront = getPositionInFrontOfPlayer(player, 1.5);

        EvokerFangs bone_spike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
        bone_spike.setPos(posInFront.x, posInFront.y, posInFront.z);
        bone_spike.setOwner(player);
        level.addFreshEntity(bone_spike);
    }

    public static Vec3 getPositionInFrontOfPlayer(ServerPlayer player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());

        double offsetX = -Math.sin(playerYaw) * distance;
        double offsetZ = Math.cos(playerYaw) * distance;

        double y = player.getY();

        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        // Check if the source is EvokerFangs
        if (event.getSource().getDirectEntity() instanceof EvokerFangs fangs) {
            // Ensure the fangs' owner is a player
            if (fangs.getOwner() instanceof ServerPlayer player) {
                // Mark the entity in the map
                if (event.getEntity() instanceof LivingEntity target) {
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
}