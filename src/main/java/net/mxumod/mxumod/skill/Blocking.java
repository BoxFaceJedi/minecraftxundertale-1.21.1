package net.mxumod.mxumod.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Overwrite;

public class Blocking {

    public static boolean isBlocking;
    private static IronGolem blockingGolem;

    public static void blocking(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        if (!isBlocking) {
            blockingGolem = new IronGolem(EntityType.IRON_GOLEM, level);
            blockingGolem.setNoAi(true);
            blockingGolem.setPos(posInFront.x, posInFront.y, posInFront.z);
            level.addFreshEntity(blockingGolem);

            isBlocking = true;
            player.sendSystemMessage(Component.literal("Player is Blocking!"));
        } else {
            if (blockingGolem != null) {
                blockingGolem.kill();
            }
            blockingGolem = null;
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
