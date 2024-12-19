package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.phys.Vec3;

public class Blocking {

    static boolean isBlocking;
    public static void blocking(ServerPlayer player) {

        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        IronGolem ironGolem = new IronGolem(EntityType.IRON_GOLEM, level);
        ironGolem.setNoAi(true);
        ironGolem.setPos(posInFront.x, posInFront.y,posInFront.z);

        if (!isBlocking) {
            level.addFreshEntity(ironGolem);
            isBlocking = true;
        }else {
            ironGolem.teleportTo(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
            ironGolem.setRemoved(Entity.RemovalReason.DISCARDED);
            isBlocking = false;
        }

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
