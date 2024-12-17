package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

public class ShootArrow {

    public static void shootArrow(ServerPlayer player) {

        ServerLevel level = player.serverLevel().getLevel();
        Vec3 randomPosition = getRandomPositionAroundHead(player);

        Arrow arrow = new Arrow(level,randomPosition.x, randomPosition.y, randomPosition.z, new ItemStack(Items.ARROW), null);

        Vec3 lookVec = player.getLookAngle();
        arrow.shoot(lookVec.x, lookVec.y, lookVec.z, 2.0f, 0.0f);
        arrow.setOwner(player);
        arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
        level.addFreshEntity(arrow);
        level.playSound(null, player.getOnPos(), SoundEvents.ARROW_SHOOT, SoundSource.MASTER);

    }

    private static Vec3 getRandomPositionAroundHead(ServerPlayer player) {
        Random random = new Random();

        Vec3 headPosition = player.getEyePosition();

        double offsetX = (random.nextDouble() - 0.5) * 2.0;
        double offsetY = random.nextDouble() * 1.5;
        double offsetZ = (random.nextDouble() - 0.5) * 2.0;

        return headPosition.add(offsetX, offsetY, offsetZ);
    }

}
