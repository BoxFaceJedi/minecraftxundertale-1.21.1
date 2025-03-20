package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;

import java.util.function.Supplier;

public class ManaS2CPacket {
    private final int mana;

    public ManaS2CPacket(int mana) {
        this.mana = mana;
    }

    public ManaS2CPacket(FriendlyByteBuf buf) {
        this.mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(mana);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {


            ServerPlayer player = context.getSender();

            new PlayerSkillManager().activateSkill(new BoneWallSkill().getName(),player);

        });
    }
}
