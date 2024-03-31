package sweet.wilderness.world.gen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;
import net.minecraft.world.Heightmap;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import sweet.wilderness.entity.ModEntities;

public class EntityGeneration {

    public static void addSpawns(){
        MobSpawn(ModEntities.GRIZZLYBEAR, SpawnGroup.CREATURE, 2, 3, 5, BiomeKeys.PLAINS, BiomeKeys.BIRCH_FOREST, BiomeKeys.TAIGA,
                BiomeKeys.OLD_GROWTH_PINE_TAIGA, BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA, BiomeKeys.CHERRY_GROVE);

        MobSpawn(ModEntities.CAPYBARA, SpawnGroup.CREATURE, 4, 5, 6, BiomeKeys.MANGROVE_SWAMP, BiomeKeys.SWAMP);
        MobSpawn(ModEntities.CROW, SpawnGroup.CREATURE, 3, 4, 3, BiomeKeys.DARK_FOREST);
        MobSpawn(ModEntities.CHIMPANZEE, SpawnGroup.CREATURE, 4, 5, 6, BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE);
        MobSpawn(ModEntities.TORTOISE, SpawnGroup.CREATURE, 2, 3, 5, BiomeKeys.JUNGLE, BiomeKeys.BAMBOO_JUNGLE);
    }

    public static boolean isValidNaturalSpawn(EntityType<? extends AnimalEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        boolean condition = SpawnReason.isTrialSpawner(spawnReason) || isLightLevelValidForNaturalSpawn(world, pos);
        if (condition) System.out.println("shouldve spawned, pos: "+pos);
        return world.getBlockState(pos.down()).isIn(BlockTags.ANIMALS_SPAWNABLE_ON) && condition;
    }

    protected static boolean isLightLevelValidForNaturalSpawn(BlockRenderView world, BlockPos pos) {
        return world.getBaseLightLevel(pos, 0) > 8;
    }

    @SafeVarargs
    private static void MobSpawn(EntityType<? extends AnimalEntity> entityType, SpawnGroup spawnGroup,
                                 int minGroupSize, int maxGroupSize, int weight, RegistryKey<Biome>... keys){
        BiomeModifications.addSpawn(BiomeSelectors.includeByKey(
                        keys),
                spawnGroup,
                entityType,
                weight, minGroupSize, maxGroupSize);

        SpawnRestriction.register(entityType, SpawnRestriction.Location.ON_GROUND,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, EntityGeneration::isValidNaturalSpawn);
    }
}
