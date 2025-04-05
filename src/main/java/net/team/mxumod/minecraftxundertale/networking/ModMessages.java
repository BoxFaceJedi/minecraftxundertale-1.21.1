package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import net.team.mxumod.minecraftxundertale.MinecraftxUndertaleMod;
import net.team.mxumod.minecraftxundertale.networking.packet.*;

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

        net.messageBuilder(BoneSpikeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BoneSpikeC2SPacket::new)
                .encoder(BoneSpikeC2SPacket::toBytes)
                .consumerMainThread(BoneSpikeC2SPacket::handle)
                .add();

        net.messageBuilder(ManaS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(ManaS2CPacket::new)
                .encoder(ManaS2CPacket::toBytes)
                .consumerMainThread(ManaS2CPacket::handle)
                .add();

        net.messageBuilder(SkillsC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SkillsC2SPacket::new)
                .encoder(SkillsC2SPacket::toBytes)
                .consumerMainThread(SkillsC2SPacket::handle)
                .add();

        net.messageBuilder(CombatmodeC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CombatmodeC2SPacket::new)
                .encoder(CombatmodeC2SPacket::toBytes)
                .consumerMainThread(CombatmodeC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.send(PacketDistributor.SERVER.noArg(), message);
    }
    public static <MSG> void sendToClient(MSG message, Supplier<ServerPlayer> playerSupplier) {
        INSTANCE.send(PacketDistributor.PLAYER.with(playerSupplier), message);
    }

}