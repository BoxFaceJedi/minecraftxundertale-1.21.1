package net.team.mxumod.minecraftxundertale.skill;

import net.minecraft.nbt.CompoundTag;
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

    public abstract void executeSkill(ServerPlayer player, CompoundTag data);

    public static Vec3 getPositionInFrontOfPlayer(Player player, double distance) {
        Vec3 playerPos = player.position();
        double playerYaw = Math.toRadians(player.getYRot());

        double offsetX = -Math.sin(playerYaw) * distance;
        double offsetZ = Math.cos(playerYaw) * distance;

        double y = player.getY();

        return new Vec3(playerPos.x + offsetX, y, playerPos.z + offsetZ);
    }

    public static Vec3 getPlayerRightVector(ServerPlayer player) {return player.getLookAngle().cross(player.getUpVector(1.0F)).normalize();}

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
