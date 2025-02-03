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
import net.team.mxumod.minecraftxundertale.skill.special.BoneSpikeSkill;

public record BoneSpikeC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneSpikeC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "BoneSpikeC2SPacket"));
    public static final StreamCodec<RegistryFriendlyByteBuf, BoneSpikeC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneSpikeC2SPacket::write, BoneSpikeC2SPacket::new);

    public BoneSpikeC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    public void handle(BoneSpikeC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {

            ServerPlayer player = (ServerPlayer) context.player();

            new PlayerSkillManager().activateSkill(new BoneSpikeSkill().getName(),player);

        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}