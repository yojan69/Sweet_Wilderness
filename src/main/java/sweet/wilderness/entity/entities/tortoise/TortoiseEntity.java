package sweet.wilderness.entity.entities.tortoise;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
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
import sweet.wilderness.entity.ai.goal.TortoiseLookAroundGoal;
import sweet.wilderness.entity.ai.goal.TortoiseSwimGoal;
import sweet.wilderness.entity.ai.goal.TortoiseWanderAroundFarGoal;

public class TortoiseEntity extends AnimalEntity implements GeoEntity {
    private AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);

    public boolean isHiding() {
        return hiding;
    }

    private boolean hiding;
    private int hidingTimer = 200;
    private boolean spinning;

    private enum tortoiseState {
        normal,
        shell
    }
    private tortoiseState state = tortoiseState.normal;

    public static final float MOVEMENT_SPEED = .2049f;

    public TortoiseEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return AnimalEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 7)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, MOVEMENT_SPEED);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new TortoiseSwimGoal(this));
        this.goalSelector.add(1, new TortoiseWanderAroundFarGoal(this, 0.75f, 1f));
        this.goalSelector.add(1, new TortoiseLookAroundGoal(this));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.TORTOISE.create(world);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, "controller", 5, this::predicate));

        RawAnimation shellHide = RawAnimation.begin().then("shell_hide", Animation.LoopType.PLAY_ONCE);
        ///RAHH UNHIDE AINT EVEN A WORD BUT WE AINT GON CARE
        RawAnimation shellUnhide = RawAnimation.begin().then("shell_unhide", Animation.LoopType.PLAY_ONCE);

        controllers.add(new AnimationController<>(this, "shell_controller", state -> PlayState.STOP)
                .triggerableAnim("hide", shellHide)
                .triggerableAnim("unhide", shellUnhide));
    }

    private <E extends GeoAnimatable> PlayState predicate(AnimationState<E> animationState) {
        if (this.state == tortoiseState.normal){
            if (animationState.isMoving()) {
                animationState.getController().
                        setAnimation(RawAnimation.begin().then("move", Animation.LoopType.LOOP));
            }
            else if (!animationState.isMoving()){
                animationState.getController().
                        setAnimation(RawAnimation.begin().then("idle", Animation.LoopType.LOOP));
            }
        }
        else if (this.state == tortoiseState.shell){
            if (!this.spinning){
                animationState.getController().
                        setAnimation(RawAnimation.begin().then("shell_idle", Animation.LoopType.LOOP));
            }
            else {
                animationState.getController().
                        setAnimation(RawAnimation.begin().then("shell_spin", Animation.LoopType.LOOP));
            }
        }

        return PlayState.CONTINUE;
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (hiding){
            HideInShell();
        }
        return super.damage(source, amount);
    }

    @Override
    public void tick() {
        super.tick();

        if (hiding)
        {
            if (--this.hidingTimer == 0) {
                UnhideFromShell();
            }
        }
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    private void HideInShell(){
        this.hiding = true;
        this.getNavigation().stop();

        this.triggerAnim("shell_controller", "hide");
        this.state = tortoiseState.shell;
    }
    private void UnhideFromShell(){
        this.hiding = false;
        hidingTimer = 200;

        this.triggerAnim("shell_controller", "unhide");
        this.state = tortoiseState.normal;
    }
}
