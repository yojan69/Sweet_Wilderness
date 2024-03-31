package sweet.wilderness.entity.entities.crow;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class CrowModel extends GeoModel<CrowEntity> {
    @Override
    public Identifier getModelResource(CrowEntity animatable) {
        return new Identifier(MOD_ID, "geo/crow.geo.json");
    }

    @Override
    public Identifier getTextureResource(CrowEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/crow.png");
    }

    @Override
    public Identifier getAnimationResource(CrowEntity animatable) {
        return new Identifier(MOD_ID, "animations/crow.animation.json");
    }

    @Override
    public void setCustomAnimations(CrowEntity animatable, long instanceId, AnimationState<CrowEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
