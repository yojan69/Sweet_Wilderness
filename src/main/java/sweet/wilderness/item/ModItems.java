package sweet.wilderness.item;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import sweet.wilderness.SweetWilderness;
import sweet.wilderness.block.ModBlocks;
import sweet.wilderness.entity.ModEntities;
import sweet.wilderness.item.items.ModFoodComponents;
import sweet.wilderness.item.items.WormPotItem;
import sweet.wilderness.sound.ModSounds;

public class ModItems {

    public static final Item HONEY_AND_SALMON_STEW = registerItem("honey_and_salmon_stew",
            new Item(new FabricItemSettings().food(ModFoodComponents.HONEY_AND_SALMON_STEW)));
    public static final Item HONEY_PIE = registerItem("honey_pie",
            new Item(new FabricItemSettings().food(ModFoodComponents.HONEY_AND_SALMON_STEW)));

    public static final Item TORTOISE_SHELL = registerItem("tortoise_shell",
            new Item(new FabricItemSettings()));
    public static final Item CRAB_BUCKET = registerItem("crab_bucket",
            new EntityBucketItem(ModEntities.CRAB,
                    Fluids.EMPTY,
                    SoundEvents.ITEM_BUCKET_EMPTY_FISH, new FabricItemSettings().maxCount(1)));
    public static final Item WORM_ON_A_POT = registerItem("worm_on_a_pot",
            new WormPotItem(new FabricItemSettings().maxCount(16)));

    public static final Item MUSIC_DISC_ECOSYSTEM = registerItem("music_disc_ecosystem",
            new MusicDiscItem(1, ModSounds.MUSIC_DISC_ECOSYSTEM,
                    new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 167));

    public static final Item MUSIC_DISC_ROOTED_SWAMP = registerItem("music_disc_rooted_swamp",
            new MusicDiscItem(1, ModSounds.MUSIC_DISC_ROOTED_SWAMP,
                    new FabricItemSettings().maxCount(1).rarity(Rarity.RARE), 167));

    public static final Item CAPYBARA_SPAWN_EGG = registerItem("capybara_spawn_egg",
            new SpawnEggItem(ModEntities.CAPYBARA, 0xBA884E, 0x815f3a, new FabricItemSettings()));

    public static final Item CHIMPANZEE_SPAWN_EGG = registerItem("chimpanzee_spawn_egg",
            new SpawnEggItem(ModEntities.CHIMPANZEE, 0xDAAA84, 0x383632, new FabricItemSettings()));

    public static final Item CRAB_SPAWN_EGG = registerItem("crab_spawn_egg",
            new SpawnEggItem(ModEntities.CRAB, 0x5E3D2A, 0x84664E, new FabricItemSettings()));

    public static final Item CROW_SPAWN_EGG = registerItem("crow_spawn_egg",
            new SpawnEggItem(ModEntities.CROW, 0x5E3D2A, 0x84664E, new FabricItemSettings()));

    public static final Item GRIZZLYBEAR_SPAWN_EGG = registerItem("grizzlybear_spawn_egg",
            new SpawnEggItem(ModEntities.GRIZZLYBEAR, 0x5E3D2A, 0x84664E, new FabricItemSettings()));

    public static final Item TORTOISE_SPAWN_EGG = registerItem("tortoise_spawn_egg",
            new SpawnEggItem(ModEntities.TORTOISE, 0xA49F3B, 0x614E3B, new FabricItemSettings()));

    public static final Item WORM_SPAWN_EGG = registerItem("worm_spawn_egg",
            new SpawnEggItem(ModEntities.WORM, 0xC47487, 0xA65D65, new FabricItemSettings()));

    private static void addItemsToFoodAndDrinkTabItemGroup(FabricItemGroupEntries entries){
        entries.add(HONEY_AND_SALMON_STEW);
        entries.add(HONEY_PIE);
    }
    private static void addItemsToUtilsAndToolsTabItemGroup(FabricItemGroupEntries entries){
        entries.add(CRAB_BUCKET);
        entries.add(WORM_ON_A_POT);
        entries.add(TORTOISE_SHELL);
        entries.add(ModBlocks.TORTOISE_EGG);
        entries.add(MUSIC_DISC_ECOSYSTEM);
        entries.add(MUSIC_DISC_ROOTED_SWAMP);

        entries.add(CAPYBARA_SPAWN_EGG);
        entries.add(CHIMPANZEE_SPAWN_EGG);
        entries.add(CRAB_SPAWN_EGG);
        entries.add(CROW_SPAWN_EGG);
        entries.add(GRIZZLYBEAR_SPAWN_EGG);
        entries.add(TORTOISE_SPAWN_EGG);
        entries.add(WORM_SPAWN_EGG);
    }
    private static void addItemsToNaturalTabItemGroup(FabricItemGroupEntries entries){
        entries.add(ModBlocks.DANDELION);
        entries.add(ModBlocks.CAT_TAIL);
    }

    public static Item registerItem(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(SweetWilderness.MOD_ID, name), item);
    }

    public static void registerModItems(){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModItems::addItemsToFoodAndDrinkTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(ModItems::addItemsToUtilsAndToolsTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(ModItems::addItemsToNaturalTabItemGroup);

        SweetWilderness.LOGGER.info("Registered items for Sweet Wilderness :D");
    }
}
