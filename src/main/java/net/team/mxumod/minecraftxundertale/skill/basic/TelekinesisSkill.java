package net.team.mxumod.minecraftxundertale.skill.basic;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TelekinesisSkill extends Skill {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(999);

    public TelekinesisSkill()  {
        super("Telekinesis", 10, 500);
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
                scheduler.scheduleAtFixedRate(() -> {}, 0, 33, TimeUnit.MILLISECONDS);

            }
        }
    }
}
