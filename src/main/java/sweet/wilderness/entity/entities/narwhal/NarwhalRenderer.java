package sweet.wilderness.entity.entities.narwhal;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class NarwhalRenderer extends GeoEntityRenderer<NarwhalEntity> {
    public NarwhalRenderer(EntityRendererFactory.Context renderManager) {
        super(renderManager, new NarwhalModel());
    }

    @Override
    public void render(NarwhalEntity entity, float entityYaw, float partialTick,
                       MatrixStack poseStack, VertexConsumerProvider bufferSource, int packedLight) {
        if (entity.isBaby()){
            poseStack.scale(.4f, .4f, .4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
