package net.mxumod.mxumod.skill;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class CameraLock {
    public static void cameraLockOn(Player player) {player.lookAt(EntityAnchorArgument.Anchor.EYES, entityInView(player, 20));
    }

    private static Vec3 entityInView(Player player, double distance) {
        HitResult hitResult = player.pick(distance, 0, true );

        if (hitResult instanceof EntityHitResult entityHitResult) {
            return entityHitResult.getEntity().position();
        }else {
            return null;
        }
    }
}
