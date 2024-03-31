package sweet.wilderness.entity.entities.worm;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.entity.ai.goal.FleeAndDigGoal;
import sweet.wilderness.item.ModItems;

import java.util.Optional;

public class WormEntity extends AnimalEntity implements GeoEntity {
    public static final float MOVEMENT_SPEED = 0.160f;
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public WormEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, MOVEMENT_SPEED);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new FleeAndDigGoal(this, getWorld()));
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(6, new LookAroundGoal(this));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.WORM.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));

        RawAnimation digAnim = RawAnimation.begin().then("dig", Animation.LoopType.HOLD_ON_LAST_FRAME);

        controllers.add(new AnimationController<>(this, "worm_controller", state -> PlayState.STOP)
                .triggerableAnim("dig", digAnim));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> animationState) {
        if (animationState.isMoving()) {
            animationState.getController().
                    setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
        }
        else{
            animationState.getController().
                    setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
        }

        return PlayState.CONTINUE;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return tryPot(player, hand, this).orElse(super.interactMob(player, hand));
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private static Optional<ActionResult> tryPot(PlayerEntity player, Hand hand, LivingEntity entity){
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.getItem() == Items.FLOWER_POT && entity.isAlive()){
            entity.playSound(SoundEvents.ITEM_BUCKET_FILL_AXOLOTL, 1.0f, 1.0f);
            ItemStack wormItemStack = new ItemStack(ModItems.WORM_ON_A_POT);
            ItemStack itemStack3 = ItemUsage.exchangeStack(itemStack, player, wormItemStack, false);
            player.setStackInHand(hand, itemStack3);
            entity.discard();
            World world = entity.getWorld();
            return Optional.of(ActionResult.success(world.isClient));
        }
        return Optional.empty();
    }
}
