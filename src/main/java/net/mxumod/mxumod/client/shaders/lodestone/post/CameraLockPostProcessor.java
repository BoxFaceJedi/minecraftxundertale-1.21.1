package net.mxumod.mxumod.client.shaders.lodestone.post;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sun.jna.platform.unix.X11;
import net.minecraft.resources.ResourceLocation;
import net.mxumod.mxumod.MxuMod;
import org.joml.Matrix4f;
import team.lodestar.lodestone.systems.postprocess.PostProcessor;

public class CameraLockPostProcessor extends PostProcessor {
    public static final CameraLockPostProcessor INSTANCE = new CameraLockPostProcessor()    ;

    static {
        INSTANCE.setActive(false);
    }

    @Override
    public ResourceLocation getPostChainLocation() {
        return ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "tint");
    }

    @Override
    public void beforeProcess(Matrix4f matrix4f) {

    }

    @Override
    public void afterProcess() {

    }
}
