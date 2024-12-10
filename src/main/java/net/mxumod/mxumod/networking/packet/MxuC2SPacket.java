package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.minecraftforge.network.NetworkContext;

import java.util.function.Supplier;

public class MxuC2SPacket {
    public MxuC2SPacket() {

    }

    public MxuC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerLevel level = player.serverLevel().getLevel();
            EntityType.COW.spawn(level,null, null, player.getOnPos(), MobSpawnType.COMMAND, true, false);
        });
    }
}
