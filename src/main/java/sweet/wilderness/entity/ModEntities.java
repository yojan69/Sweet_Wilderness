package sweet.wilderness.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import sweet.wilderness.entity.entities.chimpanzee.ChimpanzeeEntity;
import sweet.wilderness.entity.entities.crab.CrabEntity;
import sweet.wilderness.entity.entities.grizzlybear.GrizzlyBearEntity;
import sweet.wilderness.entity.entities.tortoise.TortoiseEntity;
import sweet.wilderness.entity.entities.worm.WormEntity;
import sweet.wilderness.entity.entities.crow.CrowEntity;
import sweet.wilderness.entity.entities.capybara.CapybaraEntity;

import static sweet.wilderness.SweetWilderness.MOD_ID;

public class ModEntities {
    public static final EntityType<GrizzlyBearEntity> GRIZZLYBEAR = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "grizzlybear"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, GrizzlyBearEntity::new)
                    .dimensions(EntityDimensions.fixed(1.625f, 1.4375f)).build());
    public static final EntityType<WormEntity> WORM = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "worm"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, WormEntity::new)
                    .dimensions(EntityDimensions.fixed(0.5f, 0.125f)).build());
    public static final EntityType<CrowEntity> CROW = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "crow"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrowEntity::new)
                    .dimensions(EntityDimensions.fixed(.5f, 0.625f)).build());
    public static final EntityType<CapybaraEntity> CAPYBARA = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "capybara"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CapybaraEntity::new)
                    .dimensions(EntityDimensions.fixed(0.625f, 0.8125f)).build());
    public static final EntityType<ChimpanzeeEntity> CHIMPANZEE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "chimpanzee"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, ChimpanzeeEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, 1f)).build());
    public static final EntityType<TortoiseEntity> TORTOISE = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "tortoise"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, TortoiseEntity::new)
                    .dimensions(EntityDimensions.fixed(1.125f, 0.4375f)).build());
    public static final EntityType<CrabEntity> CRAB = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "crab"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, CrabEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, 0.6875F)).build());
    /*public static final EntityType<NarwhalEntity> NARWHAL = Registry.register(
            Registries.ENTITY_TYPE, new Identifier(MOD_ID, "narwhal"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, NarwhalEntity::new)
                    .dimensions(EntityDimensions.fixed(.75f, 0.8125f)).build());*/
}
