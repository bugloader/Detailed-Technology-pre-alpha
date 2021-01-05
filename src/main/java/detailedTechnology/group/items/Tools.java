package detailedTechnology.group.items;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import detailedTechnology.items.material.DetailedToolMaterial;
import detailedTechnology.items.toolItem.*;
import detailedTechnology.items.toolItem.crafting.HammerItem;
import detailedTechnology.items.toolItem.crafting.RasperItem;
import detailedTechnology.items.toolItem.crafting.SawItem;
import detailedTechnology.items.toolItem.WrenchItem;
import detailedTechnology.items.generalclass.*;
import detailedTechnology.items.toolItem.instant.KnifeItem;
import detailedTechnology.items.toolItem.vanilla.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class Tools {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "tool"),() -> new ItemStack(Items.DIAMOND_PICKAXE));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final KnifeItem flintKnife = new KnifeItem(ToolMaterials.STONE,ITEM_GROUP);
    public static final KnifeItem copperKnife = new KnifeItem(DetailedToolMaterial.COPPER,ITEM_GROUP);
    public static final KnifeItem bronzeKnife = new KnifeItem(DetailedToolMaterial.BRONZE,ITEM_GROUP);
    public static final KnifeItem steelKnife = new KnifeItem(DetailedToolMaterial.STEEL,ITEM_GROUP);

    public static final SawItem copperSaw = new SawItem(DetailedToolMaterial.COPPER, ITEM_GROUP);
    public static final SawItem bronzeSaw = new SawItem(DetailedToolMaterial.BRONZE, ITEM_GROUP);
    public static final SawItem steelSaw = new SawItem(DetailedToolMaterial.STEEL, ITEM_GROUP);

    public static final WrenchItem copperWrench = new WrenchItem(DetailedToolMaterial.COPPER, ITEM_GROUP);
    public static final WrenchItem bronzeWrench = new WrenchItem(DetailedToolMaterial.BRONZE, ITEM_GROUP);
    public static final WrenchItem steelWrench = new WrenchItem(DetailedToolMaterial.STEEL, ITEM_GROUP);

    public static final HammerItem copperHammer = new HammerItem(DetailedToolMaterial.COPPER, ITEM_GROUP);
    public static final HammerItem bronzeHammer = new HammerItem(DetailedToolMaterial.BRONZE, ITEM_GROUP);
    public static final HammerItem steelHammer = new HammerItem(DetailedToolMaterial.STEEL, ITEM_GROUP);

    public static final RasperItem copperRasper = new RasperItem(DetailedToolMaterial.COPPER, ITEM_GROUP);
    public static final RasperItem bronzeRasper = new RasperItem(DetailedToolMaterial.BRONZE, ITEM_GROUP);
    public static final RasperItem steelRasper = new RasperItem(DetailedToolMaterial.STEEL, ITEM_GROUP);

    public static final DetailedPickaxeItem copperPickaxe = new DetailedPickaxeItem(DetailedToolMaterial.COPPER,
            2,1.5f, ITEM_GROUP);
    public static final DetailedPickaxeItem bronzePickaxe = new DetailedPickaxeItem(DetailedToolMaterial.BRONZE,
            2,1.5f, ITEM_GROUP);
    public static final DetailedPickaxeItem steelPickaxe = new DetailedPickaxeItem(DetailedToolMaterial.BRONZE,
            2,1.5f, ITEM_GROUP);

    public static final DetailedAxeItem copperAxe = new DetailedAxeItem(DetailedToolMaterial.COPPER,
            5,0.5f, ITEM_GROUP);
    public static final DetailedAxeItem bronzeAxe = new DetailedAxeItem(DetailedToolMaterial.BRONZE,
            5,0.5f, ITEM_GROUP);
    public static final DetailedAxeItem steelAxe = new DetailedAxeItem(DetailedToolMaterial.BRONZE,
            5,0.5f, ITEM_GROUP);

    public static final DetailedShovelItem copperShovel = new DetailedShovelItem(DetailedToolMaterial.COPPER,
            2,1.5f, ITEM_GROUP);
    public static final DetailedShovelItem bronzeShovel = new DetailedShovelItem(DetailedToolMaterial.BRONZE,
            2,1.5f, ITEM_GROUP);
    public static final DetailedShovelItem steelShovel = new DetailedShovelItem(DetailedToolMaterial.BRONZE,
            2,1.5f, ITEM_GROUP);

    public static final DetailedHoeItem copperHoe = new DetailedHoeItem(DetailedToolMaterial.COPPER,
            1,1.5f, ITEM_GROUP);
    public static final DetailedHoeItem bronzeHoe = new DetailedHoeItem(DetailedToolMaterial.BRONZE,
            1,1.5f, ITEM_GROUP);
    public static final DetailedHoeItem steelHoe = new DetailedHoeItem(DetailedToolMaterial.BRONZE,
            1,1.5f, ITEM_GROUP);

    public static final DetailedSwordItem copperSword = new DetailedSwordItem(DetailedToolMaterial.COPPER,
            4,2.5f, ITEM_GROUP);
    public static final DetailedSwordItem bronzeSword = new DetailedSwordItem(DetailedToolMaterial.BRONZE,
            4,2.5f, ITEM_GROUP);
    public static final DetailedSwordItem steelSword = new DetailedSwordItem(DetailedToolMaterial.BRONZE,
            4,2.5f, ITEM_GROUP);

    public static final Item copperBucket = new DetailedBucketItem(ITEM_GROUP, "copper",16, Fluids.EMPTY);
    public static final Item clayBucket = new DetailedBucketItem(ITEM_GROUP,"clay",16, Fluids.EMPTY);

    public static final Item beginnerFireStarter = new FireStarter(ITEM_GROUP,1);
    public static final Item basicFireStarter = new FireStarter(ITEM_GROUP,2);
    public static final Item advancedFireStarter = new FireStarter(ITEM_GROUP,3);

    static {
        Registration.item("Flint Knife",flintKnife);
        Registration.item("Copper Knife",copperKnife);
        Registration.item("Bronze Knife",bronzeKnife);
        Registration.item("Steel Knife",steelKnife);

        Registration.item("Beginner Fire Starter",beginnerFireStarter);
        Registration.item("Basic Fire Starter",basicFireStarter);
        Registration.item("Advanced Fire Starter",advancedFireStarter);

        Registration.item("Copper Saw",copperSaw);
        Registration.item("Bronze Saw",bronzeSaw);
        Registration.item("Steel Saw",steelSaw);

        Registration.item("Copper Wrench",copperWrench);
        Registration.item("Bronze Wrench",bronzeWrench);
        Registration.item("Steel Wrench",steelWrench);

        Registration.item("Copper Hammer",copperHammer);
        Registration.item("Bronze Hammer",bronzeHammer);
        Registration.item("Steel Hammer",steelHammer);

        Registration.item("Copper Rasper",copperRasper);
        Registration.item("Bronze Rasper",bronzeRasper);
        Registration.item("Steel Rasper",steelRasper);

        Registration.item("Copper Pickaxe",copperPickaxe);
        Registration.item("Bronze Pickaxe",bronzePickaxe);
        Registration.item("Steel Pickaxe",steelPickaxe);

        Registration.item("Copper Axe",copperAxe);
        Registration.item("Bronze Axe",bronzeAxe);
        Registration.item("Steel Axe",steelAxe);

        Registration.item("Copper Sword",copperSword);
        Registration.item("Bronze Sword",bronzeSword);
        Registration.item("Steel Sword",steelSword);

        Registration.item("Copper Shovel",copperShovel);
        Registration.item("Bronze Shovel",bronzeShovel);
        Registration.item("Steel Shovel",steelShovel);

        Registration.item("Copper Hoe",copperHoe);
        Registration.item("Bronze Hoe",bronzeHoe);
        Registration.item("Steel Hoe",steelHoe);

        Registration.item("Clay Bucket",clayBucket);
        Registration.item("Copper Bucket",copperBucket);

    }
}
