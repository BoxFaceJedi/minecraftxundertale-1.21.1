package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;

import java.util.function.Supplier;

public class BoneWallC2SPacket {
    public BoneWallC2SPacket() {

    }

    public BoneWallC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {


            ServerPlayer player = context.getSender();

            new PlayerSkillManager().activateSkill(new BoneWallSkill().getName(),player);

        });
    }
}