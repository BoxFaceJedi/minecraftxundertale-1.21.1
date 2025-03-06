package net.team.mxumod.minecraftxundertale.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneProjectileEntity;

import static net.minecraftforge.registries.ForgeRegistries.ENTITY_TYPES;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(ENTITY_TYPES, MinecraftxUndertaleMod.MOD_ID);

    public static final RegistryObject<EntityType<BoneProjectileEntity>> BONE_PROJECTILE =
            ENTITY_TYPE_DEFERRED_REGISTER.register("bone_projectile",
                    () -> EntityType.Builder.<BoneProjectileEntity>of(BoneProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .build("bone_projectile"));

    public static void EntityTypeDeferredRegister(IEventBus eventBus) {
        ENTITY_TYPE_DEFERRED_REGISTER.register(eventBus);
    }
}