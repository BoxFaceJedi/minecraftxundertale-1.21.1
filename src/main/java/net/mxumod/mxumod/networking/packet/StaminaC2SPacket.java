package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.network.CustomPayloadEvent;
import net.mxumod.mxumod.skill.PlayerSkillManager;
import net.mxumod.mxumod.skill.basic.BoneBarrageSKill;


public class StaminaC2SPacket {
    public StaminaC2SPacket() {

    }

    public StaminaC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            new PlayerSkillManager().activateSkill(new BoneBarrageSKill().getName(), player);

        });
    }
}
