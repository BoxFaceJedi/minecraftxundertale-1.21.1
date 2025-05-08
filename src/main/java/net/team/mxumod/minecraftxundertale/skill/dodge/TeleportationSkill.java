package net.team.mxumod.minecraftxundertale.skill.dodge;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.team.mxumod.minecraftxundertale.skill.Skill;

public class TeleportationSkill extends Skill {
    public TeleportationSkill(String name, int manaCost, int cooldown) {
        super("Teleportation Skill", 25, 3000);
    }

    @Override
    public void executeSkill(ServerPlayer player, Object data) {
        player.moveTo(player.position().add(player.getDeltaMovement().multiply(5.0, 5.0, 5.0)));
    }
}
