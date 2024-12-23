package net.mxumod.mxumod.skill;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class Dodge {
    public  static  void dodge(Player player) {
        player.moveTo(player.pick(10, 0, false).getLocation());
    }
}
