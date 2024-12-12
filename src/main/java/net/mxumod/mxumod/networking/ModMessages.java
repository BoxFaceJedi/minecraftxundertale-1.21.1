package net.mxumod.mxumod.networking;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.networking.packet.MxuC2SPacket;

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

            net.messageBuilder(MxuC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                    .decoder(MxuC2SPacket::new)
                    .encoder(MxuC2SPacket::toBytes)
                    .consumerMainThread(MxuC2SPacket::handle)
                    .add();

        }

        public static <MSG> void sendToServer(MSG message) {
            INSTANCE.send(message, PacketDistributor.SERVER.noArg());
        }
        public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
            INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
        }

}
