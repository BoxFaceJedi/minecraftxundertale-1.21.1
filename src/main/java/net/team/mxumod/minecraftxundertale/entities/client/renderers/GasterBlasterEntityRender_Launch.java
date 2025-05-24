package net.team.mxumod.minecraftxundertale.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.gasterblasters.GasterBlasterEntity_Launch;
import net.team.mxumod.minecraftxundertale.entities.models.GasterBlasterEntityModel_Launch;
import org.jetbrains.annotations.NotNull;

public class GasterBlasterEntityRender_Launch extends EntityRenderer<GasterBlasterEntity_Launch> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/entities/gaster_blaster_launch.png");
    private final GasterBlasterEntityModel_Launch<GasterBlasterEntity_Launch> model;

    public GasterBlasterEntityRender_Launch(EntityRendererProvider.Context context) {
        super(context);
        this.model = new GasterBlasterEntityModel_Launch<>(context.bakeLayer(GasterBlasterEntityModel_Launch.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull GasterBlasterEntity_Launch entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GasterBlasterEntity_Launch entity) {
        return TEXTURE;
    }
}