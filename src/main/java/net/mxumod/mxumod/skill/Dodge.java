package net.mxumod.mxumod.skill;

import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;

import java.util.concurrent.atomic.AtomicBoolean;
@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class Dodge {
    private static final AtomicBoolean isMoving = new AtomicBoolean(false);
    private static Thread movementThread;

    public static void dodge(Player player, double speed) {

        player.setDeltaMovement(player.getDeltaMovement().multiply(speed, 1, speed));

    }

    public static Vec3 getTargetPos(Player player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.options.keyUp.isDown()) {
            double offsetX = -Math.sin(playerYaw) * distance;
            double offsetZ = Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(offsetX, y,  offsetZ);
        } else if (minecraft.options.keyRight.isDown() && minecraft.options.keyLeft.isDown()) {
            double offsetX = Math.sin(playerYaw) * distance;
            double offsetZ = -Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(offsetX, y, offsetZ);
        } else if (minecraft.options.keyLeft.isDown()) {
            double offsetX = Math.cos(playerYaw) * distance;
            double offsetZ = Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(offsetX, y, offsetZ);
        } else if (minecraft.options.keyRight.isDown()) {
            double offsetX = -Math.cos(playerYaw) * distance;
            double offsetZ = -Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(offsetX, y, offsetZ);
        } else {
            double offsetX = Math.sin(playerYaw) * distance;
            double offsetZ = -Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(offsetX, y, offsetZ);
        }
    }
    @SubscribeEvent
    public static void onLivingEntityAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() != null) {
            if (isMoving.get()) {
                event.setCanceled(true);
                player.level().playSound(player, player.blockPosition(), SoundEvents.WIND_CHARGE_THROW, SoundSource.MASTER);
            }
        }
    }
}
