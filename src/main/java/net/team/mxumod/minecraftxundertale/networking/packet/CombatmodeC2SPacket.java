package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.ServerSideSkillManager;

import java.util.function.Supplier;

public class CombatmodeC2SPacket {
    boolean isLeft;
    public CombatmodeC2SPacket(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public CombatmodeC2SPacket(FriendlyByteBuf buf) {
        isLeft = buf.readBoolean();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isLeft);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();
            if (isLeft) ServerSideSkillManager.playerLeftCombatmode(player);
        });
    }
}
