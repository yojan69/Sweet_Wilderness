package sweet.wilderness.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.entity.Bucketable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import sweet.wilderness.entity.entities.crab.CrabEntity;

@Mixin(Bucketable.class)
public interface BucketableMixin {
    @ModifyExpressionValue(method = "tryBucket", at = @At(value = "FIELD", target = "Lnet/minecraft/item/Items;WATER_BUCKET:Lnet/minecraft/item/Item;"))
    private static Item voo(Item original, @Local LivingEntity entity){
        if (entity instanceof CrabEntity){
            return Items.BUCKET;
        }
        else{
            return Items.WATER_BUCKET;
        }
    }
}
