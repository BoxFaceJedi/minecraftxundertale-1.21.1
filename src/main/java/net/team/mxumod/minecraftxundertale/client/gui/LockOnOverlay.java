package net.team.mxumod.minecraftxundertale.client.gui;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
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
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || CameraLock.getTarget() == null) return;

        Vec3 targetPos = CameraLock.getTarget().position();
        Vec2 screenPos = testWorldToScreen(targetPos); //projectToScreen(targetPos, event.getGuiGraphics().pose());

        if (screenPos != null) {
            GuiGraphics guiGraphics = event.getGuiGraphics();
            guiGraphics.blit(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID,
                    "textures/effects/heart.png"),
                    (int) screenPos.x, (int) screenPos.y,
                    0, 0, 16, 16  // Change 16x16 if the heart texture is a different size
            );
        }
    }

    public static Matrix4f getViewMatrix() {
        Camera camera = mc.gameRenderer.getMainCamera();
        return new Matrix4f().identity().rotateX((float) Math.toRadians(camera.getXRot()))  // X 軸旋轉 (俯仰角 Pitch)
                .rotateY((float) Math.toRadians(camera.getYRot()))  // Y 軸旋轉 (偏航角 Yaw)
                .translate((float) -camera.getPosition().x, (float) -camera.getPosition().y, (float) -camera.getPosition().z);
    }

    public static Vec2 testWorldToScreen(Vec3 worldPos) {
        Vector4f transformedPos = new Vector4f((float) worldPos.x, (float) worldPos.y, (float) worldPos.z, 1.0f);

        Matrix4f projectionMatrix = mc.gameRenderer.getProjectionMatrix(mc.options.fov().get().floatValue());
        Matrix4f viewMatrix = getViewMatrix();
        viewMatrix.transform(transformedPos);

        return new Vec2((transformedPos.x/transformedPos.w * 0.5f + 0.5f) * mc.getWindow().getGuiScaledWidth(), (0.5f - transformedPos.y/transformedPos.w * 0.5f) * mc.getWindow().getGuiScaledHeight());
    }

    public static Vec2 projectToScreen(Vec3 worldPos, PoseStack poseStack) {
        Camera camera = mc.gameRenderer.getMainCamera();
        Vec3 camPos = camera.getPosition();
        Vec3 relativePos = worldPos.subtract(camPos);

        // Get projection and model-view matrices
        Matrix4f projMatrix = RenderSystem.getProjectionMatrix();
        Matrix4f modelViewMatrix = poseStack.last().pose();

        // Convert world position to clip space
        Vector4f clipPos = new Vector4f((float) relativePos.x, (float) relativePos.y, (float) relativePos.z, 1.0f);
        clipPos.mul(modelViewMatrix);
        clipPos.mul(projMatrix);

        // If W component is negative, entity is behind the camera
        if (clipPos.w <= 0) return null;

        // Convert clip space to NDC (Normalized Device Coordinates)
        clipPos.x /= clipPos.w;
        clipPos.y /= clipPos.w;

        // Convert NDC to screen coordinates
        Window window = mc.getWindow();
        float screenX = (clipPos.x + 1.0f) * 0.5f * window.getGuiScaledWidth();
        float screenY = (1.0f - clipPos.y) * 0.5f * window.getGuiScaledHeight();

        return new Vec2(screenX, screenY);
    }
}
