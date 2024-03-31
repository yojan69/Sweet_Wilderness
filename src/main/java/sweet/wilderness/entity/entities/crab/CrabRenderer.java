package sweet.wilderness.entity.entities.crab;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class CrabRenderer extends GeoEntityRenderer<CrabEntity> {
    public CrabRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new CrabModel());
    }

    @Override
    public void render(CrabEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
