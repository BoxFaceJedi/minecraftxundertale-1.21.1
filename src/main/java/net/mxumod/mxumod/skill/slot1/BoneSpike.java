package net.mxumod.mxumod.skill.slot1;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.LightingFx;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.TestShaderProcessor;
import org.joml.Vector3f;

import java.util.List;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class BoneSpike {
    static Vec3 posInFront;
    static List<LivingEntity> entityInFront;
    public static void boneSpikeAttack(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        posInFront = getPositionInFrontOfPlayer(player, 1);
        entityInFront = level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(posInFront, 1, 1, 1));

        EvokerFangs bone_spike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
        bone_spike.setPos(posInFront.x, posInFront.y, posInFront.z);
        bone_spike.setOwner(player);
        level.addFreshEntity(bone_spike);

        Vector3f center = new Vector3f(posInFront.toVector3f());
        Vector3f color = new Vector3f(1, 0, 1);
        TestShaderProcessor.INSTANCE.addFxInstance(new LightingFx(center, color));
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
                for (LivingEntity entity : entityInFront) {
                    entity.setDeltaMovement(entity.getDeltaMovement().normalize().add(0.0, 2.5, 0.0));
                }
            }
        }
    }
}