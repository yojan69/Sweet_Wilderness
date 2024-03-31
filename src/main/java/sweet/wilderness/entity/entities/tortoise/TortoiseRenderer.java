package sweet.wilderness.entity.entities.tortoise;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class TortoiseRenderer extends GeoEntityRenderer<TortoiseEntity> {
    public TortoiseRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new TortoiseModel());
    }

    @Override
    public void render(TortoiseEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
