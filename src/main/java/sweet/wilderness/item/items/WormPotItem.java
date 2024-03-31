package sweet.wilderness.item.items;

import net.minecraft.entity.SpawnReason;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.RaycastContext;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.entity.entities.worm.WormEntity;

public class WormPotItem extends Item {
    public WormPotItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getPlayer() == null){return ActionResult.FAIL;}
        if (context.getWorld().isClient()){return ActionResult.PASS;}

        BlockHitResult blockHitResult = raycast(context.getWorld(), context.getPlayer(), RaycastContext.FluidHandling.NONE);
        if (blockHitResult == null){return ActionResult.FAIL;}

        BlockPos blockPos = context.getBlockPos().offset(blockHitResult.getSide());
        spawnWorm(blockPos, (ServerWorld) context.getWorld());

        if (!context.getPlayer().getAbilities().creativeMode){
            context.getPlayer().getMainHandStack().setCount(context.getPlayer().getMainHandStack().getCount()-1);
            context.getPlayer().giveItemStack(Items.FLOWER_POT.getDefaultStack());
        }

        return ActionResult.SUCCESS;
    }

    private static void spawnWorm(BlockPos blockPos, ServerWorld world){
        WormEntity worm = ModEntities.WORM.create(world, null, null, blockPos, SpawnReason.TRIGGERED, true, false);
        world.spawnEntity(worm);
    }
}
