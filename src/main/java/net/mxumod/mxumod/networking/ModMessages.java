package net.mxumod.mxumod.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.*;
import net.mxumod.mxumod.MxuMod;
import net.mxumod.mxumod.networking.packet.BlockingC2SPacket;
import net.mxumod.mxumod.networking.packet.MxuTestC2SPacket;
import net.mxumod.mxumod.skill.Blocking;

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

            net.messageBuilder(MxuTestC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                    .decoder(MxuTestC2SPacket::new)
                    .encoder(MxuTestC2SPacket::toBytes)
                    .consumerMainThread(MxuTestC2SPacket::handle)
                    .add();

            net.messageBuilder(BlockingC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                    .decoder(BlockingC2SPacket::new)
                    .encoder(BlockingC2SPacket::toBytes)
                    .consumerMainThread(BlockingC2SPacket::handle)
                    .add();

        }

        public static <MSG> void sendToServer(MSG message) {
            INSTANCE.send(message, PacketDistributor.SERVER.noArg());
        }
        public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
            INSTANCE.send(message, PacketDistributor.PLAYER.with(player));
        }

}
