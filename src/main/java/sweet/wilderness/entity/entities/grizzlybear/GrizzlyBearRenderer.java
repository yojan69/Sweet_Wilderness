package sweet.wilderness.entity.entities.grizzlybear;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GrizzlyBearRenderer extends GeoEntityRenderer<GrizzlyBearEntity> {
    public GrizzlyBearRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new GrizzlyBearModel());
    }

    @Override
    public void render(GrizzlyBearEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
