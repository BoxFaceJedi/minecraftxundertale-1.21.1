package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.skill.basic.BoneBarrageSKill;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;
import net.team.mxumod.minecraftxundertale.skill.dodge.SideStepSkill;
import net.team.mxumod.minecraftxundertale.skill.special.BoneSpikeSkill;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Minecraftxundertale.MODID, value = Dist.CLIENT)
public class PlayerSkillManager {

    private static final List<Skill> skills = new ArrayList<>();
    private static int currentMana = 200; // Shared mana pool for simplicity
    private static final int TOTAL_MANA = 200;

    public PlayerSkillManager() {
        // Add all skills here
        skills.add(new SideStepSkill());
        skills.add(new BoneSpikeSkill());
        skills.add(new BoneWallSkill());
        skills.add(new BoneBarrageSKill());
    }

    public static int getCurrentMana() {
        return currentMana;
    }

    public static void reduceMana(int amount) {
        currentMana = Math.max(0, currentMana - amount); // Prevent negative mana
    }

    public void activateSkill(String skillName, Player player) {
        for (Skill skill : skills) {
            if (skill.getName().equals(skillName)) {
                if (skill.canActivate()) {
                    skill.activate(player);
                }
                return;
            }
        }
        player.displayClientMessage(Component.literal("Skill not found!"), true);
    }

    @SubscribeEvent
    public static void onTick(ClientTickEvent event) {
        if (currentMana < TOTAL_MANA) {
            currentMana++;
        }

        // Tick cooldown for all skills
        for (Skill skill : skills) {
            skill.tickCoolDown();
        }
    }
}