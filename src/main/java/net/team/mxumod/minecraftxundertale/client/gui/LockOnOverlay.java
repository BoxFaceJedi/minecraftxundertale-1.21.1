package net.team.mxumod.minecraftxundertale.client.gui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.CustomizeGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.util.CameraLock;
import org.joml.Matrix4f;
import org.joml.Vector4f;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT)
public class LockOnOverlay{
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent event) {
        if (mc.player == null || CameraLock.getTarget() == null) return;

        Vec3 targetPos = CameraLock.getTarget().position();
        Vec2 screenPos = projectToScreen(targetPos, event.getGuiGraphics().pose());

        Window window = mc.getWindow();

        int centerX = window.getGuiScaledWidth()/2;
        int centerY = window.getGuiScaledHeight()/2;

        GuiGraphics guiGraphics = event.getGuiGraphics();
        if (screenPos != null) {
            guiGraphics.blit(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID,
                    "textures/effects/heart.png"),
                    (int) screenPos.x - 8 + centerX, (int) screenPos.y - 8 + centerY,
                    0, 0, 16, 16, 16, 16
            );
        }
    }

    public static Vec2 projectToScreen(Vec3 worldPos, PoseStack poseStack) {
        Camera camera = mc.gameRenderer.getMainCamera();
        Vec3 camPos = camera.getPosition();
        Vec3 relativePos = worldPos.subtract(camPos);

        Matrix4f projMatrix = RenderSystem.getProjectionMatrix();
        Matrix4f modelViewMatrix = RenderSystem.getModelViewMatrix();

        Vector4f clipPos = new Vector4f((float) relativePos.x, (float) relativePos.y, (float) relativePos.z, 1.0f);
        clipPos.mul(modelViewMatrix);
        clipPos.mul(projMatrix);

        if (clipPos.w == 0.0f) return null;
        clipPos.x /= clipPos.w;
        clipPos.y /= clipPos.w;
        clipPos.z /= clipPos.w;

        Window window = mc.getWindow();
        float screenX = (clipPos.x * 0.5f + 0.5f) * window.getGuiScaledWidth();
        float screenY = (1.0f - (clipPos.y * 0.5f + 0.5f)) * window.getGuiScaledHeight();

        return new Vec2(screenX, screenY);
    }

}
