package net.team.mxumod.minecraftxundertale.skill.basic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class TelekinesisSkill extends Skill {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public TelekinesisSkill()  {
        super("Telekinesis", 100, 1000);
    }

    @Override
    public void executeSkill(ServerPlayer player, CompoundTag data) {
        final Entity target = player.level().getEntity(data.getInt("TargetId"));
        if (target instanceof LivingEntity livingTarget) {
            if (data.getString("Key").equals("W"))
                livingTarget.setDeltaMovement(player.getLookAngle().multiply(2, 2, 2));
            else if (data.getString("Key").equals("D"))
                livingTarget.setDeltaMovement(getPlayerRightVector(player).multiply(2, 2, 2));
            else if (data.getString("Key").equals("A"))
                livingTarget.setDeltaMovement(getPlayerRightVector(player).multiply(-2, -2, -2));
            else if (data.getString("Key").equals("S")) {
                player.setNoGravity(true);
                player.setInvulnerable(true);
                player.noPhysics = true;
                player.setDeltaMovement(Vec3.ZERO);

                final int[] count = {0};
                final ScheduledFuture<?>[] futureHandler = new ScheduledFuture[1];
                futureHandler[0] = scheduler.scheduleAtFixedRate(() -> {

                    if (count[0] >= 30) {
                        player.setNoGravity(false);
                        player.setInvulnerable(false);
                        player.noPhysics = false;
                        futureHandler[0].cancel(false);
                        return;
                    }
                    final Vec3 newPos = livingTarget.position().lerp(getPositionInFrontOfPlayer(player, 1.5), (double) count[0] / 30);
                    livingTarget.teleportTo(newPos.x(), newPos.y(), newPos.z());
                    count[0] += 1;
                }, 0, 33, TimeUnit.MILLISECONDS);
            }
        }
    }
}
