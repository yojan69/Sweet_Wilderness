package sweet.wilderness.entity.entities.worm;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class WormRenderer extends GeoEntityRenderer<WormEntity> {
    public WormRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new WormModel());
    }

    @Override
    public void render(WormEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
