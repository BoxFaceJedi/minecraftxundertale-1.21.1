package net.team.mxumod.minecraftxundertale.skill.special;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingKnockBackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.skill.Skill;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = MinecraftxUndertaleMod.MOD_ID)
public class BoneSpikeSkill extends Skill {

    private static final Set<LivingEntity> noKnockbackTargets = new HashSet<>();

    public BoneSpikeSkill() {
        super("Bone Spike", 25, 1000);
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (event.getSource().getDirectEntity() instanceof EvokerFangs fangs) {
            if (fangs.getOwner() instanceof ServerPlayer) {
                noKnockbackTargets.add(event.getEntity());
            }
        }
    }

    @SubscribeEvent
    public static void onLivingKnockback(LivingKnockBackEvent event) {
        LivingEntity entity = event.getEntity();
        if (noKnockbackTargets.remove(entity)) {
            event.setCanceled(true);
            entity.setDeltaMovement(entity.getDeltaMovement().add(0.0, 1.5, 0.0));
        }
    }

    @Override
    public void executeSkill(ServerPlayer player, @Nullable CompoundTag data) {
        ServerLevel level = player.serverLevel();
        Vec3 posInFront = getPositionInFrontOfPlayer(player, 1.5); // 區域變數 OK

        EvokerFangs boneSpike = new EvokerFangs(EntityType.EVOKER_FANGS, level);
        boneSpike.setPos(posInFront.x, posInFront.y, posInFront.z);
        boneSpike.setOwner(player);
        level.addFreshEntity(boneSpike);
    }
}
