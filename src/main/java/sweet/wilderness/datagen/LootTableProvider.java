package sweet.wilderness.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Blocks;
import sweet.wilderness.block.ModBlocks;

public class LootTableProvider extends FabricBlockLootTableProvider {
    public LootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.DANDELION);
        addDrop(ModBlocks.POTTED_DANDELION, Blocks.FLOWER_POT);
        addDrop(ModBlocks.POTTED_DANDELION, ModBlocks.DANDELION);
        addDrop(ModBlocks.GANODERMA_MUSHROOM);
        addDrop(ModBlocks.CAT_TAIL);
    }
}
