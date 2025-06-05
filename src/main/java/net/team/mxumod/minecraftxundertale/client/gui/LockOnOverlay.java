package net.team.mxumod.minecraftxundertale.client.gui;

import com.mojang.blaze3d.platform.Window;
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
import org.joml.Vector3f;
import org.joml.Vector4f;
import team.lodestar.lodestone.systems.item.tools.magic.MagicAxeItem;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT)
public class LockOnOverlay {
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void onRenderOverlay(CustomizeGuiOverlayEvent event) {
        if (mc.player == null || CameraLock.getTarget() == null) return;
        System.out.println("Locked!");

        Vec3 targetPos = new Vec3(CameraLock.getTarget().getEyePosition().x, CameraLock.getTarget().getEyePosition().y - 1, CameraLock.getTarget().getEyePosition().z);
        Vec2 screenPos = projectToScreen(targetPos);

        GuiGraphics guiGraphics = event.getGuiGraphics();
        if (screenPos != null) {
            guiGraphics.blit(new ResourceLocation(MinecraftxUndertaleMod.MOD_ID,
                            "textures/effects/heart.png"),
                    (int) screenPos.x - 8, (int) screenPos.y - 8,
                    0, 0, 16, 16, 16, 16
            );
        }
    }

    public static Vec2 projectToScreen(Vec3 worldPos) {
        Camera camera = mc.gameRenderer.getMainCamera();
        Vector3f camPos = camera.getPosition().toVector3f();

        Vector4f Pos = new Vector4f(camPos, 1.0f);
        Pos.mul(createViewMatrix(camPos, worldPos.toVector3f(), camera.getUpVector()));
        Pos.mul(mc.gameRenderer.getProjectionMatrix(mc.options.fov().get().floatValue()));

        if (Pos.w <= 0.0f) {
            return null;
        }

        Pos.div(Pos.w);

        Window window = mc.getWindow();
        float screenX = (Pos.x * 0.5f + 0.5f) * window.getGuiScaledWidth();
        float screenY = (1.0f - (Pos.y * 0.5f + 0.5f)) * window.getGuiScaledHeight();
        System.out.println(new Vec2(screenX, screenY));
        return new Vec2(screenX, screenY);
    }

    public static Matrix4f createViewMatrix(Vector3f eye, Vector3f center, Vector3f up) {
        Vector3f f = new Vector3f(center).sub(eye).normalize(); // forward
        Vector3f s = new Vector3f(f).cross(up).normalize();      // right
        Vector3f u = new Vector3f(s).cross(f);                   // recalculated up

        Matrix4f view = new Matrix4f();
        view.m00(s.x); view.m10(s.y); view.m20(s.z); view.m30(-s.dot(eye));
        view.m01(u.x); view.m11(u.y); view.m21(u.z); view.m31(-u.dot(eye));
        view.m02(-f.x);view.m12(-f.y);view.m22(-f.z);view.m32(f.dot(eye));
        view.m03(0);   view.m13(0);   view.m23(0);   view.m33(1);

        return view;
    }
}
