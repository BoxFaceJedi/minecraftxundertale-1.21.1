package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.libraries.ObservableNumber;
import net.team.mxumod.minecraftxundertale.libraries.ObservableValue;
import net.team.mxumod.minecraftxundertale.skill.basic.BoneBarrageSKill;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;
import net.team.mxumod.minecraftxundertale.skill.dodge.SideStepSkill;
import net.team.mxumod.minecraftxundertale.skill.special.BoneSpikeSkill;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID, value = Dist.CLIENT)
public class PlayerSkillManager {

    private static final List<Skill> skills = new ArrayList<>();

    private static int currentMana = new ObservableNumber<>(200); // Shared mana pool for simplicity
    private static int TOTAL_MANA = new ObservableNumber<>(200);

    private static Integer lastActionId = null;

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
    public static int getMaxMana() { return TOTAL_MANA;}

    public static void setCurrentMana(int a) {currentMana = a;}
    public static void setMaxMana(int a) {TOTAL_MANA = a;}

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
}