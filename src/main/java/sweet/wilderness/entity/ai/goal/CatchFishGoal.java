package sweet.wilderness.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.passive.SalmonEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import sweet.wilderness.entity.entities.grizzlybear.GrizzlyBearEntity;

public class CatchFishGoal extends Goal {
    private Path path;
    private SalmonEntity salmonTarget = null;
    private final GrizzlyBearEntity mob;

    public CatchFishGoal(GrizzlyBearEntity mob){
        this.mob = mob;
        System.out.println("initialized catch fish goal");
    }

    @Override
    public boolean canStart() {
        if (this.mob.isCatchingFish()){
            if (!mob.getWorld().isClient){
                if (!mob.getWorld().getEntitiesByClass(SalmonEntity.class,
                        mob.getBoundingBox().expand(50),
                        EntityPredicates.VALID_ENTITY).isEmpty())
                {
                    if (salmonTarget == null){
                        SalmonEntity lilSalmon = mob.getWorld().getClosestEntity(
                                SalmonEntity.class,
                                null,
                                mob, mob.getX(), mob.getY(), mob.getZ(), mob.getBoundingBox());
                            TargetSalmon(lilSalmon);
                    }
                    else{
                        System.out.println("got them");
                    }

                    return true;
                }
            }
        }
        return false;
    }

    private void TargetSalmon(SalmonEntity salmon){
        this.salmonTarget = salmon;
        System.out.println("salmon target pos is: "+salmonTarget.getBlockPos());
        this.path = mob.getNavigation().findPathTo(salmonTarget, 0);

        this.mob.getNavigation().startMovingAlong(this.path, GrizzlyBearEntity.MOVEMENT_SPEED);
    }

    @Override
    public void start() {
    }
}
