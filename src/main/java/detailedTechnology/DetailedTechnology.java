package detailedTechnology;

import detailedTechnology.blockEntity.*;
import detailedTechnology.blocks.*;
import detailedTechnology.gui.*;
import detailedTechnology.items.*;
import detailedTechnology.recipe.Recipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DetailedTechnology implements ModInitializer {

	public static final String MOD_ID = "dt";

	public static ScreenHandlerType<CrucibleScreenHandler> crucibleScreenHandler;
	public static ScreenHandlerType<StoneMileScreenHandler> stoneMileScreenHandler;
	public static ScreenHandlerType<FireStarterBlockScreenHandler> fireStarterBlockScreenHandler;
	public static ScreenHandlerType<BronzeAnvilScreenHandler> bronzeAnvilScreenHandler;
	public static ScreenHandlerType<KilnScreenHandler> kilnScreenHandler;

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"), () -> new ItemStack(Blocks.COBBLESTONE));

	public static final Item wetClaySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithCopper = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithTin = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item claySmallCrucibleWithBronze = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final Item copperRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item bronzeRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item brickDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item rawFirebrickMixture = new Item(new FabricItemSettings().group(ITEM_GROUP));
	public static final Item fireBrick = new Item(new FabricItemSettings().group(ITEM_GROUP));

	public static final HammerItem bronzeHammer = new HammerItem(DetailedToolMaterial.BRONZE,
			new FabricItemSettings().group(ITEM_GROUP));

	public static final RasperItem bronzeRasper = new RasperItem(DetailedToolMaterial.BRONZE,
			new FabricItemSettings().group(ITEM_GROUP));

	public static final DetailedPickaxeItem copperPickaxe = new DetailedPickaxeItem(DetailedToolMaterial.COPPER,
			2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedAxeItem copperAxe = new DetailedAxeItem(DetailedToolMaterial.COPPER,
			5,0.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedShovelItem copperShovel = new DetailedShovelItem(DetailedToolMaterial.COPPER,
			2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedHoeItem copperHoe = new DetailedHoeItem(DetailedToolMaterial.COPPER,
			1,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedSwordItem copperSword = new DetailedSwordItem(DetailedToolMaterial.COPPER,
			4,2.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedPickaxeItem bronzePickaxe = new DetailedPickaxeItem(DetailedToolMaterial.BRONZE,
			2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedAxeItem bronzeAxe = new DetailedAxeItem(DetailedToolMaterial.BRONZE,
			5,0.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedShovelItem bronzeShovel = new DetailedShovelItem(DetailedToolMaterial.BRONZE,
			2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedHoeItem bronzeHoe = new DetailedHoeItem(DetailedToolMaterial.BRONZE,
			1,1.5f, new FabricItemSettings().group(ITEM_GROUP));
	public static final DetailedSwordItem bronzeSword = new DetailedSwordItem(DetailedToolMaterial.BRONZE,
			4,2.5f, new FabricItemSettings().group(ITEM_GROUP));

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

	public static final Item copperBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"copper", Fluids.EMPTY);
	public static final Item copperWaterBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"copper", Fluids.WATER);
	public static final Item copperLavaBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"copper", Fluids.LAVA);
	public static final Item clayBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"clay", Fluids.EMPTY);
	public static final Item clayWaterBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"clay", Fluids.WATER);
	public static final Item clayLavaBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP),
			"clay", Fluids.LAVA);


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

		Registration.blockWithItem("Brick Crucible",brickCrucible,ITEM_GROUP);
		Registration.blockWithItem("Stone Mile",stoneMile,ITEM_GROUP);
		Registration.blockWithItem("Stone Mile Runner",stoneMileRunner,ITEM_GROUP);
		Registration.blockWithItem("Charcoal Heap",charcoalHeap,ITEM_GROUP);
		Registration.block("Burning Charcoal Heap",burningCharcoalHeap);
		Registration.blockWithItem("Fire Starter Block",fireStarterBlock,ITEM_GROUP);
		Registration.blockWithItem("Clay Ingot Model",clayModelIngot,ITEM_GROUP);
		Registration.blockWithItem("Bronze Anvil",bronzeAnvil,ITEM_GROUP);
		Registration.blockWithItem("Kiln",kiln,ITEM_GROUP);

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
		Registration.item("Brick Dust",brickDust);
		Registration.item("Raw Firebrick Mixture", rawFirebrickMixture);
		Registration.item("Firebrick",fireBrick);

		Registration.item("Beginner Fire Starter",beginnerFireStarter);
		Registration.item("Basic Fire Starter",basicFireStarter);

		Registration.item("Bronze Hammer",bronzeHammer);
		Registration.item("Bronze Rasper",bronzeRasper);

		Registration.item("Copper Helmet", copperHelmet);
		Registration.item("Copper Chest", copperChest);
		Registration.item("Copper Legs", copperLeg);
		Registration.item("Copper Boots", copperBoots);

		Registration.item("Bronze Helmet", bronzeHelmet);
		Registration.item("Bronze Chest", bronzeChest);
		Registration.item("Bronze Legs", bronzeLeg);
		Registration.item("Bronze Boots", bronzeBoots);

		Registration.item("Copper Pickaxe",copperPickaxe);
		Registration.item("Copper Axe",copperAxe);
		Registration.item("Copper Sword",copperSword);
		Registration.item("Copper Shovel",copperShovel);
		Registration.item("Copper Hoe",copperHoe);

		Registration.item("Bronze Pickaxe",bronzePickaxe);
		Registration.item("Bronze Axe",bronzeAxe);
		Registration.item("Bronze Sword",bronzeSword);
		Registration.item("Bronze Shovel",bronzeShovel);
		Registration.item("Bronze Hoe",bronzeHoe);

		Registration.item("Clay Bucket",clayBucket);
		Registration.item("Clay Water Bucket",clayWaterBucket);
		Registration.item("Clay Lava Bucket",clayLavaBucket);

		Registration.item("Copper Bucket",copperBucket);
		Registration.item("Copper Water Bucket",copperWaterBucket);
		Registration.item("Copper Lava Bucket",copperLavaBucket);

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

		new Recipes();

		System.out.println("Hello! Detailed world!");

	}
}
