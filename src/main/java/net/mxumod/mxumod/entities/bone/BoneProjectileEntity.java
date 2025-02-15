package net.mxumod.mxumod.entities.bone;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BoneProjectileEntity extends AbstractArrow {
    public BoneProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.BONE);
    }
}
