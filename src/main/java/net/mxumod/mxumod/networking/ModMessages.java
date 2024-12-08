package net.mxumod.mxumod.networking;

import net.minecraft.network.Connection;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.network.*;
import net.mxumod.mxumod.MxuMod;

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
                    .clientAcceptedVersions(Channel.VersionTest.ACCEPT_VANILLA)
                    .serverAcceptedVersions(Channel.VersionTest.ACCEPT_VANILLA)
                    .simpleChannel();

            INSTANCE = net;

        }

        public static <MSG> void sendToServer(MSG message) {
            INSTANCE.send(message, PacketDistributor.SERVER.noArg());
        }
        public static <MSG> void sendToClient(MSG message, ServerPlayer player) {
            INSTANCE.send(PacketDistributor.PLAYER.with(player), (Connection) message);
        }

}
