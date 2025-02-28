package net.mxumod.mxumod.skill.block;

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
import net.mxumod.mxumod.skill.Skill;

public class BoneWallSkill extends Skill<ServerPlayer> {

    public BoneWallSkill() {
        super("Bone Wall", 0, 5);
    }

    public static boolean isBlocking() {
        return blocking;
    }

    public static boolean blocking;
    private static IronGolem blockingGolem;

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

    @Override
    public void activate(Player player) {
        ServerLevel level = (ServerLevel) player.level();
        Vec3 posInFront = getPositionInFrontOfPlayer((ServerPlayer) player, 1);

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

                removeModifier((ServerPlayer) player);
            }
            blockingGolem = null;
            blocking = false;
        }

    }
}