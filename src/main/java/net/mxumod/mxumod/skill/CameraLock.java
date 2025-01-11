package net.mxumod.mxumod.skill;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mxumod.mxumod.libraries.ObservableValue;

import java.util.List;
import java.util.Optional;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();
    private static ObservableValue<LivingEntity> Target = new ObservableValue<>(null);


    public static Object getTarget() {
        return Target.getValue();
    }

    public static void enabledEvent() {
        MinecraftForge.EVENT_BUS.register(CameraLock.class);
    }

    public static void disabledEvent() {
        MinecraftForge.EVENT_BUS.unregister(CameraLock.class);
    }

    public static void cameraLockOn(LocalPlayer player) {
        LivingEntity tmp_Target = getEntityOnMouseIcon(player, 20);

        if (tmp_Target == Target.getValue()) {
            Target.setValue(null);
        }else {
            Target.setValue(tmp_Target);
        }
    }

    @SubscribeEvent
    public static void livingTargetDetect(LivingDeathEvent event) {
        if (event.getEntity() == Target.getValue()) {
            Target.setValue(null);
        }else {
            System.out.println(event.getEntity().getName().getString());
        }
    }

    public static class ThreadOfLockingOn extends Thread {
        @Override
        public void run() {
            while (Target.getValue() != null) {
                assert mc.player != null;
                mc.player.lookAt(EntityAnchorArgument.Anchor.EYES, Target.getValue().getEyePosition());
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        static ThreadOfLockingOn LockingThread = null;

        private static void setLockingThread(LivingEntity oldValue, LivingEntity newValue) {
            if (newValue != null) {
                LockingThread = new ThreadOfLockingOn();
                LockingThread.start();
            }else {
                LockingThread = null;
            }
        }

        public static void main() {
            Target.addChangeListener(ThreadOfLockingOn::setLockingThread);
        }
    }

    private static AABB scaleAABB(AABB BoundingBox, double Scale) {
        Vec3 center = BoundingBox.getCenter();

        return new AABB(
                center.x - BoundingBox.getXsize()*Scale/2,
                center.y - BoundingBox.getYsize()*Scale/2,
                center.z - BoundingBox.getZsize()*Scale/2,
                center.x + BoundingBox.getXsize()*Scale/2,
                center.y + BoundingBox.getYsize()*Scale/2,
                center.z + BoundingBox.getZsize()*Scale/2
        );
    }

    private static LivingEntity getEntityOnMouseIcon(LocalPlayer player, double distance) {
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
            if (entity.isDeadOrDying()) continue;
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
