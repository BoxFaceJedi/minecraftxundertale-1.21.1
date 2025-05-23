
package net.team.mxumod.minecraftxundertale.entities.bone;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;


public class BoneEntity extends Entity {
    public BoneEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = false;
        this.setBoundingBox(new AABB(this.position(), this.position().add(0, 2, 0)));
    }

    @Override public boolean canBeCollidedWith() { return true; }
    @Override public boolean isPickable() { return true; }

    @Override protected void defineSynchedData() {}
    @Override protected void readAdditionalSaveData(@NotNull CompoundTag tag) {}
    @Override protected void addAdditionalSaveData(@NotNull CompoundTag tag) {}

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getDirectEntity() instanceof AbstractArrow arrow) {
            arrow.discard();
            return false;
        }
        return super.hurt(source, amount);
    }
}
