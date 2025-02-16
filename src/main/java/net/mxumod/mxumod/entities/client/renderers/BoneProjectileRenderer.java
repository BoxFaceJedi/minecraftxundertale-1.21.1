package net.mxumod.mxumod.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.entities.bone.BoneProjectileEntity;
import net.mxumod.mxumod.entities.models.BoneProjectileModel;

public class BoneProjectileRenderer extends EntityRenderer<BoneProjectileEntity> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "textures/entities/bone_projectile.png");
    private final BoneProjectileModel model;

    public BoneProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BoneProjectileModel(context.bakeLayer(BoneProjectileModel.LAYER_LOCATION));
    }

    @Override
    public void render(BoneProjectileEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        // Apply entity rotation
        poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot()));
        poseStack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, -1);
    }

    @Override
    public ResourceLocation getTextureLocation(BoneProjectileEntity entity) {
        return TEXTURE;
    }
}
