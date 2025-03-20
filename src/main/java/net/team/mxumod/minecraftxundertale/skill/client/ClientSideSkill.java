package net.team.mxumod.minecraftxundertale.skill.client;

import net.team.mxumod.minecraftxundertale.skill.BasicSkill;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;

public class ClientSideSkill extends BasicSkill {
    public ClientSideSkill(String name, int manaCost, int cooldown) {
        super(name, manaCost, cooldown);
    }

    public boolean canActivate() {
        return PlayerSkillManager.getCurrentMana() >= manaCost && currentCoolDown >= cooldown;
    }
}
