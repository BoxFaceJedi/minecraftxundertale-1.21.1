package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public abstract class Skill<T extends Player> {
    protected String name;
    protected int manaCost;
    protected int cooldown;
    protected int currentCoolDown = 0;

    public Skill(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }

    public void tickCoolDown() {
        if (currentCoolDown < cooldown) {
            currentCoolDown += 1;
        }
    }

    public boolean canActivate() {
        return PlayerSkillManager.getCurrentMana() >= manaCost && currentCoolDown >= cooldown;
    }

    public void activate(Player player) {
        currentCoolDown = 0;
        PlayerSkillManager.reduceMana(manaCost);
        executeSkill(player);
    }

    protected abstract void executeSkill(Player player);

    public static Vec3 getPositionInFrontOfPlayer(ServerPlayer player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());

        double offsetX = -Math.sin(playerYaw) * distance;
        double offsetZ = Math.cos(playerYaw) * distance;

        double y = player.getY();

        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public int getCurrentCoolDown() {
        return currentCoolDown;
    }
}
