package net.team.mxumod.minecraftxundertale.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import org.joml.Matrix4f;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypeRegistry;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

public class MxuClient {
    public static void renderBlasterBeam(Vec3 start, Vec3 end, float width) {
        ResourceLocation BEAM_TEXTURE = new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "textures/effects/beam.png");

        Matrix4f matrix4f = Minecraft.getInstance().gameRenderer.getProjectionMatrix(Minecraft.getInstance().options.fov().get());
        // Create a new WorldVFXBuilder instance
        VFXBuilders.WorldVFXBuilder builder = new VFXBuilders.WorldVFXBuilder();

        // Define the render layer with the beam texture
        RenderType renderType = LodestoneRenderTypeRegistry.ADDITIVE_TEXTURE.apply(RenderTypeToken.createToken(BEAM_TEXTURE));

        // Set the render type (render layer)
        builder.setColor(255, 255, 255)
                .setRenderType(renderType)
                .setAlpha(1.0f)
                .renderBeam(matrix4f, start, end, width);
    }
}
