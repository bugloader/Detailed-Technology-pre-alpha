package detailedTechnology.group.items;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import detailedTechnology.items.material.ClaySmallCrucibleWithLiquid;
import detailedTechnology.items.material.DetailedMaterialStatus;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class Materials {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "material"),() -> new ItemStack(Items.SUGAR));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Item tinder = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item wetClaySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithCopper = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithTin = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithBronze = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item steelRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item brickDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawBrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawFirebrickMixture = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawFirebrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item firebrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawHighAluminaFirebrickMixture = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawHighAluminaFirebrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item highAluminaFirebrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item coke = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item smallBronzePlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeHalfPipe = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeCylinder = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzePiston = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingCopper
            = new ClaySmallCrucibleWithLiquid("copper",new FabricItemSettings().group(ITEM_GROUP));
    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingTin
            = new ClaySmallCrucibleWithLiquid("tin",new FabricItemSettings().group(ITEM_GROUP));
    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingBronze
            = new ClaySmallCrucibleWithLiquid("bronze",new FabricItemSettings().group(ITEM_GROUP));

    public static final DetailedMaterialStatus COPPER = new DetailedMaterialStatus(
            "copper",8.96f,385,1085,2567);
    public static final DetailedMaterialStatus TIN = new DetailedMaterialStatus(
            "tin",5.77f,210,232,2602);
    public static final DetailedMaterialStatus LEAD = new DetailedMaterialStatus(
            "lead",11.34f,160,327,1749);
    public static final DetailedMaterialStatus SILVER = new DetailedMaterialStatus(
            "silver",10.49f,240,961,2162);
    public static final DetailedMaterialStatus IRON = new DetailedMaterialStatus(
            "iron",7.87f,444,1538,2862);
    public static final DetailedMaterialStatus STEEL = new DetailedMaterialStatus(
            "steel",8.05f,466,1370,2750);
    public static final DetailedMaterialStatus GOLD = new DetailedMaterialStatus(
            "gold",19.3f,129,1064,2970);
    public static final DetailedMaterialStatus ALUMINIUM = new DetailedMaterialStatus(
            "aluminium",2.7f,900,660,2470);
    public static final DetailedMaterialStatus PLATINUM = new DetailedMaterialStatus(
            "platinum",21.45f,133,1768,3825);

    public static final DetailedMaterialStatus BRONZE = new DetailedMaterialStatus(
            "bronze",8.16f,347,800,2567);
    public static final DetailedMaterialStatus BRICK = new DetailedMaterialStatus(
            "brick",1.7f,750,1280,3000);
    public static final DetailedMaterialStatus FIREBRICK = new DetailedMaterialStatus(
            "firebrick",2f,858,1580,3000);
    public static final DetailedMaterialStatus QUARTZ = new DetailedMaterialStatus(
            "quartz",2.65f,670,1713,3000);
    public static final DetailedMaterialStatus HIGH_ALUMINA_FIREBRICK = new DetailedMaterialStatus(
            "high_alumina_firebrick",2f,858,1770,3000);
    public static final DetailedMaterialStatus WATER = new DetailedMaterialStatus(
            "water",1f,4200,0,100);

    public static final float R = 8.31446261815324f;


    public static final ArrayList<String> LiquidList = new ArrayList<>();

    public static final ArrayList<DetailedMaterialStatus> MATERIAL_STATUSES = new ArrayList<>();

    static {
        Registration.item("Tinder",tinder);

        Registration.item("Wet Clay Small Crucible", wetClaySmallCrucible);
        Registration.item("Clay Small Crucible", claySmallCrucible);
        Registration.item("Clay Crucible With Copper", claySmallCrucibleWithCopper);
        Registration.item("Clay Crucible With Tin", claySmallCrucibleWithTin);
        Registration.item("Clay Crucible With Bronze", claySmallCrucibleWithBronze);
        Registration.item("Clay Crucible With Melting Copper", claySmallCrucibleWithMeltingCopper);
        Registration.item("Clay Crucible With Melting Tin", claySmallCrucibleWithMeltingTin);
        Registration.item("Clay Crucible With Melting Bronze", claySmallCrucibleWithMeltingBronze);

        Registration.item("Copper Rod Frame", copperRodFrame);
        Registration.item("Bronze Rod Frame", bronzeRodFrame);
        Registration.item("Iron Rod Frame", ironRodFrame);
        Registration.item("Steel Rod Frame", steelRodFrame);

        Registration.item("Brick Dust",brickDust);
        Registration.item("Raw Brick",rawBrick);
        Registration.item("Raw Firebrick Mixture", rawFirebrickMixture);
        Registration.item("Raw Firebrick", rawFirebrick);
        Registration.item("Firebrick", firebrick);
        Registration.item("Raw High Alumina Firebrick Mixture", rawHighAluminaFirebrickMixture);
        Registration.item("Raw High Alumina Firebrick", rawHighAluminaFirebrick);
        Registration.item("High Alumina Firebrick", highAluminaFirebrick);
        Registration.item("Coke",coke);
        Registration.item("Bronze Half Pipe", bronzeHalfPipe);

        Registration.item("Small Bronze Plate",smallBronzePlate);
        Registration.item("Bronze Cylinder",bronzeCylinder);
        Registration.item("Bronze Piston",bronzePiston);

        MATERIAL_STATUSES.add(COPPER);
        MATERIAL_STATUSES.add(TIN);
        MATERIAL_STATUSES.add(LEAD);
        MATERIAL_STATUSES.add(SILVER);
        MATERIAL_STATUSES.add(IRON);
        MATERIAL_STATUSES.add(STEEL);
        MATERIAL_STATUSES.add(GOLD);
        MATERIAL_STATUSES.add(ALUMINIUM);
        MATERIAL_STATUSES.add(PLATINUM);
        MATERIAL_STATUSES.add(BRONZE);
        MATERIAL_STATUSES.add(BRICK);
        MATERIAL_STATUSES.add(FIREBRICK);
        MATERIAL_STATUSES.add(QUARTZ);
        MATERIAL_STATUSES.add(HIGH_ALUMINA_FIREBRICK);

        LiquidList.add("water");
        LiquidList.add("creosote");
        LiquidList.add("steam");
        LiquidList.add("lava");
    }

}
