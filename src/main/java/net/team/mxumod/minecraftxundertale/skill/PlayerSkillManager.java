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
    private static final ArrayList<Integer> WaitingList = new ArrayList<>();

    private final static ObservableNumber<Integer> currentMana = new ObservableNumber<>(200); // Shared mana pool for simplicity
    private final static ObservableNumber<Integer> TOTAL_MANA = new ObservableNumber<>(200);

    private static Integer lastActionId = null;

    public PlayerSkillManager() {
        // Add all skills here
        skills.add(new SideStepSkill());
        skills.add(new BoneSpikeSkill());
        skills.add(new BoneWallSkill());
        skills.add(new BoneBarrageSKill());

        currentMana.addChangeListener(JustSaveTheLogicOfMagic::ManaHandler);
    }

    public static int getCurrentMana() {
        return currentMana.getValue();
    }
    public static int getMaxMana() { return TOTAL_MANA.getValue();}

    public static void reduceMana(int amount) {
        currentMana.setValue(Math.max(0, currentMana.getValue() - amount)); // Prevent negative mana
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

    public static void startRecover(Integer Id) {
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                while (currentMana.getValue() < TOTAL_MANA.getValue() && lastActionId == Id) {
                    currentMana.setValue(currentMana.getValue() + 1);
                    Thread.sleep(50);
                }
                WaitingList.remove(Id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public static void addManaChangeListener(ObservableValue.ChangeListener<Integer> listener) {
        currentMana.addChangeListener(listener);
    }

    public static void ManaHandler(Integer oldValue, Integer newValue) {
        if (newValue < oldValue) {

            Integer tmp_Id = null;
            while (tmp_Id == null || WaitingList.contains(tmp_Id)) {
                tmp_Id = new Random().nextInt(1000);
            }
            WaitingList.add(tmp_Id);
            lastActionId = tmp_Id;
            startRecover(tmp_Id);
        }
    }

    @SubscribeEvent
    public static void tick(TickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            for (Skill skill : skills) {
                skill.tickCoolDown();
            }
        }
    }
}