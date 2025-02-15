package net.mxumod.mxumod.entities.bone;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.mxumod.mxumod.entities.ModEntities;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class BoneProjectileEntity extends AbstractArrow {


    public BoneProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    public BoneProjectileEntity(Level pLevel, double pX, double pY, double pZ, ItemStack pPickupItemStack, @Nullable ItemStack pFiredFromWeapon) {
        super(ModEntities.BONE_PROJECTILE.get(), pX, pY, pZ, pLevel, pPickupItemStack, pFiredFromWeapon);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
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
    public void tick() {
        super.tick();
        if (this.inGround) {
            this.discard();
        }
    }
}
