package net.team.mxumod.minecraftxundertale.entities.bone;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.entities.ModEntities;

public class BoneProjectileEntity extends AbstractArrow {


    public BoneProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    public BoneProjectileEntity(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntities.BONE_PROJECTILE.get(), pX, pY, pZ, pLevel);
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entity = result.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 1);

        if (!this.level().isClientSide) {
            this.level().broadcastEntityEvent(this, (byte)3);
            this.discard();
        }
    }

    @Override
    protected ItemStack getPickupItem() {
        return null;
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level().isClientSide && this.inGroundTime > 60) {
            this.discard();
        }

        Vec3 motion = this.getDeltaMovement();
        if (!motion.equals(Vec3.ZERO) && !this.inGround) {
            float yaw = (float) (Math.atan2(-motion.x, motion.z) * (180F / Math.PI));
            float pitch = (float) (Math.asin(-motion.y / motion.length()) * (180F / Math.PI));
            this.setRot(yaw, pitch);
        }
    }
}