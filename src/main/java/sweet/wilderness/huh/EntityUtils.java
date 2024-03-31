package sweet.wilderness.huh;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityUtils {
    public static void RemoveEntity(World world, Entity entity){
        entity.discard();
    }
}
