package net.mxumod.mxumod.skill.basic;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.entities.bone.BoneProjectileEntity;
import net.mxumod.mxumod.skill.Skill;

import java.util.Random;
@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID)
public class BoneBarrageSKill extends Skill<ServerPlayer> {

    public BoneBarrageSKill() {
        super("Bone Barrage", 2, 4);
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

        BoneProjectileEntity bone = new BoneProjectileEntity(level,randomPosition.x, randomPosition.y, randomPosition.z, new ItemStack(Items.AIR), null);

        Vec3 lookVec = player.getLookAngle();
        bone.shoot(lookVec.x, lookVec.y, lookVec.z, 3.5f, 0.0f);
        bone.setOwner(player);
        level.addFreshEntity(bone);
        level.playSound(null, bone.getX(), bone.getY(), bone.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.MASTER);


    }
}
