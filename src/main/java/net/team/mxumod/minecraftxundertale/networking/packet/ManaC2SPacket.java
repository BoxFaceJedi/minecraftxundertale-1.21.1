package net.team.mxumod.minecraftxundertale.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.team.mxumod.minecraftxundertale.capabilities.mana.PlayerManaProvider;
import net.team.mxumod.minecraftxundertale.skill.PlayerSkillManager;
import net.team.mxumod.minecraftxundertale.skill.block.BoneWallSkill;

import java.util.function.Supplier;

public class ManaC2SPacket {
    int tmp_mana;

    public ManaC2SPacket(int tmp_mana) {
        this.tmp_mana = tmp_mana;
    }

    public ManaC2SPacket(FriendlyByteBuf buf) {
        this.tmp_mana = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.tmp_mana);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            player.getCapability(PlayerManaProvider.PLAYER_MANA).ifPresent(playerMana -> {
                playerMana.setValue(tmp_mana);
            });
        });
    }
}