package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;

import java.util.function.Supplier;

public class MaxManaS2CPacket {
    private final int maxMana;

    public MaxManaS2CPacket(int a) {
        this.maxMana = a;
    }

    public MaxManaS2CPacket(FriendlyByteBuf buf) {
        this.maxMana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(maxMana);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {

        });
    }
}
