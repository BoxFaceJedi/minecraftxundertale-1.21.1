package net.team.mxumod.minecraftxundertale.effects;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import team.lodestar.lodestone.systems.postprocess.PostProcessor;

public class TintPostProcessor extends PostProcessor {
    public static final TintPostProcessor INSTANCE = new TintPostProcessor();

    @Override
    public ResourceLocation getPostChainLocation() {
        return new ResourceLocation(MinecraftxUndertaleMod.MOD_ID, "tint_post");
    }

    @Override
    public void beforeProcess(PoseStack poseStack) {

    }

    @Override
    public void afterProcess() {

    }
}