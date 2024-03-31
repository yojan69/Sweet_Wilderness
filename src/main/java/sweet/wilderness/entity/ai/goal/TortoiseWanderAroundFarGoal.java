package sweet.wilderness.entity.ai.goal;

import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import sweet.wilderness.entity.entities.tortoise.TortoiseEntity;

public class TortoiseWanderAroundFarGoal
        extends WanderAroundFarGoal {
    private final TortoiseEntity mob;

    public TortoiseWanderAroundFarGoal(TortoiseEntity mob, double speed, float probability) {
        super(mob, speed, probability);
        this.mob = mob;
    }

    @Override
    public boolean canStart() {
        if (this.mob.isHiding()){
            return false;
        }

        return super.canStart();
    }
}