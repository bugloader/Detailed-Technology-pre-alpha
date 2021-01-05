package detailedTechnology.group.machine;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blocks.machines.auto.block.*;
import detailedTechnology.code.Registration;
import detailedTechnology.blocks.currentdone.*;
import detailedTechnology.blocks.machines.auto.blockEntity.*;
import detailedTechnology.blocks.machines.auto.screenHandler.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Auto {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "machine"), () -> new ItemStack(Items.PISTON));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static ScreenHandlerType<CrucibleScreenHandler> crucibleScreenHandler;
    public static ScreenHandlerType<StoneMileScreenHandler> stoneMileScreenHandler;
    public static ScreenHandlerType<CombustionChamberScreenHandler> combustionChamberScreenHandler;
    public static ScreenHandlerType<CokeOvenScreenHandler> cokeOvenScreenHandler;
    public static ScreenHandlerType<PrimitiveBlastFurnaceScreenHandler> primitiveBlastFurnaceScreenHandler;
    public static ScreenHandlerType<BronzeBoilerScreenHandler> bronzeBoilerScreenHandler;
    public static ScreenHandlerType<KilnScreenHandler> kilnScreenHandler;

    public static BlockEntityType<BrickCrucibleEntity> brickCrucibleEntity;
    public static BlockEntityType<QuartzCrucibleEntity> quartzCrucibleEntity;
    public static BlockEntityType<StoneMileEntity> stoneMileEntity;
    public static BlockEntityType<BurningCharcoalHeapEntity> burningCharcoalHeapEntity;
    public static BlockEntityType<FirebrickCombustionChamberEntity> firebrickCombustionChamberEntity;
    public static BlockEntityType<BronzeCombustionChamberEntity> bronzeCombustionChamberEntity;
    public static BlockEntityType<HighAluminaFirebrickCombustionChamberEntity> highAluminaFirebrickCombustionChamberEntity;
    public static BlockEntityType<SteelCombustionChamberEntity> steelCombustionEntity;
    public static BlockEntityType<CokeOvenEntity> cokeOvenEntity;
    public static BlockEntityType<PrimitiveBlastFurnaceEntity> primitiveBlastFurnaceEntity;
    public static BlockEntityType<BronzeBoilerEntity> bronzeBoilerEntity;
    public static BlockEntityType<BronzeWattDoubleActingSteamEngineEntity> bronzeWattDoubleActingSteamEngineEntity;
    public static BlockEntityType<KilnEntity> kilnEntity;

    public static final BurningCharcoalHeap burningCharcoalHeap = new BurningCharcoalHeap();
    public static final Crucible brickCrucible = new Crucible("brick");
    public static final Crucible quartzCrucible = new Crucible("quartz");
    public static final StoneMile stoneMile = new StoneMile();
    public static final CombustionChamber bronzeCombustionChamber = new CombustionChamber("bronze");
    public static final CombustionChamber firebrickCombustionChamber = new CombustionChamber("firebrick");
    public static final CombustionChamber steelCombustionChamber = new CombustionChamber("steel");
    public static final CombustionChamber highAluminaFirebrickCombustionChamber = new CombustionChamber("high_alumina_firebrick");
    public static final CokeOven cokeOven = new CokeOven();
    public static final PrimitiveBlastFurnace primitiveBlastFurnace = new PrimitiveBlastFurnace();
    public static final BronzeBoiler bronzeBoiler = new BronzeBoiler();
    public static final BronzeWattDoubleActingSteamEngine bronzeWattDoubleActingSteamEngine = new BronzeWattDoubleActingSteamEngine();
    public static final StoneMileRunnerWithGear stoneMileRunnerWithGear = new StoneMileRunnerWithGear();
    public static final Kiln kiln = new Kiln();

    static {
        brickCrucibleEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":brick_crucible",
                BlockEntityType.Builder.create(BrickCrucibleEntity::new, brickCrucible).build(null));
        quartzCrucibleEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":quartz_crucible",
                BlockEntityType.Builder.create(QuartzCrucibleEntity::new, quartzCrucible).build(null));
        stoneMileEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":stone_mile",
                BlockEntityType.Builder.create(StoneMileEntity::new, stoneMile).build(null));
        burningCharcoalHeapEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":burning_charcoal_heap",
                BlockEntityType.Builder.create(BurningCharcoalHeapEntity::new, burningCharcoalHeap).build(null));
        kilnEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":kiln",
                BlockEntityType.Builder.create(KilnEntity::new, kiln).build(null));
        firebrickCombustionChamberEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":firebrick_combustion_chamber",
                BlockEntityType.Builder.create(FirebrickCombustionChamberEntity::new, firebrickCombustionChamber).build(null));
        bronzeCombustionChamberEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_combustion_chamber",
                BlockEntityType.Builder.create(BronzeCombustionChamberEntity::new, bronzeCombustionChamber).build(null));
        steelCombustionEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":steel_combustion_chamber",
                BlockEntityType.Builder.create(SteelCombustionChamberEntity::new, steelCombustionChamber).build(null));
        highAluminaFirebrickCombustionChamberEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":high_alumina_firebrick_combustion_chamber",
                BlockEntityType.Builder.create(HighAluminaFirebrickCombustionChamberEntity::new, highAluminaFirebrickCombustionChamber).build(null));
        cokeOvenEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":coke_oven",
                BlockEntityType.Builder.create(CokeOvenEntity::new, cokeOven).build(null));
        primitiveBlastFurnaceEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":primitive_blast_furnace",
                BlockEntityType.Builder.create(PrimitiveBlastFurnaceEntity::new, primitiveBlastFurnace).build(null));
        bronzeBoilerEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_boiler",
                BlockEntityType.Builder.create(BronzeBoilerEntity::new, bronzeBoiler).build(null));
        bronzeWattDoubleActingSteamEngineEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_watt_double_acting_steam_engine",
                BlockEntityType.Builder.create(BronzeWattDoubleActingSteamEngineEntity::new, bronzeWattDoubleActingSteamEngine).build(null));

        Registration.blockWithItem("Brick Crucible", brickCrucible, ITEM_GROUP);
        Registration.blockWithItem("Quartz Crucible", quartzCrucible, ITEM_GROUP);
        Registration.blockWithItem("Stone Mile", stoneMile, ITEM_GROUP);
        Registration.block("Burning Charcoal Heap", burningCharcoalHeap);
        Registration.blockWithItem("Kiln", kiln, ITEM_GROUP);
        Registration.blockWithItem("Bronze Combustion Chamber", bronzeCombustionChamber, ITEM_GROUP);
        Registration.blockWithItem("Firebrick Combustion Chamber", firebrickCombustionChamber, ITEM_GROUP);
        Registration.blockWithItem("Steel Combustion Chamber", steelCombustionChamber, ITEM_GROUP);
        Registration.blockWithItem("High Alumina Firebrick Combustion Chamber", highAluminaFirebrickCombustionChamber, ITEM_GROUP);
        Registration.blockWithItem("Coke Oven", cokeOven, ITEM_GROUP);
        Registration.blockWithItem("Primitive Blast Furnace", primitiveBlastFurnace, ITEM_GROUP);
        Registration.blockWithItem("Bronze Boiler", bronzeBoiler, ITEM_GROUP);
        Registration.blockWithItem("Bronze Watt Double Acting Steam Engine", bronzeWattDoubleActingSteamEngine, ITEM_GROUP);
        Registration.blockWithItem("Stone Mile Runner With Gear", stoneMileRunnerWithGear, ITEM_GROUP);

        crucibleScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "brick_crucible"), CrucibleScreenHandler::new);
        stoneMileScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "stone_mile"), StoneMileScreenHandler::new);
        combustionChamberScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "combustion_chamber"), CombustionChamberScreenHandler::new);
        cokeOvenScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "coke_oven"), CokeOvenScreenHandler::new);
        bronzeBoilerScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "bronze_boiler"), BronzeBoilerScreenHandler::new);
        primitiveBlastFurnaceScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "primitive_blast_furnace"), PrimitiveBlastFurnaceScreenHandler::new);
        kilnScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "kiln"), KilnScreenHandler::new);
    }
}
