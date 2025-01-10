package net.mxumod.mxumod.skill;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();
    private static LivingEntity Target=null;
    private static boolean LockingOn=false;


    public void enableCameraLock() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void disableCameraLock() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public static void cameraLockOn(LocalPlayer player) {
        Target = getEntityonMouseIcon(player, 20);
        if (Target == null) {
            System.out.println("Nothing selected");
        }else {
            System.out.println(Target.getName().getString());
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
