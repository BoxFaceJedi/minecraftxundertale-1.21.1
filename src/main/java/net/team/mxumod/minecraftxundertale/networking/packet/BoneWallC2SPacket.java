package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.mxumod.mxumod.skill.PlayerSkillManager;
import net.mxumod.mxumod.skill.block.BoneWallSkill;

public class BoneWallC2SPacket {
    public BoneWallC2SPacket() {

    }

    public BoneWallC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {


            ServerPlayer player = context.getSender();

            new PlayerSkillManager().activateSkill(new BoneWallSkill().getName(),player);

        });
    }
}