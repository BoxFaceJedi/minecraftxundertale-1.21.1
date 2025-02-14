package net.mxumod.mxumod.entities.bone;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import static net.mxumod.mxumod.entities.ModEntities.*;

public class BoneProjectileEntity extends AbstractArrow {
    public BoneProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    public BoneProjectileEntity(Level world, LivingEntity shooter) {
        super(BONE_PROJECTILE.get(), shooter, world, new ItemStack(Items.AIR), new ItemStack(Items.AIR));
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
    }
}
