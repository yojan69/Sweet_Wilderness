package sweet.wilderness.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.FluidFillable;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import sweet.wilderness.item.ModItems;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin {
    @Shadow public abstract void onEmptied(@Nullable PlayerEntity player, World world, ItemStack stack, BlockPos pos);

    @Shadow @Final private Fluid fluid;

    @Inject(method = "use",
            slice = @Slice(from = @At(value = "FIELD", target = "Lnet/minecraft/fluid/Fluids;EMPTY:Lnet/minecraft/fluid/Fluid;")),
    at = @At(value = "FIELD", target = "Lnet/minecraft/fluid/Fluids;EMPTY:Lnet/minecraft/fluid/Fluid;", ordinal = 1), cancellable = true)
    private void hehe(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir,
                      @Local BlockHitResult blockHitResult){
        if ((user.getStackInHand(hand)).getItem() == ModItems.CRAB_BUCKET){
            BlockPos blockPos = world.getBlockState(blockHitResult.getBlockPos())
                    .getBlock() instanceof FluidFillable
                    && fluid == Fluids.WATER
                    ? blockHitResult.getBlockPos()
                    : blockHitResult.getBlockPos().offset(blockHitResult.getSide());

            onEmptied(user, world, user.getStackInHand(hand), blockPos);

            cir.setReturnValue(TypedActionResult.success(BucketItem.getEmptiedStack(user.getStackInHand(hand), user), world.isClient()));
            cir.cancel();
        }
    }
}
