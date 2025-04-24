package net.team.mxumod.minecraftxundertale.skill.basic.Telekinesis;

import net.minecraft.world.entity.LivingEntity;

public class TelekinesisData {
    public LivingEntity target;
    public String key;

    public TelekinesisData(LivingEntity target, String key) {
        this.target = target;
        this.key = key;
    }
}
