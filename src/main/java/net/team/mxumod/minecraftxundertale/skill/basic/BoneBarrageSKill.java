package net.team.mxumod.minecraftxundertale.skill.basic;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.entities.bone.BoneProjectileEntity;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.Random;

public class BoneBarrageSKill extends Skill<ServerPlayer> {

    public BoneBarrageSKill() {
        super("Bone Barrage", 1, 5);
    }

    public static void shootArrow(ServerPlayer player) {

    }

    private static Vec3 getRandomPositionAroundHead(ServerPlayer player) {
        Random random = new Random();

        Vec3 headPosition = player.getEyePosition();

        double offsetX = (random.nextDouble() - 0.5) * 2.0;
        double offsetY = random.nextDouble() * 1.5;
        double offsetZ = (random.nextDouble() - 0.5) * 2.0;

        return headPosition.add(offsetX, offsetY, offsetZ);
    }

    @Override
    public void activate(Player player) {
        ServerLevel level = (ServerLevel) player.level();
        Vec3 randomPosition = getRandomPositionAroundHead((ServerPlayer) player);

        BoneProjectileEntity bone = new BoneProjectileEntity(level,randomPosition.x, randomPosition.y, randomPosition.z);

        Vec3 lookVec = player.getLookAngle();
        bone.shoot(lookVec.x, lookVec.y, lookVec.z, 3.5f, 0.0f);
        bone.setOwner(player);
        //bone.pickup = AbstractArrow.Pickup.DISALLOWED;
        level.addFreshEntity(bone);
        bone.playSound(SoundEvents.ARROW_SHOOT, 1.0f, 1.0f);
    }
}
