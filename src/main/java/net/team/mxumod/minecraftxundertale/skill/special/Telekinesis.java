package net.team.mxumod.minecraftxundertale.skill.special;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.team.mxumod.minecraftxundertale.skill.Skill;

public class Telekinesis extends Skill {
    public Telekinesis()  {
        super("Telekinesis", 10, 500);
    }

    public class TelekinesisMotion {
        private final LivingEntity entity;
        private final Vec3 start;
        private final Vec3 control;
        private final Vec3 end;
        private final int duration;
        private int ticks = 0;
        private boolean active = true;

        public TelekinesisMotion(LivingEntity entity, Vec3 end, int duration) {
            this.entity = entity;
            this.start = entity.position();
            this.end = end;
            this.duration = duration;

            // 控制點：起點與終點中間 + Y 軸抬高 1.5 格
            this.control = start.add(end).scale(0.5).add(0, 1.5, 0);
            entity.setNoGravity(true);
        }

        public void tick(ServerLevel level) {
            if (!active || ticks >= duration) {
                finish();
                return;
            }

            // 如果碰到方塊就結束
            if (!level.getBlockCollisions(entity, entity.getBoundingBox()).toList().isEmpty()) {
                finish();
                return;
            }

            float t = (float) ticks / duration;
            Vec3 targetPos = quadraticBezier(t, start, control, end);
            Vec3 motion = targetPos.subtract(entity.position());

            entity.setDeltaMovement(motion);
            entity.hurtMarked = true;
            ticks++;
        }

        private void finish() {
            entity.setDeltaMovement(Vec3.ZERO);
            entity.setNoGravity(false);
            active = false;
        }

        public boolean isActive() {
            return active;
        }

        // 二次貝茲曲線公式
        private Vec3 quadraticBezier(float t, Vec3 p0, Vec3 p1, Vec3 p2) {
            double u = 1 - t;
            double tt = t * t;
            double uu = u * u;

            return new Vec3(
                    uu * p0.x + 2 * u * t * p1.x + tt * p2.x,
                    uu * p0.y + 2 * u * t * p1.y + tt * p2.y,
                    uu * p0.z + 2 * u * t * p1.z + tt * p2.z
            );
        }
    }


    @Override
    public void executeSkill(ServerPlayer player, Object data) {
        if (data instanceof LivingEntity targetEntity) {
            Vec3 deltaPosition = player.position().subtract(targetEntity.position());
            double Radius = deltaPosition.length();
            Vec3 startPos = player.position(), endPos = player.position().add(deltaPosition.multiply(2, 2, 2));
            
        }


    }
}
