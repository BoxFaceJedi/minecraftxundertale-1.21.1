package net.team.mxumod.minecraftxundertale.skill.block;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import javax.annotation.Nullable;
import java.util.UUID;

public class BoneWallSkill extends Skill {

    public BoneWallSkill() {
        super("Bone Wall", 0, 100);
    }

    public boolean isBlocking() {
        return blocking;
    }

    private boolean blocking;
    private IronGolem blockingGolem;
    private static final UUID SPEED_MODIFIER_ID = UUID.fromString("be386a3e-e2d6-4e9e-8c8f-24df028f546a");
    private static void applySpeedModifier (Player player, double multiplier) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributes == null) return;

        if (attributes.getModifier(SPEED_MODIFIER_ID) == null) { // ✅ Correct condition
            AttributeModifier modifier = new AttributeModifier(
                    SPEED_MODIFIER_ID, // ✅ Correct UUID tracking
                    "Blocking Speed Reduction",
                    -1.0 + multiplier,
                    AttributeModifier.Operation.MULTIPLY_TOTAL
            );

            attributes.addTransientModifier(modifier);
        }
    }

    private static void removeModifier(ServerPlayer player) {
        var attributes = player.getAttribute(Attributes.MOVEMENT_SPEED);
        if (attributes == null) return;

        attributes.removeModifier(SPEED_MODIFIER_ID); // ✅ Proper removal by UUID
    }

    @Override
    public void executeSkill(ServerPlayer player, @Nullable Object data) {
        ServerLevel level = (ServerLevel) player.level();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1);

        if (!blocking) {
            if (this.blockingGolem != null) {
                this.blockingGolem.remove(Entity.RemovalReason.KILLED);
            }
            this.blockingGolem = new IronGolem(EntityType.IRON_GOLEM, level);
            this.blockingGolem.setNoAi(true);
            this.blockingGolem.setPos(posInFront.x, posInFront.y, posInFront.z);
            level.addFreshEntity(this.blockingGolem);

            applySpeedModifier(player, 0.35);
            this.blocking = true;
        } else {
            if (this.blockingGolem != null) {
                this.blockingGolem.remove(Entity.RemovalReason.KILLED);
                removeModifier(player);
            }
            this.blockingGolem = null;
            this.blocking = false;
        }
    }
}