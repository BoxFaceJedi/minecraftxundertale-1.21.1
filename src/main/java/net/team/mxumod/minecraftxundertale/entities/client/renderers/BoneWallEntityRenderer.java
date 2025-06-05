package net.team.mxumod.minecraftxundertale.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneWallEntity;
import net.team.mxumod.minecraftxundertale.entities.models.BoneWallEntityModel;
import org.jetbrains.annotations.NotNull;

public class BoneWallEntityRenderer extends EntityRenderer<BoneWallEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/entities/bone_wall_entity.png");
    private final BoneWallEntityModel<BoneWallEntity> model;

    public BoneWallEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new BoneWallEntityModel<>(context.bakeLayer(net.team.mxumod.minecraftxundertale.entities.models.BoneWallEntityModel.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull BoneWallEntity entity, float entityYaw, float partialTicks, @NotNull PoseStack poseStack, MultiBufferSource buffer, int packedLight) {

        VertexConsumer vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BoneWallEntity entity) {
        return TEXTURE;
    }
}