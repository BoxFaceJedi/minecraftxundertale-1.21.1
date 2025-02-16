package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.mxumod.mxumod.libraries.ObservableNumber;

public abstract class Skill<T extends Player> {
    protected String name;
    protected Class<?> tmpClass;

    protected int manaCost;
    protected int cooldown; //The unit of cooldown is tick.
    protected ObservableNumber<Integer> currentCoolDown = new ObservableNumber<>(0);

    protected void eventRegister() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    protected void eventUnregister() {
        MinecraftForge.EVENT_BUS.unregister(this);
    }

    public Skill(String name, int manaCost, int cooldown) {
        this.name = name;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.currentCoolDown.setValue(cooldown);

        this.currentCoolDown.addChangeListener(((oldValue, newValue) -> eventRegister()));
    }

    @SubscribeEvent
    public void cooldownHandler(TickEvent.PlayerTickEvent  event) {
        if (currentCoolDown.getValue() < cooldown - 1) {
            currentCoolDown.addValue(1, false);
        }else {
            currentCoolDown.addValue(1, false);
            eventUnregister();
        }
    }

    public abstract void activate(Player player);

    public boolean canActivate() {
        if (PlayerSkillManager.getCurrentMana() >= manaCost && currentCoolDown.getValue() >= cooldown) {
            currentCoolDown.setValue(0);
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
        return currentCoolDown.getValue();
    }
}
