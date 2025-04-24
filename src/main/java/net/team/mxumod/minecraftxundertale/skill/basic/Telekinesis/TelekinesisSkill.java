package net.team.mxumod.minecraftxundertale.skill.basic.Telekinesis;

import net.minecraft.server.level.ServerPlayer;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class TelekinesisSkill extends Skill {
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(999);

    public TelekinesisSkill()  {
        super("Telekinesis", 10, 500);
    }

    @Override
    public void executeSkill(ServerPlayer player, Object data) {
        if (data instanceof TelekinesisData tmp_Data) {
            if (tmp_Data.key.equals("W")) {
                tmp_Data.target.setDeltaMovement(player.);
            }
        }
    }
}
