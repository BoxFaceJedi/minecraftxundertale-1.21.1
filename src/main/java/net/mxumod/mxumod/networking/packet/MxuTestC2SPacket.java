package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraftforge.event.network.CustomPayloadEvent;

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
            assert player != null;
            ServerLevel level = player.serverLevel().getLevel();
            EntityType.ARROW.spawn(level,null, null, player.getOnPos(), MobSpawnType.COMMAND, true, false);
        });
    }
}
