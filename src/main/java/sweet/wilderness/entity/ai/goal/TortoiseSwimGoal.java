package sweet.wilderness.entity.ai.goal;

import net.minecraft.entity.ai.goal.SwimGoal;
import sweet.wilderness.entity.entities.tortoise.TortoiseEntity;

public class TortoiseSwimGoal
        extends SwimGoal {
    private final TortoiseEntity mob;

    public TortoiseSwimGoal(TortoiseEntity mob) {
        super(mob);
        this.mob = mob;
    }

    public boolean canStart() {
        if (this.mob.isHiding())
            return false;


        return super.canStart();
    }
}