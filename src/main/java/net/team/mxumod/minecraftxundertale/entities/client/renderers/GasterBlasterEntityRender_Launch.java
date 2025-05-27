package net.team.mxumod.minecraftxundertale.entities.client.renderers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.entities.gasterblasters.GasterBlasterEntity_Launch;
import net.team.mxumod.minecraftxundertale.entities.models.GasterBlasterEntityModel_Launch;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

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
        // Render the beam effect
        renderBlasterBeam(entity.position(), entity.pick(20, 0, false).getLocation(), 1.0f, poseStack);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull GasterBlasterEntity_Launch entity) {
        return TEXTURE;
    }

    public void renderBlasterBeam(Vec3 start, Vec3 end, float width, PoseStack poseStack){
        ResourceLocation BEAM_TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/effects/beam.png");

        poseStack.pushPose();
        Matrix4f matrix4f = poseStack.last().pose();
        // Create a new WorldVFXBuilder instance
        VFXBuilders.WorldVFXBuilder builder = new VFXBuilders.WorldVFXBuilder();

        // Define the render layer with the beam texture
        RenderType renderType = LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.apply(RenderTypeToken.createToken(BEAM_TEXTURE));

        // Set the render type (render layer)
        builder.setColor(255, 255, 255)
                .setRenderType(renderType)
                .setAlpha(0.9f)
                .renderBeam(matrix4f, start, end, width);
        poseStack.popPose();
    }
}