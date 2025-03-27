package net.team.mxumod.minecraftxundertale.skill.client;

import net.minecraft.world.entity.player.Player;
import net.team.mxumod.minecraftxundertale.skill.Skill;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;

public class ClientSideSkill extends Skill {
    public ClientSideSkill(String name, int manaCost, int cooldown) {

        super(name, manaCost, cooldown);
    }


    public boolean canActivate() {
        return PlayerSkillManager.getCurrentMana() >= manaCost && currentCoolDown >= cooldown;
    }

    @Override
    protected void executeSkill(Player player) {

    }
}
