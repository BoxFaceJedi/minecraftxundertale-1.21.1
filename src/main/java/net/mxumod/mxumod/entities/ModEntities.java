package net.mxumod.mxumod.entities;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.entities.bone.BoneProjectileEntity;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, MxuMod.MOD_ID);

    public static final RegistryObject<EntityType<BoneProjectileEntity>> BONE_PROJECTILE =
            ENTITY_TYPE_DEFERRED_REGISTER.register("bone_projectile",
                    () -> EntityType.Builder.<BoneProjectileEntity>of(BoneProjectileEntity::new, MobCategory.MISC)
                            .sized(0.5F, 0.5F)
                            .build(ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "bone_projectile").toString()));

}
