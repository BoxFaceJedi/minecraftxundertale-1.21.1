package net.mxumod.mxumod.skill;

import com.ibm.icu.impl.breakiter.DictionaryBreakEngine;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.client.event.sound.PlaySoundSourceEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;
import org.w3c.dom.Entity;

import java.util.concurrent.atomic.AtomicBoolean;
@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class Dodge {
    private static final AtomicBoolean isMoving = new AtomicBoolean(false);
    private static Thread movementThread;

    public static void dodge(Player player, double speed) {
        Vec3 targetPos = getTargetPos(player, 2);
        if (isMoving.get()) {
            stopSmoothMovement();
        }

        isMoving.set(true);

        movementThread = new Thread(() -> {

            Vec3 currentPosition = player.position();

            while (isMoving.get() && currentPosition.distanceTo(targetPos) > 0.1) {
                try {
                    // Calculate direction and movement step
                    Vec3 direction = targetPos.subtract(currentPosition).normalize();
                    Vec3 movementStep = direction.scale(speed);

                    // Update player position
                    currentPosition = currentPosition.add(movementStep);
                    double x = currentPosition.x;
                    double y = currentPosition.y;
                    double z = currentPosition.z;

                    // Run on the main thread to avoid threading issues
                    Minecraft.getInstance().execute(() -> player.moveTo(x, y, z));

                    // Sleep for smooth transitions
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            // Stop movement
            isMoving.set(false);
        });

        movementThread.start();
    }

    public static void stopSmoothMovement() {
        isMoving.set(false);
        if (movementThread != null && movementThread.isAlive()) {
            movementThread.interrupt();
        }
    }

    public static Vec3 getTargetPos(Player player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());
        Minecraft minecraft = Minecraft.getInstance();

        if (minecraft.options.keyUp.isDown()) {
            double offsetX = -Math.sin(playerYaw) * distance;
            double offsetZ = Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else if (minecraft.options.keyLeft.isDown()) {
            double offsetX = Math.cos(playerYaw) * distance;
            double offsetZ = Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else if (minecraft.options.keyRight.isDown()) {
            double offsetX = -Math.cos(playerYaw) * distance;
            double offsetZ = -Math.sin(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        } else {
            double offsetX = Math.sin(playerYaw) * distance;
            double offsetZ = -Math.cos(playerYaw) * distance;

            double y = player.getY();

            return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
        }
    }
    @SubscribeEvent
    public static void onPlayerHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player && event.getSource().getEntity() != null) {
            if (isMoving.get()) {
                event.setCanceled(true);
            }
        }
    }
}
