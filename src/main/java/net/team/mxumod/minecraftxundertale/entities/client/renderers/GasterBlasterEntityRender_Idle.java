package net.team.mxumod.minecraftxundertale.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.gasterblasters.GasterBlasterEntity_Idle;
import net.team.mxumod.minecraftxundertale.entities.models.GasterBlasterEntityModel_Idle;
import org.jetbrains.annotations.NotNull;

public class GasterBlasterEntityRender_Idle extends EntityRenderer<GasterBlasterEntity_Idle> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/entities/gaster_blaster_idle.png");
    private final GasterBlasterEntityModel_Idle<GasterBlasterEntity_Idle> model;

    public GasterBlasterEntityRender_Idle(EntityRendererProvider.Context context) {
        super(context);
        this.model = new GasterBlasterEntityModel_Idle<>(context.bakeLayer(GasterBlasterEntityModel_Idle.LAYER_LOCATION));
    }

    @Override
    public void render(GasterBlasterEntity_Idle entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Apply entity rotation
        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GasterBlasterEntity_Idle entity) {
        return TEXTURE;
    }
}