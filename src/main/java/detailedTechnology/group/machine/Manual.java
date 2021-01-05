package detailedTechnology.group.machine;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blocks.machines.manual.Model.block.*;
import detailedTechnology.blocks.machines.manual.Model.blockEntity.*;
import detailedTechnology.blocks.machines.manual.block.*;
import detailedTechnology.blocks.machines.manual.blockEntity.*;
import detailedTechnology.blocks.machines.manual.screenHandler.*;
import detailedTechnology.code.Registration;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Manual {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "manual"), () -> new ItemStack(Items.CRAFTING_TABLE));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static ScreenHandlerType<CarpenterWorkbenchScreenHandler> carpenterWorkbenchScreenHandler;
    public static ScreenHandlerType<FireStarterBlockScreenHandler> fireStarterBlockScreenHandler;
    public static ScreenHandlerType<AnvilScreenHandler> bronzeAnvilScreenHandler;
    public static ScreenHandlerType<ModelScreenHandler> clayModelScreenHandler;

    public static BlockEntityType<CarpenterWorkbenchEntity> carpenterWorkbenchEntity;
    public static BlockEntityType<FireStarterBlockEntity> fireStarterBlockEntity;
    public static BlockEntityType<BronzeAnvilEntity> bronzeAnvilEntity;
    public static BlockEntityType<SteelAnvilEntity> steelAnvilEntity;
    public static BlockEntityType<ClayIngotModelEntity> clayIngotModelEntity;
    public static BlockEntityType<ClayPlateModelEntity> clayPlateModelEntity;
    public static BlockEntityType<ClayGearModelEntity> clayGearModelEntity;
    public static BlockEntityType<ClayRodModelEntity> clayRodModelEntity;
    public static BlockEntityType<HighAluminaFirebrickIngotModelEntity> highAluminaFirebrickIngotModelEntity;
    public static BlockEntityType<HighAluminaFirebrickPlateModelEntity> highAluminaFirebrickPlateModelEntity;
    public static BlockEntityType<HighAluminaFirebrickGearModelEntity> highAluminaFirebrickGearModelEntity;
    public static BlockEntityType<HighAluminaFirebrickRodModelEntity> highAluminaFirebrickRodModelEntity;
    public static BlockEntityType<BrickModelEntity> brickModelEntity;

    public static final BrickModel brickModel = new BrickModel();
    public static final CarpenterWorkbench carpenterWorkbench = new CarpenterWorkbench();
    public static final Model clayIngotModel = new Model("ingot","clay");
    public static final Model clayPlateModel = new Model("plate","clay");
    public static final Model clayRodModel = new Model("rod","clay");
    public static final Model clayGearModel = new Model("gear","clay");
    public static final Model highAluminaFirebrickIngotModel = new Model("ingot","high_alumina_firebrick");
    public static final Model highAluminaFirebrickPlateModel = new Model("plate","high_alumina_firebrick");
    public static final Model highAluminaFirebrickRodModel = new Model("rod","high_alumina_firebrick");
    public static final Model highAluminaFirebrickGearModel = new Model("gear","high_alumina_firebrick");
    public static final StoneMileRunner stoneMileRunner = new StoneMileRunner();
    public static final FireStarterBlock fireStarterBlock = new FireStarterBlock();
    public static final Anvil bronzeAnvil = new Anvil(1);
    public static final Anvil steelAnvil = new Anvil(2);

    static {
        brickModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":brick_model",
                BlockEntityType.Builder.create(BrickModelEntity::new, brickModel).build(null));
        carpenterWorkbenchEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":carpenter_workbench",
                BlockEntityType.Builder.create(CarpenterWorkbenchEntity::new, carpenterWorkbench).build(null));
        fireStarterBlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":fire_starter_block",
                BlockEntityType.Builder.create(FireStarterBlockEntity::new, fireStarterBlock).build(null));
        bronzeAnvilEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_anvil",
                BlockEntityType.Builder.create(BronzeAnvilEntity::new, bronzeAnvil).build(null));
        steelAnvilEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":steel_anvil",
                BlockEntityType.Builder.create(SteelAnvilEntity::new, steelAnvil).build(null));
        clayIngotModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":clay_ingot_model",
                BlockEntityType.Builder.create(ClayIngotModelEntity::new, clayIngotModel).build(null));
        clayPlateModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":clay_plate_model",
                BlockEntityType.Builder.create(ClayPlateModelEntity::new, clayPlateModel).build(null));
        clayGearModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":clay_gear_model",
                BlockEntityType.Builder.create(ClayGearModelEntity::new, clayGearModel).build(null));
        clayRodModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":clay_rod_model",
                BlockEntityType.Builder.create(ClayRodModelEntity::new, clayRodModel).build(null));
        highAluminaFirebrickIngotModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":high_alumina_firebrick_ingot_model",
                BlockEntityType.Builder.create(HighAluminaFirebrickIngotModelEntity::new, highAluminaFirebrickIngotModel).build(null));
        highAluminaFirebrickPlateModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":high_alumina_firebrick_plate_model",
                BlockEntityType.Builder.create(HighAluminaFirebrickPlateModelEntity::new, highAluminaFirebrickPlateModel).build(null));
        highAluminaFirebrickGearModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":high_alumina_firebrick_gear_model",
                BlockEntityType.Builder.create(HighAluminaFirebrickGearModelEntity::new, highAluminaFirebrickGearModel).build(null));
        highAluminaFirebrickRodModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":high_alumina_firebrick_rod_model",
                BlockEntityType.Builder.create(HighAluminaFirebrickRodModelEntity::new, highAluminaFirebrickRodModel).build(null));

        Registration.blockWithItem("Carpenter Workbench", carpenterWorkbench, ITEM_GROUP);
        Registration.blockWithItem("Stone Mile Runner", stoneMileRunner, ITEM_GROUP);
        Registration.blockWithItem("Fire Starter Block", fireStarterBlock, ITEM_GROUP);
        Registration.blockWithItem("Clay Ingot Model", clayIngotModel, ITEM_GROUP);
        Registration.blockWithItem("Clay Plate Model", clayPlateModel, ITEM_GROUP);
        Registration.blockWithItem("Clay Rod Model", clayRodModel, ITEM_GROUP);
        Registration.blockWithItem("Clay Gear Model", clayGearModel, ITEM_GROUP);
        Registration.blockWithItem("High Alumina Firebrick Ingot Model", highAluminaFirebrickIngotModel, ITEM_GROUP);
        Registration.blockWithItem("High Alumina Firebrick Plate Model", highAluminaFirebrickPlateModel, ITEM_GROUP);
        Registration.blockWithItem("High Alumina Firebrick Rod Model", highAluminaFirebrickRodModel, ITEM_GROUP);
        Registration.blockWithItem("High Alumina Firebrick Gear Model", highAluminaFirebrickGearModel, ITEM_GROUP);
        Registration.blockWithItem("Bronze Anvil", bronzeAnvil, ITEM_GROUP);
        Registration.blockWithItem("Steel Anvil", steelAnvil, ITEM_GROUP);
        Registration.blockWithItem("Brick Model", brickModel, ITEM_GROUP);

        carpenterWorkbenchScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "carpenter_workbench"), CarpenterWorkbenchScreenHandler::new);
        fireStarterBlockScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "fire_starter_block"), FireStarterBlockScreenHandler::new);
        clayModelScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "clay_model"), ModelScreenHandler::new);
        bronzeAnvilScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "bronze_anvil"), AnvilScreenHandler::new);
    }
}
