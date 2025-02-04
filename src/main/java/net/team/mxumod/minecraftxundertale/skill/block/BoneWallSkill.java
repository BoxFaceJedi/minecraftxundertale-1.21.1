package net.team.mxumod.minecraftxundertale.skill.block;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.event.EnterCombatmode;
import net.team.mxumod.minecraftxundertale.skill.Skill;

public class BoneWallSkill extends Skill<ServerPlayer> {
    private static boolean blocking = false;
    private static IronGolem blockingGolem;

    public BoneWallSkill() {
        super("Bone Wall", 0, 5);
    }

    public static boolean isBlocking() {
        return blocking;
    }

    private static void applySpeedModifier(ServerPlayer player, double multiplier) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributes == null) return;

        if (!attributes.hasModifier(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "blocking_speed"))) {
            AttributeModifier modifier = new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "blocking_speed"),
                    -1.0 + multiplier,
                    AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
            );
            attributes.addPermanentModifier(modifier);
        }
        player.sendSystemMessage(Component.literal("applied slowness"));
    }

    private static void removeModifier(ServerPlayer player) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributes == null) return;

        attributes.removeModifier(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "blocking_speed"));
        player.sendSystemMessage(Component.literal("removed slowness"));
    }

    @Override
    public void activate(Player player) {

        ServerPlayer serverPlayer = (ServerPlayer) player;
        // Ensure blocking only works in combat mode
        if (!EnterCombatmode.isCombatMode()) {
            forceDisableBlocking(serverPlayer);
            return;
        }

        ServerLevel level = (ServerLevel) player.level();
        Vec3 posInFront = getPositionInFrontOfPlayer((ServerPlayer) player, 1);

        if (!blocking) {
            blockingGolem = new IronGolem(EntityType.IRON_GOLEM, level);
            blockingGolem.setNoAi(true);
            blockingGolem.setPos(posInFront.x, posInFront.y, posInFront.z);
            level.addFreshEntity(blockingGolem);

            applySpeedModifier(serverPlayer, 0.35);

            blocking = true;
        } else {
            forceDisableBlocking(serverPlayer);
        }
    }

    public static void forceDisableBlocking(ServerPlayer player) {
        if (blocking) {
            if (blockingGolem != null) {
                blockingGolem.kill();
                blockingGolem = null;
            }

            // Remove speed modifier when blocking ends
            removeModifier(player);

            blocking = false;
        }
    }
}