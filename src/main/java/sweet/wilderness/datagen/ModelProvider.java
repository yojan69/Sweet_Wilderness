package sweet.wilderness.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelIds;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import sweet.wilderness.SweetWilderness;
import sweet.wilderness.block.ModBlocks;
import sweet.wilderness.item.ModItems;

public class ModelProvider extends FabricModelProvider {
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerFlowerPotPlant(ModBlocks.DANDELION, ModBlocks.POTTED_DANDELION, BlockStateModelGenerator.TintType.NOT_TINTED);
        blockStateModelGenerator.registerDoubleBlock(ModBlocks.CAT_TAIL, new Identifier(SweetWilderness.MOD_ID, "block/cat_tail_upper"),
                new Identifier(SweetWilderness.MOD_ID, "block/cat_tail_lower"));

        blockStateModelGenerator.registerParentedItemModel(ModItems.CAPYBARA_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.CHIMPANZEE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.CRAB_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.CROW_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.GRIZZLYBEAR_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.TORTOISE_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
        blockStateModelGenerator.registerParentedItemModel(ModItems.WORM_SPAWN_EGG, ModelIds.getMinecraftNamespacedItem("template_spawn_egg"));
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.HONEY_AND_SALMON_STEW, Models.GENERATED);
        itemModelGenerator.register(ModItems.HONEY_PIE, Models.GENERATED);

        itemModelGenerator.register(ModItems.TORTOISE_SHELL, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRAB_BUCKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.WORM_ON_A_POT, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ECOSYSTEM, Models.GENERATED);
        itemModelGenerator.register(ModItems.MUSIC_DISC_ROOTED_SWAMP, Models.GENERATED);

        itemModelGenerator.register(ModBlocks.TORTOISE_EGG.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.CAT_TAIL.asItem(), Models.GENERATED);
        itemModelGenerator.register(ModBlocks.GANODERMA_MUSHROOM.asItem(), Models.GENERATED);
    }
}
