package net.team.mxumod.minecraftxundertale.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import team.lodestar.lodestone.registry.client.LodestoneRenderTypes;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;
import team.lodestar.lodestone.systems.rendering.rendeertype.RenderTypeToken;

public class MxuClient {

    private static final ResourceLocation BEAM_TEXTURE = ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "textures/effects/beam.png");
    public static void renderBlasterBeam(PoseStack poseStack, Vec3 start, Vec3 end, float width) {

        // Create a new WorldVFXBuilder instance
        VFXBuilders.WorldVFXBuilder builder = new VFXBuilders.WorldVFXBuilder();

        // Define the render layer with the beam texture
        RenderType renderType = LodestoneRenderTypes.ADDITIVE_TEXTURE.apply(RenderTypeToken.createToken(BEAM_TEXTURE));

        // Set the render type (render layer)
        builder.setRenderType(renderType)
                .setColor(255, 255, 255)
                .setAlpha(1.0f)
                .renderBeam(poseStack.last().pose(), start, end, width);
    }
}
