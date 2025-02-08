package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.networking.packet.BoneBarrageC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packet.BoneSpikeC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packet.BoneWallC2SPacket;

import java.util.function.Supplier;

public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;

    private static int id() {
        return packetId++;
    }

    public  static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(ResourceLocation.fromNamespaceAndPath(MinecraftxUndertaleMod.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions((s) -> true)
                .serverAcceptedVersions((s) -> true)
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
        INSTANCE.send(PacketDistributor.SERVER.noArg(), message);
    }
    public static <MSG> void sendToClient(MSG message, Supplier<ServerPlayer> playerSupplier) {
        INSTANCE.send(PacketDistributor.PLAYER.with(playerSupplier), message);
    }

}