package net.team.mxumod.minecraftxundertale.capabilities.mana;

import net.minecraft.nbt.CompoundTag;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.libraries.ObservableNumber;

public class PlayerMana extends ObservableNumber<Integer> {
    private int maxMana = 200;

    public PlayerMana() {
        super(200);
    }

    public void copyFrom(PlayerMana source) {
        this.value = source.value;
    }

    public void saveNBTData(CompoundTag nbtData) {nbtData.putInt(MinecraftxUndertaleMod.MOD_ID + "_Mana", this.value);}

    public void loadNBTData(CompoundTag nbtData) {
        nbtData.getInt(MinecraftxUndertaleMod.MOD_ID + "_Mana");
    }
}
