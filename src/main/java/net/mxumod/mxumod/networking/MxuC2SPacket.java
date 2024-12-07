package net.mxumod.mxumod.networking;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkContext;

import java.util.function.Supplier;

public class MxuC2SPacket {
    public MxuC2SPacket() {

    }

    public MxuC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkContext> context) {
        return true;
    }


}
