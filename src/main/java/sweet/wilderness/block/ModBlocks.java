package sweet.wilderness.block;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sweet.wilderness.block.blocks.CatTailBlock;
import sweet.wilderness.block.blocks.GanodermaMushroomBlock;
import sweet.wilderness.block.blocks.TortoiseEggBlock;
import sweet.wilderness.item.ModItems;

import static sweet.wilderness.SweetWilderness.LOGGER;
import static sweet.wilderness.SweetWilderness.MOD_ID;

public class ModBlocks {
    public static final Block TORTOISE_EGG = registerBlock("tortoise_egg",
            new TortoiseEggBlock(FabricBlockSettings.copyOf(Blocks.TURTLE_EGG)));

    public static final Block CAT_TAIL = registerBlock("cat_tail",
            new CatTailBlock(FabricBlockSettings.copyOf(Blocks.TALL_GRASS)));

    public static final Block DANDELION = registerBlock("dandelion",
            new FlowerBlock(StatusEffects.WEAKNESS, 9, FabricBlockSettings.copyOf(Blocks.WHITE_TULIP).nonOpaque()));

    public static final Block GANODERMA_MUSHROOM = registerBlock("ganoderma_mushroom",
            new GanodermaMushroomBlock(FabricBlockSettings.copyOf(DANDELION).nonOpaque()));



    public static final Block POTTED_DANDELION = Registry.register(Registries.BLOCK, new Identifier(MOD_ID, "potted_dandelion"),
            new FlowerPotBlock(DANDELION, FabricBlockSettings.copyOf(Blocks.POTTED_WHITE_TULIP).nonOpaque()));

    private static Block registerBlock(String name, Block block){
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block){
        return ModItems.registerItem(name, new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks(){
        LOGGER.info("Registered Blocks for Sweet Wilderness :D");
    }
}
