package net.team.mxumod.minecraftxundertale.skill.block;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.entities.ModEntities;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneWallEntity;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import javax.annotation.Nullable;
import java.util.UUID;

public class BoneWallSkill extends Skill {

    public BoneWallSkill() {
        super("Bone Wall", 2, 100);
    }

    public boolean isBlocking() {
        return blocking;
    }

    private boolean blocking;
    private BoneWallEntity boneWallEntity;
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

        attributes.removeModifier(SPEED_MODIFIER_ID);
    }

    @Override
    public void executeSkill(ServerPlayer player, @Nullable CompoundTag data) {
        ServerLevel level = (ServerLevel) player.level();

        if (!blocking) {
            if (this.boneWallEntity != null) {
                this.boneWallEntity.remove(Entity.RemovalReason.KILLED);
            }
            this.boneWallEntity = new BoneWallEntity(ModEntities.BONE_WALL_ENTITY.get(), level);

            this.boneWallEntity.setPos(getPositionInFrontOfPlayer(player, 1));
            this.boneWallEntity.setYRot(player.getYRot());

            level.addFreshEntity(this.boneWallEntity);

            applySpeedModifier(player, 0.35);
            this.blocking = true;
        } else {
            if (this.boneWallEntity != null) {
                this.boneWallEntity.remove(Entity.RemovalReason.KILLED);
                removeModifier(player);
            }
            this.boneWallEntity = null;
            this.blocking = false;
        }
    }
}