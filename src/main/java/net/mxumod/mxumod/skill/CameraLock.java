package net.mxumod.mxumod.skill;

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
}
