package sweet.wilderness.entity.entities.grizzlybear;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.entity.ai.goal.CatchFishGoal;

import java.util.Random;
import java.util.UUID;

public class GrizzlyBearEntity extends AnimalEntity implements GeoEntity, Angerable {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public static final float MOVEMENT_SPEED = 0.267f;

    Random random = new Random();
    private int fakeHungerHEHE;
    private boolean catchingFish = false;

    public GrizzlyBearEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
        fakeHungerHEHE = 100;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 60.00)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.5f)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 2.30f)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, MOVEMENT_SPEED);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(2, new CatchFishGoal(this));
        this.goalSelector.add(3, new MeleeAttackGoal(this, 1.2D, false));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.75f, 1));
        this.goalSelector.add(6, new LookAroundGoal(this));

        this.targetSelector.add(4, new ActiveTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::shouldAngerAt));
        this.targetSelector.add(3, new ActiveTargetGoal<>(this, AnimalEntity.class , true,
                livingEntity->!(livingEntity instanceof GrizzlyBearEntity)));
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.GRIZZLYBEAR.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));
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
    public void tick() {
        super.tick();
        if (this.getWorld().isClient){return;}

    }

    private void hung(int hunger){
        fakeHungerHEHE -= hunger;
        System.out.println(fakeHungerHEHE);

        if (fakeHungerHEHE <= 0){
            catchingFish = true;
            System.out.println(catchingFish);
        }
    }

    public boolean isCatchingFish() {
        return catchingFish;
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    @Override
    public int getAngerTime() {
        return 0;
    }

    @Override
    public void setAngerTime(int angerTime) {

    }

    @Nullable
    @Override
    public UUID getAngryAt() {
        return null;
    }

    @Override
    public void setAngryAt(@Nullable UUID angryAt) {

    }

    @Override
    public void chooseRandomAngerTime() {

    }
}
