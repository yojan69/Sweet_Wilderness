package sweet.wilderness.block.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;
import sweet.wilderness.block.ModBlocks;

public class CatTailBlock extends Block {
    public CatTailBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HALF, DoubleBlockHalf.LOWER));
    }
    public static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 16, 16);
    public static final EnumProperty<DoubleBlockHalf> HALF = Properties.DOUBLE_BLOCK_HALF;

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        world.setBlockState(pos.up(), (BlockState)state.with(HALF, DoubleBlockHalf.UPPER), Block.NOTIFY_ALL);
    }

    @Override
    public boolean canReplace(BlockState state, ItemPlacementContext context) {
        return false;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockPos blockPosUp = pos.up(1);
        BlockState blockState = world.getBlockState(blockPos);
        BlockState blockStateUp = world.getBlockState(blockPosUp);
        if (blockState.isIn(BlockTags.DIRT) && blockStateUp.isOf(Blocks.AIR)) {
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        BreakBothBlocks(state, world, pos);
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        BreakBothBlocks(state, world, pos);

        return super.onBreak(world, pos, state, player);
    }

    private void BreakBothBlocks(BlockState state, World world, BlockPos pos){
        BlockState stateUp = world.getBlockState(pos.up());
        BlockState stateDown = world.getBlockState(pos.down());

        switch (state.get(HALF)){
            case UPPER:
                if (!stateDown.isOf(ModBlocks.CAT_TAIL)){break;}
                world.breakBlock(pos.down(), false);
                break;
            case LOWER:
                if (!stateUp.isOf(ModBlocks.CAT_TAIL)){break;}
                world.breakBlock(pos.up(), false);
                break;
        }
    }
}
