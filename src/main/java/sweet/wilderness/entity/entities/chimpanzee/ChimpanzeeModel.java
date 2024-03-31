package sweet.wilderness.entity.entities.chimpanzee;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class ChimpanzeeModel extends GeoModel<ChimpanzeeEntity> {
    @Override
    public Identifier getModelResource(ChimpanzeeEntity animatable) {
        return new Identifier(MOD_ID, "geo/chimpanzee.geo.json");
    }

    @Override
    public Identifier getTextureResource(ChimpanzeeEntity animatable) {
        return new Identifier(MOD_ID, "textures/entity/chimpanzee.png");
    }

    @Override
    public Identifier getAnimationResource(ChimpanzeeEntity animatable) {
        return new Identifier(MOD_ID, "animations/chimpanzee.animation.json");
    }

    @Override
    public void setCustomAnimations(ChimpanzeeEntity animatable, long instanceId, AnimationState<ChimpanzeeEntity> animationState) {
        CoreGeoBone head = getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);
            head.setRotX(entityData.headPitch() * MathHelper.RADIANS_PER_DEGREE);
            head.setRotY(entityData.netHeadYaw() * MathHelper.RADIANS_PER_DEGREE);
        }
    }}
