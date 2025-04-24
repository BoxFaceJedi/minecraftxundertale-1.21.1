package net.team.mxumod.minecraftxundertale.skill.special;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.team.mxumod.minecraftxundertale.skill.Skill;
import org.lwjgl.opengl.INTELBlackholeRender;

import java.util.List;
import java.util.Map;

public class Telekinesis extends Skill {
    public Telekinesis()  {
        super("Telekinesis", 10, 500);
    }

    @Override
    public void executeSkill(ServerPlayer player, Object data) {
        if (data instanceof List<String> list) {

        }
    }
}
