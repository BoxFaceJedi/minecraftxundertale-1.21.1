package net.mxumod.mxumod.skill;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.joml.Quaterniond;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;
import java.util.stream.Collectors;

public class CameraLock {
    private static final Minecraft mc = Minecraft.getInstance();

    public static void cameraLockOn(LocalPlayer player) {
        List<LivingEntity> livingEntities = entityInView(player, 20);

        for (LivingEntity tmp_Entity: livingEntities) {
            System.out.println(tmp_Entity.getName().getString());
        }
    }

    private static List<LivingEntity> entityInView(LocalPlayer player, double distance) {
        Camera camera = mc.gameRenderer.getMainCamera();
        float fov = Minecraft.getInstance().options.fov().get().floatValue();

        Vec3 cameraPos = camera.getPosition();
        Matrix4f viewMatrix = new Matrix4f().lookAt(
                camera.getPosition().toVector3f(),  // 攝影機位置
                camera.getLookVector().add(camera.getPosition().toVector3f()), // 目標點
                camera.getUpVector() // 上向量
        );
        Matrix4f projectionMatrix = mc.gameRenderer.getProjectionMatrix(fov);

        Frustum frustum = new Frustum(viewMatrix, projectionMatrix);

        frustum.prepare(cameraPos.x, cameraPos.y, cameraPos.z);

        double px = player.getX();
        double py = player.getY();
        double pz = player.getZ();

        AABB tmp_Box = new AABB(new Vec3(px + distance, py + distance, pz + distance), new Vec3(px - distance, py - distance, pz - distance));


        return mc.level.getEntitiesOfClass(LivingEntity.class, player.getBoundingBox().inflate(distance)).stream()
                .filter(entity -> frustum.isVisible(entity.getBoundingBox()))
                .filter(entity -> entity != player)
                .collect(Collectors.toList());
    }
}
