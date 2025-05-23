package net.team.mxumod.minecraftxundertale.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneEntity;
import net.team.mxumod.minecraftxundertale.entities.models.BoneEntityModel;
import org.jetbrains.annotations.NotNull;

public class BoneEntityRenderer extends EntityRenderer<BoneEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/entities/bone_entity.png");
    private final BoneEntityModel<BoneEntity> model;

    public BoneEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
        this.model = new BoneEntityModel<>(pContext.bakeLayer(BoneEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull BoneEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.scale(1.0F, 1.0F, 2.0F);
        poseStack.translate(0, 0.5, 0);

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BoneEntity pEntity) {
        return TEXTURE;
    }
}
