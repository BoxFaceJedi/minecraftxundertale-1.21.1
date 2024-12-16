package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.breeze.Shoot;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.mxumod.mxumod.skill.ShootArrow;

import static net.mxumod.mxumod.event.ClientEvents.ClientForgeEvents.keyHeld;

public class MxuTestC2SPacket {
    public MxuTestC2SPacket() {

    }

    public MxuTestC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

                ShootArrow.shootArrow(player);

        });
    }
}
