package net.mxumod.mxumod.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

import java.util.ArrayList;
import java.util.List;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();
    private static Entity Target;

    public static void cameraLockOn(LocalPlayer player) {

    }

    private static void getEntityonMouseIcon (LocalPlayer player, double distance) {
        HitResult result = player.pick(distance, 0.0f, false);

        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        AABB RangeBox = new AABB(x - distance, y - distance, z - distance, x + distance, y + distance, z + distance);

        List<LivingEntity> entities = player.level().getEntitiesOfClass(LivingEntity.class, RangeBox);
        List<AABB> tmp_EntitiesBB = new ArrayList<>();

        for (LivingEntity entity : entities) {
            AABB tmp_AABB = entity.getBoundingBox();
            entity.setBoundingBox(tmp_AABB.inflate(5.0));
            tmp_EntitiesBB.add(tmp_AABB);
        }

        if (result.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHitResult = (EntityHitResult) result;
            Target = entityHitResult.getEntity();
        }
        for (AABB OBB : tmp_EntitiesBB) {

        }

    }
}
