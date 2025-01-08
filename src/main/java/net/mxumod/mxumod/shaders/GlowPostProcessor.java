package net.mxumod.mxumod.shaders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.resources.ResourceLocation;
import net.mxumod.mxumod.MxuMod;
import team.lodestar.lodestone.systems.postprocess.MultiInstancePostProcessor;

public class GlowPostProcessor extends MultiInstancePostProcessor<LightingFx> {
    public static final GlowPostProcessor INSTANCE = new GlowPostProcessor();
    private EffectInstance effectGlow;

    @Override
    public ResourceLocation getPostChainLocation() {
        return ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "glow_post");
    }
    // Max amount of FxInstances that can be added to the post processor at once
    @Override
    protected int getMaxInstances() {
        return 16;
    }

    // We passed in a total of 6 floats/uniforms to the shader inside our LightingFx class so this should return 6, will crash if it doesn't match
    @Override
    protected int getDataSizePerInstance() {
        return 6;
    }

    @Override
    public void init() {
        super.init();
        if (postChain != null) {
            effectGlow = effects[0];
        }
    }

    @Override
    public void beforeProcess(PoseStack viewModelStack) {
        super.beforeProcess(viewModelStack);
        setDataBufferUniform(effectGlow, "DataBuffer", "InstanceCount");
    }

    @Override
    public void afterProcess() {

    }
}
