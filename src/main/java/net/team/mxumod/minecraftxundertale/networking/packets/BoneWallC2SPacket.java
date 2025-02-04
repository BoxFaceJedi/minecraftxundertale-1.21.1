package net.team.mxumod.minecraftxundertale.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;

public record BoneWallC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneWallC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "bonewallc2spacket"));
    public static final StreamCodec<FriendlyByteBuf, BoneWallC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneWallC2SPacket::write, BoneWallC2SPacket::new);

    public BoneWallC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}