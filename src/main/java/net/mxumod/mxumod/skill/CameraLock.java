package net.mxumod.mxumod.skill;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class CameraLock {
    public static void cameraLockOn(Player player) {
        player.lookAt();
    }

    private static Vec3 entityInView(Player player, double distance) {
        player.pick(distance, 0, true );
    }
}
