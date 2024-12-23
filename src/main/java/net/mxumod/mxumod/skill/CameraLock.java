package net.mxumod.mxumod.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CameraLock {
    public static void cameraLockOn(Player player) {player.lookAt(EntityAnchorArgument.Anchor.EYES, entityInView(player, 48));
    }

    private static Vec3 entityInView(Player player, double distance) {
        HitResult hitResult = player.pick(distance, 0, false);

        if (hitResult.getType() == (HitResult.Type.ENTITY )) {
            return hitResult.getLocation();
        }else {
            return player.position();
        }
    }
    private static final Minecraft mc = Minecraft.getInstance();

    public static Vec3 getPlayerLookDirection() {
        if (mc.player == null) {
            return null; // 玩家对象不存在
        }

        // 获取玩家的旋转角度
        float yaw = mc.player.getYRot();  // 水平角度（Yaw）
        float pitch = mc.player.getXRot(); // 垂直角度（Pitch）

        // 将角度转换为弧度
        double yawRad = Math.toRadians(-yaw);   // 注意：Minecraft 的 yaw 是逆时针为正
        double pitchRad = Math.toRadians(-pitch); // Pitch 向下为正，向上为负

        // 计算方向向量
        double x = Math.cos(yawRad) * Math.cos(pitchRad);
        double y = Math.sin(pitchRad);
        double z = Math.sin(yawRad) * Math.cos(pitchRad);

        return new Vec3(x, y, z); // 返回方向向量
    }
}
