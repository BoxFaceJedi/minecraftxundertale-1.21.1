package net.mxumod.mxumod.entities.bone;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.mxumod.mxumod.entities.EntityRegistry;

public class BoneEntity extends AbstractArrow {
    public BoneEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
        super(entityType, world);
    }

    public BoneEntity(Level world, LivingEntity shooter) {
        super(EntityRegistry.BoneEntity.get(), shooter, world);
    }

    @Override
    protected ItemStack getPickupItem() {
        return new ItemStack(Items.BONE); // 定義拾取時變成骨頭
    }
}
