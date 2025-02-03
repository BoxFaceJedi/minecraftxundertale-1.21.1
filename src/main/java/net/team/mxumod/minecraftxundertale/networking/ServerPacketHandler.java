package net.team.mxumod.minecraftxundertale.networking;

import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneBarrageC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneSpikeC2SPacket;
import net.team.mxumod.minecraftxundertale.networking.packets.BoneWallC2SPacket;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.basic.BoneBarrageSKill;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;
import net.team.mxumod.minecraftxundertale.skill.special.BoneSpikeSkill;

public class ServerPacketHandler {
    public static void handleBoneBarrage(BoneBarrageC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            new PlayerSkillManager().activateSkill(new BoneBarrageSKill().getName(), player);
        });
    }

    public static void handleBoneSpike(BoneSpikeC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            new PlayerSkillManager().activateSkill(new BoneSpikeSkill().getName(),player);

        });
    }

    public static void handleBoneWall(BoneWallC2SPacket message, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            new PlayerSkillManager().activateSkill(new BoneWallSkill().getName(),player);

        });
    }
}
