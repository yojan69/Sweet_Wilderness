package sweet.wilderness.entity.ai.goal;

import net.minecraft.entity.ai.goal.LookAroundGoal;
import sweet.wilderness.entity.entities.tortoise.TortoiseEntity;

public class TortoiseLookAroundGoal
        extends LookAroundGoal {
    private final TortoiseEntity mob;

    public TortoiseLookAroundGoal(TortoiseEntity mob) {
        super(mob);
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
