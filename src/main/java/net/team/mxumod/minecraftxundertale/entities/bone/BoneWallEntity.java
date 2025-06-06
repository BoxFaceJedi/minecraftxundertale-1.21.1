
package net.team.mxumod.minecraftxundertale.entities.bone;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;


public class BoneWallEntity extends Entity {
    public BoneWallEntity(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    @Override public boolean canBeCollidedWith() { return true; }
    @Override public boolean isPickable() { return true; }

    @Override
    public void tick() {
        super.tick();

        double x = this.getX();
        double y = this.getY();
        double z = this.getZ();
        this.setBoundingBox(new AABB(
                x - 1.5, y, z - 0.5,
                x + 1.5, y + 2.0, z + 0.5
        ));
    }

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
