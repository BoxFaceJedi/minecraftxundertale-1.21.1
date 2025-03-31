package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.server.level.ServerPlayer;
import net.team.mxumod.minecraftxundertale.libraries.ObservableNumber;
import net.team.mxumod.minecraftxundertale.libraries.ObservableValue;
import net.team.mxumod.minecraftxundertale.networking.ModMessages;
import net.team.mxumod.minecraftxundertale.networking.packet.ManaS2CPacket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ServerSideSkillManager {
    private static int maxMana = 200;

    private final static HashMap<ServerPlayer, ObservableNumber<Integer>> playersManaMap = new HashMap<>();
    private final static HashMap<ServerPlayer, ArrayList<String>> playersCooldownSkillsMap = new HashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(999);
    private static final HashMap<ServerPlayer, HashMap<String, ScheduledFuture<?>>> cooldownTasks = new HashMap<>();
    private final static ArrayList<TemporarySkill> availableSkills = new ArrayList<>() {{

    }};

    //tmp_DataList = [playerMana, lastActionId];
    public static void addPlayer(ServerPlayer player) {
        ObservableNumber<Integer> tmp_Mana = new ObservableNumber<>(0);
        playersManaMap.putIfAbsent(player, tmp_Mana);
        playersCooldownSkillsMap.putIfAbsent(player,new ArrayList<>());
        startRecover(player);
        tmp_Mana.addChangeListener((oldValue, newValue) -> {
            ModMessages.sendToClient(new ManaS2CPacket(newValue), () -> player);
        });
        tmp_Mana.addChangeListener(((oldValue, newValue) -> {if (newValue < oldValue) startRecover(player);}));
    }

    public static void playerUseSkillRequire(String SkillName, ServerPlayer player) {
        if (!isPlayerContained(player)) return;
        for (TemporarySkill skill : availableSkills) {
            if (!skill.getName().equals(SkillName)) continue;
            if (!isSkillUsable(skill, player)) return;

            skill.executeSkill();
            playersCooldownSkillsMap.get(player).add(skill.getName());
            playersManaMap.get(player).reduceValue(skill.getManaCost());

            ScheduledFuture<?> cooldownTask = scheduler.schedule(() -> {
                playersCooldownSkillsMap.get(player).remove(skill.getName());
            }, skill.getCooldown(), TimeUnit.MILLISECONDS);
            cooldownTasks.computeIfAbsent(player, k -> new HashMap<>()).put(skill.getName(), cooldownTask);
            return;
        }
    }

    public static boolean isSkillUsable(TemporarySkill skill, ServerPlayer player) {
        return playersManaMap.get(player).getValue() >= skill.getManaCost() &&
                !playersCooldownSkillsMap.get(player).contains(skill.getName());
    }

    public static void startRecover(ServerPlayer player) {
        if (!isPlayerContained(player)) return;
        cooldownTasks.computeIfAbsent(player, k -> new HashMap<>());
        if (cooldownTasks.containsKey(player) && cooldownTasks.get(player).containsKey("manaRecover")) {
            cooldownTasks.get(player).get("manaRecover").cancel(false);
            cooldownTasks.get(player).remove("manaRecover");
        }

        ScheduledFuture<?> recoverTask = scheduler.scheduleAtFixedRate(() -> {
            ObservableNumber<Integer> tmp_Mana = playersManaMap.get(player);
            if (tmp_Mana.getValue() >= maxMana) return; // 确保 Mana 存在
            tmp_Mana.setValue(Math.min(tmp_Mana.getValue() + 20, maxMana)); // 修正 Mana 计算
        }, 3, 1, TimeUnit.SECONDS);

        cooldownTasks.computeIfAbsent(player, k -> new HashMap<>()).put("manaRecover", recoverTask);
    }

    public static void addPlayerManaListener(ServerPlayer player, ObservableNumber.ChangeListener<Integer> listener) {
        if (isPlayerContained(player)) playersManaMap.get(player).addChangeListener(listener);
    }

    public static void removePlayerManaListener(ServerPlayer player, ObservableValue.ChangeListener<Integer> listener) {
        if (isPlayerContained(player)) playersManaMap.get(player).removeChangeListener(listener);
    }

    public static Integer getPlayerMana(ServerPlayer player) {
        if (isPlayerContained(player)) return playersManaMap.get(player).getValue(); else return null;
    }

    public static void setPlayerMana(ServerPlayer player, int tmp_Mana) {
        if (isPlayerContained(player)) playersManaMap.get(player).setValue(tmp_Mana);
    }

    public static void addPlayerMana(ServerPlayer player, int tmp_Mana) {
        if (isPlayerContained(player)) playersManaMap.get(player).setValue(Math.min(playersManaMap.get(player).getValue() + tmp_Mana, maxMana));
    }

    public static void reducePlayerMana(ServerPlayer player, int tmp_Mana) {
        if (isPlayerContained(player)) playersManaMap.get(player).setValue(Math.max(0, playersManaMap.get(player).getValue() + tmp_Mana));
    }

    public static void removePlayer(ServerPlayer player) {
        playersManaMap.remove(player);
        playersCooldownSkillsMap.remove(player);

        if (cooldownTasks.containsKey(player)) {
            cooldownTasks.get(player).values().forEach(task -> task.cancel(false));
            cooldownTasks.remove(player);
        }
    }

    public static boolean isPlayerContained(ServerPlayer player) {
        return (playersManaMap.containsKey(player) && playersCooldownSkillsMap.containsKey(player));
    }
}
