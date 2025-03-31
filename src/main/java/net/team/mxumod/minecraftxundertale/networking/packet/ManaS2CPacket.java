package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.client.gui.ManaOverlay;
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
        buf.writeInt(this.mana);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ManaOverlay.currentMana = mana;
        });
    }
}
