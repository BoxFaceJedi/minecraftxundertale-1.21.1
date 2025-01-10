package net.mxumod.mxumod.skill;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import java.util.List;
import java.util.Optional;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();
    private static LivingEntity Target = null;

    public static void enableCameraLock() {
        MinecraftForge.EVENT_BUS.register(CameraLock.class);
    }

    public static void disableCameraLock() {
        MinecraftForge.EVENT_BUS.unregister(CameraLock.class);
    }


    public static LivingEntity getTarget() {
        return Target;
    }

    public static void cameraLockOn(LocalPlayer player) {
        LivingEntity tmp_Target = getEntityonMouseIcon(player, 20);

        if (tmp_Target == Target) {
            Target = null;
        }else {
            Target = tmp_Target;
        }
    }

    @SubscribeEvent
    public static void TargetDetectEvent(LivingDeathEvent event) {
        if (event.getEntity() == Target) {
            Target = null;
        }
    }

    @SubscribeEvent
    public static void LockingOn(TickEvent.ClientTickEvent event) {
        if (Target != null) {
            assert mc.player != null;
            mc.player.lookAt(EntityAnchorArgument.Anchor.EYES, Target.getEyePosition());
        }
    }

    private static AABB scaleAABB(AABB BoundingBox, double Scale) {
        Vec3 center = BoundingBox.getCenter();
        AABB ScaledBox = new AABB(
                center.x - BoundingBox.getXsize()*Scale/2,
                center.y - BoundingBox.getYsize()*Scale/2,
                center.z - BoundingBox.getZsize()*Scale/2,
                center.x + BoundingBox.getXsize()*Scale/2,
                center.y + BoundingBox.getYsize()*Scale/2,
                center.z + BoundingBox.getZsize()*Scale/2
        );


        return ScaledBox;
    }

    private static LivingEntity getEntityonMouseIcon (LocalPlayer player, double distance) {
        Camera camera = mc.gameRenderer.getMainCamera();
        Vec3 LookVector = new Vec3(camera.getLookVector()).normalize();

        Vec3 CameraPos = camera.getPosition();
        double  x = camera.getPosition().x();
        double y = player.getY();
        double z = player.getZ();

        AABB RangeBox = new AABB(CameraPos, CameraPos.add(LookVector.scale(distance)));

        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, RangeBox);

        double nearestDistance = distance;
        LivingEntity nearestEntity = null;

        for (LivingEntity entity : entities) {
            AABB tmp_EntityBox = scaleAABB(entity.getBoundingBox(), 7.5);
            Optional<Vec3> result = tmp_EntityBox.clip(CameraPos, CameraPos.add(LookVector.scale(distance)));

            if (!result.isEmpty()) {
                if (result.get().distanceTo(CameraPos) < nearestDistance) {
                    nearestEntity = entity;
                    nearestDistance = result.get().distanceTo(CameraPos);
                }
            }
        }
        return nearestEntity;
    }
}
