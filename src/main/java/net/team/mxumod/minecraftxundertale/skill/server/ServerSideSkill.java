package net.team.mxumod.minecraftxundertale.skill.server;

import net.team.mxumod.minecraftxundertale.skill.Skill;

public abstract class ServerSideSkill extends Skill {
    public ServerSideSkill(String name, int manaCost, int cooldown) {
        super(name, manaCost, cooldown);
    }


}
