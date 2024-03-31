package sweet.wilderness.entity.entities.crab;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class CrabModel extends GeoModel<CrabEntity> {
    @Override
    public Identifier getModelResource(CrabEntity animatable) {
        return new Identifier(MOD_ID, "geo/crab.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrabEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/crab.png");
    }

    @Override
    public Identifier getAnimationResource(CrabEntity animatable) {
        return new Identifier(MOD_ID, "animations/crab.animation.json");
    }

    @Override
    public void setCustomAnimations(CrabEntity animatable, long instanceId, AnimationState<CrabEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
