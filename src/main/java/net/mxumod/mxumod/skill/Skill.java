package net.mxumod.mxumod.skill;

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
            currentCoolDown++;
        }
    }

    public abstract void activate(Player player);

    public boolean canActivate() {
        if (PlayerSkillManager.getCurrentMana() >= manaCost && currentCoolDown >= cooldown) {
            currentCoolDown = 0;
            PlayerSkillManager.reduceMana(manaCost); // Reduce mana via PlayerSkillManager
            return true;
        }
        return false;
    }

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
