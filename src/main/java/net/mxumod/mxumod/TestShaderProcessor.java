package net.mxumod.mxumod;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.resources.ResourceLocation;
import team.lodestar.lodestone.systems.postprocess.MultiInstancePostProcessor;

public class TestShaderProcessor extends MultiInstancePostProcessor<LightingFx> {

    public static final TestShaderProcessor INSTANCE = new TestShaderProcessor();
    private EffectInstance effectGlow;

    @Override
    protected int getMaxInstances() {
        return 16;
    }

    @Override
    protected int getDataSizePerInstance() {
        return 6;
    }

    @Override
    public ResourceLocation getPostChainLocation() {
        return ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "glow");
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
