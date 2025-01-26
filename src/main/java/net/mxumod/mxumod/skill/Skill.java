package net.mxumod.mxumod.skill;

import net.minecraft.world.entity.player.Player;

public abstract class Skill {
    protected String name;
    protected int manaCost;
    protected long lastActivationTime = 0;
    protected long cooldown;
    protected long currentTime;

    public Skill(String name, int manaCost, long cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
    }

    public abstract void activate(Player player);

    public boolean canActivate(Player player, int currentMana) {
        currentTime = System.currentTimeMillis();
        if (currentMana >= manaCost && currentTime - lastActivationTime >= cooldown) {
            lastActivationTime = currentTime;
            return true;
        } else {
            return false;
        }
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public long getCooldown() {
        return cooldown;
    }
}
