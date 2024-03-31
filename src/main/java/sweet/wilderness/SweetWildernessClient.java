package sweet.wilderness;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import sweet.wilderness.block.ModBlocks;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.entity.entities.chimpanzee.ChimpanzeeRenderer;
import sweet.wilderness.entity.entities.crab.CrabRenderer;
import sweet.wilderness.entity.entities.grizzlybear.GrizzlyBearRenderer;
import sweet.wilderness.entity.entities.tortoise.TortoiseRenderer;
import sweet.wilderness.entity.entities.worm.WormRenderer;
import sweet.wilderness.entity.entities.crow.CrowRenderer;
import sweet.wilderness.entity.entities.capybara.CapybaraRenderer;

public class SweetWildernessClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.register(ModEntities.GRIZZLYBEAR, GrizzlyBearRenderer::new);
		EntityRendererRegistry.register(ModEntities.WORM, WormRenderer::new);
		EntityRendererRegistry.register(ModEntities.CROW, CrowRenderer::new);
		EntityRendererRegistry.register(ModEntities.CAPYBARA, CapybaraRenderer::new);
		EntityRendererRegistry.register(ModEntities.CHIMPANZEE, ChimpanzeeRenderer::new);
		EntityRendererRegistry.register(ModEntities.TORTOISE, TortoiseRenderer::new);
		EntityRendererRegistry.register(ModEntities.CRAB, CrabRenderer::new);
		//EntityRendererRegistry.register(Entities.NARWHAL, NarwhalRenderer::new);

		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DANDELION, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.POTTED_DANDELION, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.GANODERMA_MUSHROOM, RenderLayer.getCutout());
		BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.CAT_TAIL, RenderLayer.getCutout());
	}
}