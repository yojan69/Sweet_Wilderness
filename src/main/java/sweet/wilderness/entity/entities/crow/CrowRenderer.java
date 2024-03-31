package sweet.wilderness.entity.entities.crow;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrowRenderer extends GeoEntityRenderer<CrowEntity> {
    public CrowRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrowModel());
    }

    @Override
    public void render(CrowEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
