package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.phys.Vec3;

public class Blocking {

    static boolean isBlocking = false;
    public static void blocking(ServerPlayer player) {

        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        Shulker shulker = new Shulker(EntityType.SHULKER, level);
        shulker.setNoAi(true);
        shulker.setPos(posInFront.x, posInFront.y,posInFront.z);

        if (!isBlocking) {
            level.addFreshEntity(shulker);
            isBlocking = true;
        }else {

            isBlocking = false;
        }

    }

    public static Vec3 getPositionInFrontOfPlayer(ServerPlayer player, double distance) {
        // Get player's current position
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot()); // Convert yaw to radians

        // Calculate offsets based on the yaw (cosine and sine for X and Z directions)
        double offsetX = -Math.sin(playerYaw) * distance; // Negative sine for X-axis
        double offsetZ = Math.cos(playerYaw) * distance;  // Cosine for Z-axis

        // Y-coordinate: Use player's feet position
        double y = player.getY();

        // Combine into a BlockPos (rounded to integers for block coordinates)
        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

}
