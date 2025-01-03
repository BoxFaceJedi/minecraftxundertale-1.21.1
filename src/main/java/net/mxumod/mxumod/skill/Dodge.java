package net.mxumod.mxumod.skill;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.mxumod.mxumod.MxuMod;

import java.util.concurrent.atomic.AtomicBoolean;
@Mod.EventBusSubscriber(modid = MxuMod.MOD_ID, value = Dist.CLIENT)
public class Dodge {
    private static final AtomicBoolean isMoving = new AtomicBoolean(false);

    public static void dodge(Player player, double speed) {
        player.setDeltaMovement(player.getDeltaMovement().normalize().multiply(speed, 1, speed));
    }
    @SubscribeEvent
    public static void onLivingEntityAttack(LivingAttackEvent event) {
        if (event.getEntity() instanceof Player player && event.getSource().getEntity() != null) {

        }
    }
}
