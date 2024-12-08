package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.network.NetworkContext;

import java.util.function.Supplier;

public class MxuC2SPacket {
    public MxuC2SPacket() {

    }

    public MxuC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkContext> supplier) {
        NetworkContext context = supplier.get();


        return true;
    }


}
