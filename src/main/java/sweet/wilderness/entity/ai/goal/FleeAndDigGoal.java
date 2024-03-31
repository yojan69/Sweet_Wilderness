package sweet.wilderness.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import sweet.wilderness.entity.entities.worm.WormEntity;
import sweet.wilderness.huh.EntityUtils;

import java.util.EnumSet;
import java.util.List;

public class FleeAndDigGoal extends Goal {
    private final WormEntity mob;
    private final World world;
    private long ticksUntilSomething;
    private Path path;
    private BlockPos targetPos;
    private boolean started;

    ///RAAHHH HARD CODING IT ALL OUT WE AINT GON CARE
    private boolean finishing;

    public FleeAndDigGoal(WormEntity mob, World world){
        this.mob = mob;
        this.world = world;

        this.setControls(EnumSet.of(Goal.Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<PlayerEntity> playersInRangeList =
                world.getEntitiesByClass(PlayerEntity.class, mob.getBoundingBox().expand(8, 3, 8),
                        EntityPredicates.VALID_ENTITY);

        if (playersInRangeList.isEmpty()) return false;

        PlayerEntity closestPlayer = world.getClosestEntity(playersInRangeList, TargetPredicate.DEFAULT,
                mob, mob.getX(), mob.getY(), mob.getZ());

        if (closestPlayer == null) return false;

        double distance = mob.getPos().distanceTo(closestPlayer.getPos());

        if (distance > 4) return false;

        if (!closestPlayer.isSneaking()
            || closestPlayer.getVelocity().getX() > 3
            || closestPlayer.getVelocity().getZ() > 3) {

            if (!started) {
                GetFleePos(mob, closestPlayer);
                path = mob.getNavigation().findPathTo(targetPos, 0);
            }

            return targetPos != null && path != null;
        }

        return false;
    }

    @Override
    public void start() {
        started = true;
        mob.getNavigation().startMovingAlong(path, 1.5f);
    }

    @Override
    public boolean shouldContinue() {
        if (started){
            if (path.isFinished() && !finishing){
                finishing = true;
                Dig();
            }

            return true;
        }

        return false;
    }

    @Override
    public boolean canStop() {
        return false;
    }

    private void GetFleePos(MobEntity mob, LivingEntity entity){
        Vec3d direction = entity.getPos().subtract(mob.getPos()).normalize().negate();
        Vec3d targetDirection = mob.getPos().add(direction.multiply(3));

        targetPos = new BlockPos((int)targetDirection.getX(), (int)targetDirection.getY(), (int)targetDirection.getZ());
    }
    private void Dig(){
        mob.triggerAnim("worm_controller", "dig");

        ticksUntilSomething = 20L;
    }

    @Override
    public void tick() {
        super.tick();

        if (--this.ticksUntilSomething != 0L)
            return;

        started = false;
        EntityUtils.RemoveEntity(world, mob);

    }
}
