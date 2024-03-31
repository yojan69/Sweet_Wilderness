package sweet.wilderness.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "attackLivingEntity", at = @At(value = "TAIL"))
    private void tortoiseSpinLogic(LivingEntity target, CallbackInfo ci){

        double x = this.getVelocity().x;
        double z = this.getVelocity().z;
        double magnitude = MathHelper.sqrt((float) (x * x + z * z));

        if (magnitude > 0.125){
        }
    }
}
