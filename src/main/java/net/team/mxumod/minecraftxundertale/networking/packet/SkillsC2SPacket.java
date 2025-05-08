package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.skill.ServerSideSkillManager;

import java.util.function.Supplier;

public class SkillsC2SPacket {
    private final String skillName;
    private final CompoundTag data;


    public SkillsC2SPacket(String skillName, CompoundTag data) {
        this.skillName = skillName;
        this.data = data;
    }

    public SkillsC2SPacket(String skillName) {
        this.skillName = skillName;
        this.data = new CompoundTag();
    }

    public SkillsC2SPacket(FriendlyByteBuf buf) {
        this.skillName = buf.readUtf();
        this.data = buf.readNbt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.skillName);
        buf.writeNbt(this.data);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player != null) {
                ServerSideSkillManager.playerUseSkillRequire(skillName, player, this.data);
            }
        });
        context.setPacketHandled(true);
    }
}