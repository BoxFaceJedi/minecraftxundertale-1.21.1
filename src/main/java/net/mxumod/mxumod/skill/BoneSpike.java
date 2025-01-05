package net.mxumod.mxumod.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Array;
import java.util.List;

@Mod.EventBusSubscriber()
public class BoneSpike {

    public static void boneSpikeAttack(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);
        List<LivingEntity> entityInFront = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(posInFront, 1, 1, 1));

        EvokerFangs bone_spike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
        bone_spike.setPos(posInFront.x, posInFront.y, posInFront.z);
        bone_spike.setOwner(player);
        level.addFreshEntity(bone_spike);

        //for (LivingEntity entity : entityInFront) {
            //entity.setDeltaMovement(entity.getDeltaMovement().normalize().add(0.0, 2.5, 0.0));
        //}
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
    public static void boneSpikeHit(LivingAttackEvent event) {
        if (event.getSource().getDirectEntity() instanceof EvokerFangs fangs) {
            if (fangs.getOwner() instanceof Player player) {
                event.getEntity().setDeltaMovement(event.getEntity().getDeltaMovement().normalize().multiply(0.0, 2.0, 0.0));
                player.sendSystemMessage(Component.literal("SPECIAL ATTACK"));
            }
        }
    }
}