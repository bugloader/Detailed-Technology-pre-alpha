package detailedTechnology;

import detailedTechnology.blockEntity.*;
import detailedTechnology.blocks.*;
import detailedTechnology.gui.*;
import detailedTechnology.items.*;
import detailedTechnology.recipe.AnvilRecipe;
import detailedTechnology.recipe.KilnRecipe;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DetailedTechnology implements ModInitializer {

	public static final String MOD_ID = "dt";
	public static final Registration registration = new Registration(MOD_ID);

	public static ScreenHandlerType<CrucibleScreenHandler> crucibleScreenHandler;
	public static ScreenHandlerType<StoneMileScreenHandler> stoneMileScreenHandler;
	public static ScreenHandlerType<FireStarterBlockScreenHandler> fireStarterBlockScreenHandler;
	public static ScreenHandlerType<BronzeAnvilScreenHandler> bronzeAnvilScreenHandler;
	public static ScreenHandlerType<KilnScreenHandler> kilnScreenHandler;

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier("dt", "general"), () -> new ItemStack(Blocks.COBBLESTONE));

	public static final Item wetClaySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithCopper = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithTin = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithBronze = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final Item copperRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item bronzeRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final HammerItem bronzeHammer = new HammerItem(BronzeMaterial.INSTANCE,
			new FabricItemSettings().group(ITEM_GROUP));

	public static final RasperItem bronzeRasper = new RasperItem(BronzeMaterial.INSTANCE,
			new FabricItemSettings().group(ITEM_GROUP));

	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingCopper
			= new ClaySmallCrucibleWithLiquid("copper");
	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingTin
			= new ClaySmallCrucibleWithLiquid("tin");
	public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingBronze
			= new ClaySmallCrucibleWithLiquid("bronze");

	public static final BeginnerFireStarter beginnerFireStarter = new BeginnerFireStarter();
	public static final BasicFireStarter basicFireStarter = new BasicFireStarter();

	public static BlockEntityType<BrickCrucibleEntity> brickCrucibleEntity;
	public static BlockEntityType<StoneMileEntity> stoneMileEntity;
	public static BlockEntityType<BurningCharcoalHeapEntity> burningCharcoalHeapEntity;
	public static BlockEntityType<FireStarterBlockEntity> fireStarterBlockEntity;
	public static BlockEntityType<BronzeAnvilEntity> bronzeAnvilEntity;
	public static BlockEntityType<KilnEntity> kilnEntity;

	public static final CharcoalHeap charcoalHeap = new CharcoalHeap();
	public static final StoneMileRunner stoneMileRunner = new StoneMileRunner();

	public static final BurningCharcoalHeap burningCharcoalHeap = new BurningCharcoalHeap();
	public static final BrickCrucible brickCrucible = new BrickCrucible();
	public static final StoneMile stoneMile = new StoneMile();
	public static final FireStarterBlock fireStarterBlock = new FireStarterBlock();
	public static final BronzeAnvil bronzeAnvil = new BronzeAnvil();
	public static final Kiln kiln = new Kiln();

	public static final ClayModelIngot clayModelIngot = new ClayModelIngot();

	public static final Ores ores = new Ores();

	public static final Item copperHelmet = new ArmorItem(DetailedArmorMaterial.COPPER,
			EquipmentSlot.HEAD, new Item.Settings().group(ITEM_GROUP));
	public static final Item copperChest = new ArmorItem(DetailedArmorMaterial.COPPER,
			EquipmentSlot.CHEST, new Item.Settings().group(ITEM_GROUP));
	public static final Item copperLeg = new ArmorItem(DetailedArmorMaterial.COPPER,
			EquipmentSlot.LEGS, new Item.Settings().group(ITEM_GROUP));
	public static final Item copperBoots = new ArmorItem(DetailedArmorMaterial.COPPER,
			EquipmentSlot.FEET, new Item.Settings().group(ITEM_GROUP));

	public static final Item bronzeHelmet = new ArmorItem(DetailedArmorMaterial.BRONZE,
			EquipmentSlot.HEAD, new Item.Settings().group(ITEM_GROUP));
	public static final Item bronzeChest = new ArmorItem(DetailedArmorMaterial.BRONZE,
			EquipmentSlot.CHEST, new Item.Settings().group(ITEM_GROUP));
	public static final Item bronzeLeg = new ArmorItem(DetailedArmorMaterial.BRONZE,
			EquipmentSlot.LEGS, new Item.Settings().group(ITEM_GROUP));
	public static final Item bronzeBoots = new ArmorItem(DetailedArmorMaterial.BRONZE,
			EquipmentSlot.FEET, new Item.Settings().group(ITEM_GROUP));

	public static final AnvilRecipe anvilRecipe = new AnvilRecipe();
	public static final KilnRecipe kilnRecipe = new KilnRecipe();



	@Override
	public void onInitialize() {

		brickCrucibleEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":brick_crucible",
				BlockEntityType.Builder.create(BrickCrucibleEntity::new,brickCrucible).build(null));
		stoneMileEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":stone_mile",
				BlockEntityType.Builder.create(StoneMileEntity::new,stoneMile).build(null));
		burningCharcoalHeapEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":burning_charcoal_heap",
				BlockEntityType.Builder.create(BurningCharcoalHeapEntity::new,burningCharcoalHeap).build(null));
		fireStarterBlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":fire_starter_block",
				BlockEntityType.Builder.create(FireStarterBlockEntity::new,fireStarterBlock).build(null));
		bronzeAnvilEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":bronze_anvil",
				BlockEntityType.Builder.create(BronzeAnvilEntity::new,bronzeAnvil).build(null));
		kilnEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":kiln",
				BlockEntityType.Builder.create(KilnEntity::new,kiln).build(null));

		Registration.blockWithItem("brick_crucible",brickCrucible,ITEM_GROUP);
		Registration.blockWithItem("stone_mile",stoneMile,ITEM_GROUP);
		Registration.blockWithItem("stone_mile_runner",stoneMileRunner,ITEM_GROUP);
		Registration.blockWithItem("charcoal_heap",charcoalHeap,ITEM_GROUP);
		Registration.block("burning_charcoal_heap",burningCharcoalHeap);
		Registration.blockWithItem("fire_starter_block",fireStarterBlock,ITEM_GROUP);
		Registration.blockWithItem("clay_ingot_model",clayModelIngot,ITEM_GROUP);
		Registration.blockWithItem("bronze_anvil",bronzeAnvil,ITEM_GROUP);
		Registration.blockWithItem("kiln",kiln,ITEM_GROUP);

		Registration.item("wet_clay_small_crucible", wetClaySmallCrucible);
		Registration.item("clay_small_crucible", claySmallCrucible);
		Registration.item("clay_crucible_with_copper", claySmallCrucibleWithCopper);
		Registration.item("clay_crucible_with_tin", claySmallCrucibleWithTin);
		Registration.item("clay_crucible_with_bronze", claySmallCrucibleWithBronze);
		Registration.item("clay_crucible_with_melting_copper", claySmallCrucibleWithMeltingCopper);
		Registration.item("clay_crucible_with_melting_tin", claySmallCrucibleWithMeltingTin);
		Registration.item("clay_crucible_with_melting_bronze", claySmallCrucibleWithMeltingBronze);

		Registration.item("copper_rod_frame", copperRodFrame);
		Registration.item("bronze_rod_frame", bronzeRodFrame);

		Registration.item("beginner_fire_starter",beginnerFireStarter);
		Registration.item("basic_fire_starter",basicFireStarter);

		Registration.item("bronze_hammer",bronzeHammer);
		Registration.item("bronze_rasper",bronzeRasper);

		Registration.item("copper_helmet", copperHelmet);
		Registration.item("copper_chest", copperChest);
		Registration.item("copper_legs", copperLeg);
		Registration.item("copper_boots", copperBoots);

		Registration.item("bronze_helmet", bronzeHelmet);
		Registration.item("bronze_chest", bronzeChest);
		Registration.item("bronze_legs", bronzeLeg);
		Registration.item("bronze_boots", bronzeBoots);

		crucibleScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "brick_crucible"),CrucibleScreenHandler::new);
		stoneMileScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "stone_mile"),StoneMileScreenHandler::new);
		fireStarterBlockScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "fire_starter_block"),FireStarterBlockScreenHandler::new);
		bronzeAnvilScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "bronze_anvil"),BronzeAnvilScreenHandler::new);
		kilnScreenHandler = ScreenHandlerRegistry.registerSimple(
				new Identifier(MOD_ID, "kiln"), KilnScreenHandler::new);

		System.out.println("Hello detailed world!");
	}
}
