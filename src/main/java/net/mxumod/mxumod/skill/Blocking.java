package net.mxumod.mxumod.skill;

import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;
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
            ironGolem.kill();
            isBlocking = false;
        }

    }

    public static Vec3 getPositionInFrontOfPlayer(ServerPlayer player, double distance) {
        // Get player's current position
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot()); // Convert yaw to radians

        double offsetX = -Math.sin(playerYaw) * distance;
        double offsetZ = Math.cos(playerYaw) * distance;

        double y = player.getY();

        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

}
