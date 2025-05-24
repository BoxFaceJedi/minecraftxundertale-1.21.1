package net.team.mxumod.minecraftxundertale.entities;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.client.renderers.BoneEntityRenderer;
import net.team.mxumod.minecraftxundertale.entities.client.renderers.BoneProjectileRenderer;
import net.team.mxumod.minecraftxundertale.entities.client.renderers.GasterBlasterEntityRender_Idle;
import net.team.mxumod.minecraftxundertale.entities.client.renderers.GasterBlasterEntityRender_Launch;
import net.team.mxumod.minecraftxundertale.entities.models.BoneEntityModel;
import net.team.mxumod.minecraftxundertale.entities.models.BoneProjectileModel;
import net.team.mxumod.minecraftxundertale.entities.models.GasterBlasterEntityModel_Idle;
import net.team.mxumod.minecraftxundertale.entities.models.GasterBlasterEntityModel_Launch;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModRenderers {
    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BONE_ENTITY.get(), BoneEntityRenderer::new);
        event.registerEntityRenderer(ModEntities.BONE_PROJECTILE.get(), BoneProjectileRenderer::new);
        event.registerEntityRenderer(ModEntities.GASTER_BLASTER_ENTITY_IDLE.get(), GasterBlasterEntityRender_Idle::new);
        event.registerEntityRenderer(ModEntities.GASTER_BLASTER_LAUNCH_IDLE.get(), GasterBlasterEntityRender_Launch::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BoneProjectileModel.LAYER_LOCATION, BoneProjectileModel::createBodyLayer);
        event.registerLayerDefinition(BoneEntityModel.LAYER_LOCATION, BoneEntityModel::createBodyLayer);
        event.registerLayerDefinition(GasterBlasterEntityModel_Idle.LAYER_LOCATION, GasterBlasterEntityModel_Idle::createBodyLayer);
        event.registerLayerDefinition(GasterBlasterEntityModel_Launch.LAYER_LOCATION, GasterBlasterEntityModel_Launch::createBodyLayer);
    }
}
