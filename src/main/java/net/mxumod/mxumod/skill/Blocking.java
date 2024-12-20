package net.mxumod.mxumod.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.phys.Vec3;

public class Blocking {

    public static boolean isBlocking;
    public static void blocking(ServerPlayer player) {

        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        IronGolem ironGolem = new IronGolem(EntityType.IRON_GOLEM, level);
        ironGolem.setNoAi(true);
        ironGolem.setPos(posInFront.x, posInFront.y,posInFront.z);
        ironGolem.setInvisible(false);


        if (!isBlocking) {
            //level.addFreshEntity(ironGolem);
            isBlocking = true;
            player.sendSystemMessage(Component.literal("Player is Blocking!"));
        }else {
            ironGolem.kill();
            isBlocking = false;
            player.sendSystemMessage(Component.literal("Player no longer Blocking!"));
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
