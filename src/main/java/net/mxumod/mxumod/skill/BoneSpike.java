package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.Vec3;

public class BoneSpike {
    private static EvokerFangs bone_spike;

    public static void boneSpikeAttack(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

            bone_spike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
            bone_spike.setPos(posInFront.x, posInFront.y, posInFront.z);
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

}