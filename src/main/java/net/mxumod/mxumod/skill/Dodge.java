package net.mxumod.mxumod.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class Dodge {
    public  static  void dodge(Player player) {
        player.moveTo(getDodgePos(player, 1));
        System.out.println(player.getYRot());
    }

    public static Vec3 getDodgePos(Player player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());
        if (Minecraft.getInstance().options.keyDown.isDown()) {
            double offsetX = Math.sin(playerYaw) * distance;
            double offsetZ = -Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else if (Minecraft.getInstance().options.keyLeft.isDown()) {
            double offsetX = Math.cos(playerYaw) * distance;
            double offsetZ = Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else if (Minecraft.getInstance().options.keyRight.isDown()) {
            double offsetX = -Math.cos(playerYaw) * distance;
            double offsetZ = -Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else {
            double offsetX = -Math.sin(playerYaw) * distance;
            double offsetZ = Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        }
    }
}
