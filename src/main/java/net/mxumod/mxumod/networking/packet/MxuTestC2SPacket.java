package net.mxumod.mxumod.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.network.CustomPayloadEvent;

public class MxuTestC2SPacket {
    public MxuTestC2SPacket() {

    }

    public MxuTestC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public void handle(CustomPayloadEvent.Context context) {
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();

            assert player != null;
            ServerLevel level = player.serverLevel().getLevel();

            Arrow arrow = new Arrow(level,player.getX(), player.getEyeY(), player.getZ(), new ItemStack(Items.ARROW, 1), null);

            Vec3 lookVec = player.getLookAngle();

            arrow.shoot(lookVec.x, lookVec.y, lookVec.z, 2.0f, 0.0f);

            arrow.setOwner(player);

            level.addFreshEntity(arrow);
        });
    }
}
