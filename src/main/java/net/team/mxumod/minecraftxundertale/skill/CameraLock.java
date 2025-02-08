package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.*;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.List;
import java.util.Optional;

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
        LivingEntity newTarget = getEntityOnMouseIcon(player, 20);

        if (newTarget == Target) {
            Target = null;
        } else {
            Target = newTarget;
        }
    }

    @SubscribeEvent
    public static void livingTargetDetect(LivingDeathEvent event) {
        if (event.getEntity() == Target) {
            Target = null;
        }
    }

    @SubscribeEvent
    public static void onClientTick(RenderLivingEvent.Pre<?, ?> event) {
        if (Target == null) {
            return;
        }

        if (mc.player != null) {
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
        float newYaw = interpolateAngle(player.getYRot(), targetYaw, SMOOTH_FACTOR);
        float newPitch = interpolateAngle(player.getXRot(), targetPitch, SMOOTH_FACTOR);

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

    private static LivingEntity getEntityOnMouseIcon(LocalPlayer player, double distance) {
        Camera camera = mc.gameRenderer.getMainCamera();
        Vec3 lookVector = new Vec3(camera.getLookVector().x(), camera.getLookVector().y(), camera.getLookVector().z()).normalize();
        Vec3 cameraPos = camera.getPosition();

        AABB rangeBox = new AABB(
                cameraPos.x, cameraPos.y, cameraPos.z,
                cameraPos.x + lookVector.x * distance,
                cameraPos.y + lookVector.y * distance,
                cameraPos.z + lookVector.z * distance
        );

        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, rangeBox).stream()
                .filter(entity -> entity != player)
                .toList();

        double nearestDistance = distance;
        LivingEntity nearestEntity = null;

        for (LivingEntity entity : entities) {
            if (entity.isDeadOrDying()) continue;
            AABB entityBox = scaleAABB(entity.getBoundingBox(), 7.5);
            Optional<Vec3> result = entityBox.clip(cameraPos, cameraPos.add(lookVector.scale(distance)));

            if (result.isPresent() && result.get().distanceTo(cameraPos) < nearestDistance) {
                nearestEntity = entity;
                nearestDistance = result.get().distanceTo(cameraPos);
            }
        }
        return nearestEntity;
    }
}