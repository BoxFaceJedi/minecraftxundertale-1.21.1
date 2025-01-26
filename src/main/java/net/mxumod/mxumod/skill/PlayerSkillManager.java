package net.mxumod.mxumod.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.mxumod.mxumod.skill.dodge.SideStepSkill;

import java.util.ArrayList;
import java.util.List;

public class PlayerSkillManager {

    private final List<Skill> skills = new ArrayList<>();

    public PlayerSkillManager() {
        // Add all skills here
        skills.add(new SideStepSkill());
    }

    public void activateSkill(String skillName, Player player, int currentMana) {
        for (Skill skill : skills) {
            if (skill.getName().equals(skillName)) {
                if (skill.canActivate(player, currentMana)) {
                    skill.activate(player);
                }
                return;
            }
        }
        player.displayClientMessage(Component.literal("Skill not found!"), true);
    }
}
