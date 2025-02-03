package net.team.mxumod.minecraftxundertale.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;

public record BoneBarrageC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneBarrageC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "bonebarragec2spacket"));
    public static final StreamCodec<FriendlyByteBuf, BoneBarrageC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneBarrageC2SPacket::write, BoneBarrageC2SPacket::new);

    public BoneBarrageC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
