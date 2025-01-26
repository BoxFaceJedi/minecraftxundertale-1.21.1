package net.mxumod.mxumod.skill;

import net.minecraft.world.entity.player.Player;

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

    public boolean canActivate(PlayerSkillManager manager) {
        if (manager.getCurrentMana() >= manaCost && currentCoolDown >= cooldown) {
            currentCoolDown = 0;
            manager.reduceMana(manaCost); // Reduce mana via PlayerSkillManager
            return true;
        }
        return false;
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
