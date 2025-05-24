package net.team.mxumod.minecraftxundertale.entities.gasterblasters;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class GasterBlasterEntity_Launch extends Entity {
    public GasterBlasterEntity_Launch(EntityType<?> type, Level level) {
        super(type, level);
        this.noPhysics = false;
    }

    @Override protected void defineSynchedData() {}
    @Override protected void readAdditionalSaveData(@NotNull CompoundTag tag) {}
    @Override protected void addAdditionalSaveData(@NotNull CompoundTag tag) {}
}