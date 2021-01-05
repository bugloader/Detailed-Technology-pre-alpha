package detailedTechnology.code;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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

public class Registration {

    public static String MOD_ID = "dt";

    public static String getIdName(String name) {
        return name.toLowerCase().replaceAll(" ", "_");
    }

    public static void item(String name, Item item) {
        String idName = getIdName(name);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, idName), item);
        System.out.println("\"item." + MOD_ID + "." + idName + "\":\"" + name + "\",");
    }

    public static void block(String name, Block block) {
        String idName = getIdName(name);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, idName), block);
        System.out.println("\"block." + MOD_ID + "." + idName + "\":\"" + name + "\",");
    }

    public static void blockWithItem(String name, Block block, ItemGroup itemGroup) {
        String idName = getIdName(name);
        Registry.register(Registry.BLOCK, new Identifier(MOD_ID, idName), block);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, idName),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
        System.out.println("\"block." + MOD_ID + "." + idName + "\":\"" + name + "\",");
    }

    public static void stoneOre(String oreName, Block oreBlock, ItemGroup itemGroup, int size, int repeat,
                                 int bottomOffset, int topOffset, int maximum) {
        ConfiguredFeature<?, ?> tempFeature = Feature.ORE.configure(new OreFeatureConfig(
                OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, oreBlock.getDefaultState(), size))
                .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(bottomOffset, topOffset, maximum)))
                .spreadHorizontally().repeat(repeat);
        RegistryKey<ConfiguredFeature<?, ?>> tempKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_" + oreName.toLowerCase() + "_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, tempKey.getValue(), tempFeature);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, tempKey);
        Registration.blockWithItem(oreName + " Ore", oreBlock, itemGroup);
    }

}
