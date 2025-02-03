package net.team.mxumod.minecraftxundertale.networking;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.team.mxumod.minecraftxundertale.Minecraftxundertale;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneBarrageC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneSpikeC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneWallC2SPacket;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, modid = Minecraftxundertale.MODID, value = Dist.CLIENT)
public class ModNetworking {
    private static int packetId = 0;

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(String.valueOf(id()));

        registrar.playToServer(
                BoneBarrageC2SPacket.TYPE,
                BoneBarrageC2SPacket.STREAM_CODEC,
                ServerPacketHandler::handleBoneBarrage
        );

        registrar.playToServer(
                BoneSpikeC2SPacket.TYPE,
                BoneSpikeC2SPacket.STREAM_CODEC,
                ServerPacketHandler::handleBoneSpike
        );

        registrar.playToServer(
                BoneWallC2SPacket.TYPE,
                BoneWallC2SPacket.STREAM_CODEC,
                ServerPacketHandler::handleBoneWall
        );
    }

    private static int id() {
        return packetId++;
    }
}
