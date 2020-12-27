package detailedTechnology.group.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Ores {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "ore"),() -> new ItemStack(Items.FURNACE));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Block copperOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block copperBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block tinOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block tinBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,4f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block bronzeBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,6f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));

    public static final Item copperIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperDrop = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperRod = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item tinIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinRod = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzePlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeRod = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item ironSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironDust = new Item(new FabricItemSettings().group(ITEM_GROUP));

    private static final ConfiguredFeature<?, ?> oreCopperOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,copperOreBlock.getDefaultState(),32))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(40,40,60)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oreTinOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,tinOreBlock.getDefaultState(),16))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30,30,55)))
            .spreadHorizontally().repeat(2);

    static
    {
        Registration.blockWithItem("Copper Ore",copperOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Copper Block",copperBlock, ITEM_GROUP);
        Registration.blockWithItem("Tin Ore",tinOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Tin Block",tinBlock, ITEM_GROUP);
        Registration.blockWithItem("Bronze Block",bronzeBlock, ITEM_GROUP);

        Registration.item("Copper Ingot", copperIngot);
        Registration.item("Copper Drop", copperDrop);
        Registration.item("Copper Nugget", copperNugget);
        Registration.item("Copper Sharp", copperSharp);
        Registration.item("Copper Dust", copperDust);
        Registration.item("Copper Plate", copperPlate);
        Registration.item("Copper Rod", copperRod);

        Registration.item("Tin Ingot", tinIngot);
        Registration.item("Tin Nugget", tinNugget);
        Registration.item("Tin Sharp", tinSharp);
        Registration.item("Tin Dust", tinDust);
        Registration.item("Tin Plate", tinPlate);
        Registration.item("Tin Rod", tinRod);

        Registration.item("Bronze Ingot", bronzeIngot);
        Registration.item("Bronze Nugget", bronzeNugget);
        Registration.item("Bronze Sharp", bronzeSharp);
        Registration.item("Bronze Dust", bronzeDust);
        Registration.item("Bronze Plate", bronzePlate);
        Registration.item("Bronze Rod", bronzeRod);

        Registration.item("Iron Sharp", ironSharp);
        Registration.item("Iron Dust", ironDust);

        RegistryKey<ConfiguredFeature<?, ?>> oreCopperOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_copper_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreCopperOverworld.getValue(), oreCopperOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreCopperOverworld);
        RegistryKey<ConfiguredFeature<?, ?>> oreTinOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_tin_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTinOverworld.getValue(), oreTinOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTinOverworld);
    }
}
