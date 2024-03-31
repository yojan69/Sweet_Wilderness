package sweet.wilderness.entity.entities.grizzlybear;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class GrizzlyBearModel extends GeoModel<GrizzlyBearEntity> {
    @Override
    public Identifier getModelResource(GrizzlyBearEntity animatable) {
        return new Identifier(MOD_ID, "geo/grizzlybear.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrizzlyBearEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/grizzlybear.png");
    }

    @Override
    public Identifier getAnimationResource(GrizzlyBearEntity animatable) {
        return new Identifier(MOD_ID, "animations/grizzlybear.animation.json");
    }

    @Override
    public void setCustomAnimations(GrizzlyBearEntity animatable, long instanceId, AnimationState<GrizzlyBearEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
