package net.mxumod.mxumod.entities.bone;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class BoneEntity extends AbstractArrow {
    public BoneEntity(EntityType<? extends BoneEntity> entityType, Level world) {
        super(entityType, world);
    }

    public BoneEntity(Level world, LivingEntity shooter) {
        super(EntityType.ARROW, world);
    }

    @Override
    protected @NotNull ItemStack getDefaultPickupItem() {
        return new ItemStack(Items.AIR);
    }
}
