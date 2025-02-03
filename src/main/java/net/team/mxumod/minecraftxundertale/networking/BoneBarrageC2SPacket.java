package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.basic.BoneBarrageSKill;

public record BoneBarrageC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneBarrageC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "BoneBarrageC2SPacket"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoneBarrageC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneBarrageC2SPacket::write, BoneBarrageC2SPacket::new);

    public BoneBarrageC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    public void handle(BoneBarrageC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

                new PlayerSkillManager().activateSkill(new BoneBarrageSKill().getName(), player);

        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
