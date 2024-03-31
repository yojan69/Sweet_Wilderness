package sweet.wilderness;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import sweet.wilderness.datagen.ItemTagProvider;
import sweet.wilderness.datagen.LootTableProvider;
import sweet.wilderness.datagen.ModelProvider;

public class SweetWildernessDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(ModelProvider::new);
		pack.addProvider(ItemTagProvider::new);
		pack.addProvider(LootTableProvider::new);
	}
}
