package net.team.mxumod.minecraftxundertale.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;

public record BoneSpikeC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneSpikeC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "bonespikec2spacket"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoneSpikeC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneSpikeC2SPacket::write, BoneSpikeC2SPacket::new);

    public BoneSpikeC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}