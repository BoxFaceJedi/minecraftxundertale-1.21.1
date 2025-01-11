package net.mxumod.mxumod.skill.slot1;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.mxumod.mxumod.MxuMod;

public class Blocking {

    public static boolean isBlocking() {
        return blocking;
    }

    public static boolean blocking;
    private static IronGolem blockingGolem;

    public static void blocking(ServerPlayer player) {
        ServerLevel level = player.serverLevel().getLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        if (!blocking) {
            blockingGolem = new IronGolem(EntityType.IRON_GOLEM, level);
            blockingGolem.setNoAi(true);
            blockingGolem.setPos(posInFront.x, posInFront.y, posInFront.z);
            level.addFreshEntity(blockingGolem);

            applySpeedModifier(player, 0.35);

            blocking = true;
        } else {
            if (blockingGolem != null) {
                blockingGolem.kill();

                removeModifier(player);
            }
            blockingGolem = null;
            blocking = false;
        }
    }

    private static void applySpeedModifier (Player player, double multiplier) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);

        if (attributes == null) return;

        if (!attributes.hasModifier(ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "blocking_speed"))) {

            AttributeModifier modifier = new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "blocking_speed"),
                    -1.0 + multiplier,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );

            attributes.addPermanentModifier(modifier);
        }
    }

    private static void removeModifier(ServerPlayer player) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributes == null) return;

        attributes.removeModifier(ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "blocking_speed"));
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