package sweet.wilderness.entity.entities.tortoise;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class TortoiseModel extends GeoModel<TortoiseEntity> {
    @Override
    public Identifier getModelResource(TortoiseEntity animatable) {
        return new Identifier(MOD_ID, "geo/tortoise.geo.json");
    }

    @Override
    public Identifier getTextureResource(TortoiseEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/tortoise.png");
    }

    @Override
    public Identifier getAnimationResource(TortoiseEntity animatable) {
        return new Identifier(MOD_ID, "animations/tortoise.animation.json");
    }

    @Override
    public void setCustomAnimations(TortoiseEntity animatable, long instanceId, AnimationState<TortoiseEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
