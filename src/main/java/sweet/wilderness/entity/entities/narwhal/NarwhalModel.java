package sweet.wilderness.entity.entities.narwhal;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class NarwhalModel extends GeoModel<NarwhalEntity> {
    @Override
    public Identifier getModelResource(NarwhalEntity animatable) {
        return new Identifier(MOD_ID, "geo/narwhal.geo.json");
    }

    @Override
    public Identifier getTextureResource(NarwhalEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/narwhal.png");
    }

    @Override
    public Identifier getAnimationResource(NarwhalEntity animatable) {
        return new Identifier(MOD_ID, "animations/narwhal.animation.json");
    }

    @Override
    public void setCustomAnimations(NarwhalEntity animatable, long instanceId, AnimationState<NarwhalEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
