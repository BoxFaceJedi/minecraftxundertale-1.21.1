package net.team.mxumod.minecraftxundertale.util;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Matrix4f;
import org.joml.Vector4f;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.awt.SystemColor.window;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();
    private static LivingEntity Target = null;
    private static final float SMOOTH_FACTOR = 0.2f; // Controls smoothness of rotation (0.1-0.3 recommended).

    public static void enableEvent() {
        MinecraftForge.EVENT_BUS.register(CameraLock.class);
    }

    public static void disableEvent() {
        MinecraftForge.EVENT_BUS.unregister(CameraLock.class);
        Target = null;
    }

    public static void toggleCameraLock(LocalPlayer player) {
        Window window = mc.getWindow();

        float screenWidth = window.getGuiScaledWidth();
        float screenHeight = (float) window.getGuiScaledHeight();

        float closiest = 9999;
        LivingEntity newTarget = null;
        Vec2 screenCenter = new Vec2(screenWidth/2, screenHeight/2);
        List<LivingEntity> tmp_entities = getEntitiesOnScreen(Minecraft.getInstance().player, 20);
        if (tmp_entities.isEmpty()) {
            return;
        }
        for (LivingEntity entity : tmp_entities) {
            Vec3 entityPos = entity.position().add(0, entity.getBbHeight() / 2, 0); // 取得生物中心點
            Vec2 screenPos = worldToScreen(entityPos);
            if (screenPos != null) {
                float tmp_d = screenPos.distanceToSqr(screenCenter);
                if (tmp_d < closiest) {
                    closiest = tmp_d;
                    newTarget = entity;
                }
            }
        }

        if (newTarget != null) {
            Target = (newTarget == Target) ? null : newTarget;
        } else {
            Target = null;
        }
    }

    public static Matrix4f getViewMatrix() {
        Camera camera = mc.gameRenderer.getMainCamera();
        Matrix4f viewMatrix = new Matrix4f();

        // 旋轉與位移變換
        viewMatrix.identity()
                .rotateX((float) Math.toRadians(camera.getXRot()))
                .rotateY((float) Math.toRadians(camera.getYRot()))
                .translate((float) -camera.getPosition().x, (float) -camera.getPosition().y, (float) -camera.getPosition().z);

        return viewMatrix;
    }


    @SubscribeEvent
    public static void livingTargetDetect(LivingDeathEvent event) {
        if (event.getEntity() == Target) {
            Target = null;
        }
    }

    @SubscribeEvent
    public static void onClientTick(RenderLivingEvent.Pre<?, ?> event) {
        if (Target != null && (!Target.isAlive() || mc.player.distanceTo(Target) > 25)) {
            Target = null;
        }
        if (mc.player != null && Target != null) {
            smoothLookAt(mc.player, Target);
        }
    }

    private static void smoothLookAt(LocalPlayer player, LivingEntity target) {
        Vec3 targetPos = target.getEyePosition();
        Vec3 playerPos = player.getEyePosition();

        double dx = targetPos.x - playerPos.x;
        double dy = targetPos.y - playerPos.y;
        double dz = targetPos.z - playerPos.z;

        double distanceXZ = Math.sqrt(dx * dx + dz * dz);
        float targetYaw = (float) Math.toDegrees(Math.atan2(dz, dx)) - 90.0f;
        float targetPitch = (float) -Math.toDegrees(Math.atan2(dy, distanceXZ));

        // Smoothly interpolate yaw and pitch
        float distanceFactor = Math.min(0.4f, Math.abs(targetYaw - player.getYRot()) * 0.005f);
        float smoothFactor = Math.max(SMOOTH_FACTOR, distanceFactor);
        float newYaw = interpolateAngle(player.getYRot(), targetYaw, smoothFactor);
        float newPitch = interpolateAngle(player.getXRot(), targetPitch, smoothFactor);

        player.setYRot(newYaw);
        player.setXRot(newPitch);
    }

    private static float interpolateAngle(float current, float target, float factor) {
        float delta = normalizeAngle(target - current);
        return current + delta * factor;
    }

    private static float normalizeAngle(float angle) {
        angle = angle % 360.0f;
        if (angle > 180.0f) angle -= 360.0f;
        if (angle < -180.0f) angle += 360.0f;
        return angle;
    }

    private static AABB scaleAABB(AABB boundingBox, double scale) {
        Vec3 center = boundingBox.getCenter();

        return new AABB(
                center.x - boundingBox.getXsize() * scale / 2,
                center.y - boundingBox.getYsize() * scale / 2,
                center.z - boundingBox.getZsize() * scale / 2,
                center.x + boundingBox.getXsize() * scale / 2,
                center.y + boundingBox.getYsize() * scale / 2,
                center.z + boundingBox.getZsize() * scale / 2
        );
    }

    public static List<LivingEntity> getEntitiesOnScreen(LocalPlayer player, double range) {
        Camera camera = Minecraft.getInstance().gameRenderer.getMainCamera();
        Vec3 cameraPos = camera.getPosition();

        // 取得投影與視圖矩陣
        float fov = Minecraft.getInstance().options.fov().get().floatValue();
        Matrix4f projectionMatrix = Minecraft.getInstance().gameRenderer.getProjectionMatrix(fov);
        PoseStack poseStack = new PoseStack();
        poseStack.pushPose();
        poseStack.translate(-cameraPos.x, -cameraPos.y, -cameraPos.z);
        Matrix4f viewMatrix = getViewMatrix();

        // 建立視錐體
        Frustum frustum = new Frustum(viewMatrix, projectionMatrix);
        frustum.prepare(cameraPos.x, cameraPos.y, cameraPos.z);

        // 設定搜尋範圍 (AABB)
        AABB boundingBox = new AABB(cameraPos.add(-range, -range, -range), cameraPos.add(range, range, range));

        return Minecraft.getInstance().level.getEntitiesOfClass(LivingEntity.class, boundingBox)
                .stream()
                .filter(entity -> frustum.isVisible(entity.getBoundingBox()) && player.distanceTo(entity) < range)
                .collect(Collectors.toList());
    }

    public static Vec2 worldToScreen(Vec3 worldPos) {
        Minecraft mc = Minecraft.getInstance();
        Window window = mc.getWindow();

        float fov = mc.options.fov().get().floatValue();
        Matrix4f projectionMatrix = mc.gameRenderer.getProjectionMatrix(fov);
        Matrix4f viewMatrix = getViewMatrix();

        Vector4f vec = new Vector4f((float) worldPos.x, (float) worldPos.y, (float) worldPos.z, 1.0f);
        viewMatrix.transform(vec);
        projectionMatrix.transform(vec);

        if (vec.w() == 0) return null; // 防止除以0

        float x = (vec.x() / vec.w() * 0.5f + 0.5f) * window.getGuiScaledWidth();
        float y = (1 - (vec.y() / vec.w() * 0.5f + 0.5f)) * window.getGuiScaledHeight();

        return new Vec2(x, y);
    }
}