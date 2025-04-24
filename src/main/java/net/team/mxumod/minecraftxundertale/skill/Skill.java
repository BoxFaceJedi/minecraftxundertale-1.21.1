package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public abstract class Skill {
    protected String skillName;
    protected int manaCost, cooldown;

    public Skill(String skillName, int manaCost, int cooldown) {
        this.skillName = skillName;
        this.cooldown = cooldown;
        this.manaCost = manaCost;
    }

    public abstract void executeSkill(ServerPlayer player, Object data);;

    public static Vec3 getPositionInFrontOfPlayer(Player player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());

        double offsetX = -Math.sin(playerYaw) * distance;
        double offsetZ = Math.cos(playerYaw) * distance;

        double y = player.getY();

        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

    public Vec3 getPlayerLookVector(ServerPlayer player) {
        double playerYaw = Math.toRadians(player.getYRot());
        Math.sin(player.getXRot());
        return new Vec3(-Math.sin(playerYaw), , Math.cos(playerYaw));
    }

    public String getName() {
        return this.skillName;
    }
    public int getManaCost() {
        return this.manaCost;
    }
    public int getCooldown() {
        return this.cooldown;
    }

}
