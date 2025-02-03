package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;

public record BoneWallC2SPacket() implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<BoneWallC2SPacket> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Minecraftxundertale.MODID, "BoneWallC2SPacket"));
    public static final StreamCodec<FriendlyByteBuf, BoneWallC2SPacket> STREAM_CODEC = CustomPacketPayload.codec(BoneWallC2SPacket::write, BoneWallC2SPacket::new);

    public BoneWallC2SPacket(FriendlyByteBuf buf) {
        this();
    }

    public void write(FriendlyByteBuf buf) {

    }

    public static void handle(BoneWallC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {


            ServerPlayer player = (ServerPlayer) context.player();

            new PlayerSkillManager().activateSkill(new BoneWallSkill().getName(),player);

        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}