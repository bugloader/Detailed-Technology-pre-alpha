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
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block leadOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block leadBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block nickelOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block nickelBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block aluminiumOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block aluminiumBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block silverOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block silverBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block platinumOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block platinumBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block uraniumOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block uraniumBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block wolframiteOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block wolframiteBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block bronzeBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,6f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block steelBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(6f,10f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));

    public static final Item copperIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item silverIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item aluminiumIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item platinumIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item steelIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item silverNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item aluminiumNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item platinumNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item steelNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item silverSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item aluminiumSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item platinumSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item silverDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item aluminiumDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item platinumDust = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeDust = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperDrop = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item tinPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item ironPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzePlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item steelPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item steelRod = new Item(new FabricItemSettings().group(ITEM_GROUP));


    static
    {
        Registration.stoneOre("Copper",copperOreBlock,ITEM_GROUP,16,2,40,40,60);
        Registration.stoneOre("Tin",tinOreBlock,ITEM_GROUP,8,2,30,30,55);
        Registration.stoneOre("Lead",leadOreBlock,ITEM_GROUP,8,2,40,40,70);
        Registration.stoneOre("Nickel",nickelOreBlock,ITEM_GROUP,8,2,30,30,60);
        Registration.stoneOre("Aluminium",aluminiumOreBlock,ITEM_GROUP,8,2,30,30,60);
        Registration.stoneOre("Silver",silverOreBlock,ITEM_GROUP,4,2,10,10,30);
        Registration.stoneOre("Platinum",platinumOreBlock,ITEM_GROUP,3,2,5,5,20);
        Registration.stoneOre("Uranium",uraniumOreBlock,ITEM_GROUP,2,2,1,1,15);
        Registration.stoneOre("Wolframite",wolframiteOreBlock,ITEM_GROUP,2,2,1,1,10);

        Registration.blockWithItem("Copper Block",copperBlock, ITEM_GROUP);
        Registration.blockWithItem("Tin Block",tinBlock, ITEM_GROUP);
        Registration.blockWithItem("Nickel Block",nickelBlock, ITEM_GROUP);
        Registration.blockWithItem("Aluminium Block",aluminiumBlock, ITEM_GROUP);
        Registration.blockWithItem("Lead Block",leadBlock, ITEM_GROUP);
        Registration.blockWithItem("Silver Block",silverBlock, ITEM_GROUP);
        Registration.blockWithItem("Platinum Block",platinumBlock, ITEM_GROUP);
        Registration.blockWithItem("Uranium Block",uraniumBlock, ITEM_GROUP);
        Registration.blockWithItem("Wolframite Block",wolframiteBlock, ITEM_GROUP);


        Registration.blockWithItem("Bronze Block",bronzeBlock, ITEM_GROUP);
        Registration.blockWithItem("Steel Block",steelBlock, ITEM_GROUP);

        Registration.item("Copper Ingot", copperIngot);
        Registration.item("Tin Ingot", tinIngot);
        Registration.item("Silver Ingot", silverIngot);
        Registration.item("Aluminium Ingot", aluminiumIngot);
        Registration.item("Platinum Ingot", platinumIngot);

        Registration.item("Bronze Ingot", bronzeIngot);
        Registration.item("Steel Ingot", steelIngot);

        Registration.item("Copper Nugget", copperNugget);
        Registration.item("Tin Nugget", tinNugget);
        Registration.item("Silver Nugget", silverNugget);
        Registration.item("Aluminium Nugget", aluminiumNugget);
        Registration.item("Platinum Nugget", platinumNugget);

        Registration.item("Bronze Nugget", bronzeNugget);
        Registration.item("Steel Nugget", steelNugget);

        Registration.item("Copper Sharp", copperSharp);
        Registration.item("Tin Sharp", tinSharp);
        Registration.item("Iron Sharp", ironSharp);
        Registration.item("Silver Sharp", silverSharp);
        Registration.item("Aluminium Sharp", aluminiumSharp);
        Registration.item("Platinum Sharp", platinumSharp);

        Registration.item("Bronze Sharp", bronzeSharp);

        Registration.item("Copper Dust", copperDust);
        Registration.item("Tin Dust", tinDust);
        Registration.item("Iron Dust", ironDust);
        Registration.item("Silver Dust", silverDust);
        Registration.item("Aluminium Dust", aluminiumDust);
        Registration.item("Platinum Dust", platinumDust);

        Registration.item("Bronze Dust", bronzeDust);

        Registration.item("Copper Drop", copperDrop);
        Registration.item("Copper Plate", copperPlate);
        Registration.item("Copper Rod", copperRod);
        Registration.item("Copper Gear",copperGear);

        Registration.item("Tin Plate", tinPlate);
        Registration.item("Tin Rod", tinRod);
        Registration.item("Tin Gear",tinGear);

        Registration.item("Bronze Plate", bronzePlate);
        Registration.item("Bronze Rod", bronzeRod);
        Registration.item("Bronze Gear",bronzeGear);

        Registration.item("Iron Plate",ironPlate);
        Registration.item("Iron Rod",ironRod);
        Registration.item("Iron Gear",ironGear);

        Registration.item("Steel Plate",steelPlate);
        Registration.item("Steel Rod",steelRod);

       }
}
