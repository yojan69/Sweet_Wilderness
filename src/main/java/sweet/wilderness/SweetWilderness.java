package sweet.wilderness;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sweet.wilderness.block.ModBlocks;
import sweet.wilderness.entity.entities.chimpanzee.ChimpanzeeEntity;
import sweet.wilderness.entity.entities.crab.CrabEntity;
import sweet.wilderness.entity.entities.grizzlybear.GrizzlyBearEntity;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.entity.entities.tortoise.TortoiseEntity;
import sweet.wilderness.entity.entities.worm.WormEntity;
import sweet.wilderness.entity.entities.crow.CrowEntity;
import sweet.wilderness.entity.entities.capybara.CapybaraEntity;
import sweet.wilderness.item.ModItems;
import sweet.wilderness.sound.ModSounds;
import sweet.wilderness.world.gen.EntityGeneration;

public class SweetWilderness implements ModInitializer {
    public static final String MOD_ID = "sweet_wilderness";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		FabricDefaultAttributeRegistry.register(ModEntities.GRIZZLYBEAR, GrizzlyBearEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WORM, WormEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CROW, CrowEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CAPYBARA, CapybaraEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CHIMPANZEE, ChimpanzeeEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.TORTOISE, TortoiseEntity.setAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.CRAB, CrabEntity.setAttributes());
		//FabricDefaultAttributeRegistry.register(Entities.NARWHAL, NarwhalEntity.setAttributes());
		EntityGeneration.addSpawns();

		ModItems.registerModItems();
		ModSounds.registerSounds();
		ModBlocks.registerModBlocks();

		LOGGER.info("Initializing Sweet Wilderness :D");
	}
}