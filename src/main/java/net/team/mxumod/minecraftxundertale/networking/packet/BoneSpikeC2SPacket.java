package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.special.BoneSpikeSkill;

import java.util.function.Supplier;

public class BoneSpikeC2SPacket {
    public BoneSpikeC2SPacket() {

    }

    public BoneSpikeC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();

            new PlayerSkillManager().activateSkill(new BoneSpikeSkill().getName(),player);

        });
    }
}