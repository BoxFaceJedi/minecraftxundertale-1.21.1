package net.mxumod.mxumod.skill;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;

@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class Dodge {

    public static void dodge(Player player, double speed) {
        player.setDeltaMovement(player.getDeltaMovement().normalize().multiply(speed, 1, speed));
    }
}
