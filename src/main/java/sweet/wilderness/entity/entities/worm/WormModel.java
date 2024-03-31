package sweet.wilderness.entity.entities.worm;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class WormModel extends GeoModel<WormEntity> {
    @Override
    public Identifier getModelResource(WormEntity animatable) {
        return new Identifier(MOD_ID, "geo/worm.geo.json");
    }

    @Override
    public Identifier getTextureResource(WormEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/worm.png");
    }

    @Override
    public Identifier getAnimationResource(WormEntity animatable) {
        return new Identifier(MOD_ID, "animations/worm.animation.json");
    }

    @Override
    public void setCustomAnimations(WormEntity animatable, long instanceId, AnimationState<WormEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
