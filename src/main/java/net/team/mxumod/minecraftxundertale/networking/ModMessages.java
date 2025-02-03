package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.networking.packet.BoneWallC2SPacket;
import net.mxumod.mxumod.networking.packet.BoneSpikeC2SPacket;
import net.mxumod.mxumod.networking.packet.BoneBarrageC2SPacket;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public  static void register() {
        SimpleChannel net = ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(MxuMod.MOD_ID, "messages"))
                .networkProtocolVersion(1)
                .clientAcceptedVersions((s, v) -> true)
                .serverAcceptedVersions((s, v) -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(BoneBarrageC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BoneBarrageC2SPacket::new)
                .encoder(BoneBarrageC2SPacket::toBytes)
                .consumerMainThread(BoneBarrageC2SPacket::handle)
                .add();

        net.messageBuilder(BoneWallC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BoneWallC2SPacket::new)
                .encoder(BoneWallC2SPacket::toBytes)
                .consumerMainThread(BoneWallC2SPacket::handle)
                .add();

        net.messageBuilder(BoneSpikeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BoneSpikeC2SPacket::new)
                .encoder(BoneSpikeC2SPacket::toBytes)
                .consumerMainThread(BoneSpikeC2SPacket::handle)
                .add();

    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(message, PacketDistributor.SERVER.noArg());
    }
    public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
        INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
    }

}