package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.ServerSideSkillManager;

import java.util.function.Supplier;

public class SkillsC2SPacket {
    String skillName;

    public SkillsC2SPacket(String skillName) {
        this.skillName = skillName;
    }

    public SkillsC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            ServerSideSkillManager.playerUseSkillRequire(skillName, player);
        });
    }
}
