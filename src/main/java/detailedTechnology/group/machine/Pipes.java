package detailedTechnology.group.machine;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blocks.pipes.blockEntity.*;
import detailedTechnology.blocks.tanks.blockEntity.BronzeBarrelEntity;
import detailedTechnology.blocks.tanks.blockEntity.FirebrickBarrelEntity;
import detailedTechnology.blocks.tanks.blockEntity.WoodBarrelEntity;
import detailedTechnology.blocks.tanks.block.Barrel;
import detailedTechnology.blocks.pipes.block.*;
import detailedTechnology.code.Registration;
import detailedTechnology.blocks.tanks.screenHandler.BarrelScreenHandler;
import detailedTechnology.items.toolItem.vanilla.DetailedBucketItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Pipes {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "tank"), () -> new ItemStack(Items.WATER_BUCKET));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Item copperWaterBucket = new DetailedBucketItem(ITEM_GROUP, "copper",1, Fluids.WATER);
    public static final Item copperLavaBucket = new DetailedBucketItem(ITEM_GROUP, "copper",1, Fluids.LAVA);
    public static final Item clayWaterBucket = new DetailedBucketItem(ITEM_GROUP, "clay",1, Fluids.WATER);
    public static final Item clayLavaBucket = new DetailedBucketItem(ITEM_GROUP, "clay",1, Fluids.LAVA);


    public static ScreenHandlerType<BarrelScreenHandler> barrelScreenHandler;

    public static BlockEntityType<WoodBarrelEntity> woodBarrelEntity;
    public static BlockEntityType<BronzeBarrelEntity> bronzeBarrelEntity;
    public static BlockEntityType<FirebrickBarrelEntity> firebrickBarrelEntity;

    public static BlockEntityType<BronzeStraightPipeEntity> bronzeStraightPipeEntity;
    public static BlockEntityType<BronzeBentPipeEntity> bronzeBentPipeEntity;
    public static BlockEntityType<BronzeTPipeEntity> bronzeTPipeEntity;
    public static BlockEntityType<BronzeCrossPipeEntity> bronzeCrossPipeEntity;
    public static BlockEntityType<BronzeFiveWayPipeEntity> bronzeFiveWayPipeEntity;
    public static BlockEntityType<BronzeSixWayPipeEntity> bronzeSixWayPipeEntity;

    public static final Barrel woodBarrel = new Barrel("wood");
    public static final Barrel bronzeBarrel = new Barrel("bronze");
    public static final Barrel firebrickBarrel = new Barrel("firebrick");

    public static final StraightPipe bronzeStraightPipe = new StraightPipe("bronze");
    public static final BentPipe bronzeBentPipe = new BentPipe("bronze");
    public static final TPipe bronzeTPipe = new TPipe("bronze");
    public static final CrossPipe bronzeCrossPipe = new CrossPipe("bronze");
    public static final FiveWayPipe bronzeFiveWayPipe = new FiveWayPipe("bronze");
    public static final SixWayPipe bronzeSixWayPipe = new SixWayPipe("bronze");

    static {
        Registration.item("Clay Water Bucket", clayWaterBucket);
        Registration.item("Clay Lava Bucket", clayLavaBucket);
        Registration.item("Copper Water Bucket", copperWaterBucket);
        Registration.item("Copper Lava Bucket", copperLavaBucket);


        woodBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":wood_barrel",
                BlockEntityType.Builder.create(WoodBarrelEntity::new, woodBarrel).build(null));
        bronzeBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_barrel",
                BlockEntityType.Builder.create(BronzeBarrelEntity::new, bronzeBarrel).build(null));
        firebrickBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":firebrick_barrel",
                BlockEntityType.Builder.create(FirebrickBarrelEntity::new, firebrickBarrel).build(null));

        bronzeStraightPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_straight_pipe",
                BlockEntityType.Builder.create(BronzeStraightPipeEntity::new, bronzeStraightPipe).build(null));
        bronzeBentPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_bent_pipe",
                BlockEntityType.Builder.create(BronzeBentPipeEntity::new, bronzeBentPipe).build(null));
        bronzeTPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_t_pipe",
                BlockEntityType.Builder.create(BronzeTPipeEntity::new, bronzeTPipe).build(null));
        bronzeCrossPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_cross_pipe",
                BlockEntityType.Builder.create(BronzeCrossPipeEntity::new, bronzeCrossPipe).build(null));
        bronzeFiveWayPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_five_way_pipe",
                BlockEntityType.Builder.create(BronzeFiveWayPipeEntity::new, bronzeFiveWayPipe).build(null));
        bronzeSixWayPipeEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_six_way_pipe",
                BlockEntityType.Builder.create(BronzeSixWayPipeEntity::new, bronzeSixWayPipe).build(null));


        Registration.blockWithItem("Wood Barrel", woodBarrel, ITEM_GROUP);
        Registration.blockWithItem("Bronze Barrel", bronzeBarrel, ITEM_GROUP);
        Registration.blockWithItem("Firebrick Barrel", firebrickBarrel, ITEM_GROUP);
        Registration.blockWithItem("Bronze Straight Pipe", bronzeStraightPipe, ITEM_GROUP);
        Registration.blockWithItem("Bronze Bent Pipe", bronzeBentPipe, ITEM_GROUP);
        Registration.blockWithItem("Bronze T Pipe", bronzeTPipe, ITEM_GROUP);
        Registration.blockWithItem("Bronze Cross Pipe", bronzeCrossPipe, ITEM_GROUP);
        Registration.blockWithItem("Bronze Five Way Pipe", bronzeFiveWayPipe, ITEM_GROUP);
        Registration.blockWithItem("Bronze Six Way Pipe", bronzeSixWayPipe, ITEM_GROUP);

        barrelScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "barrel"), BarrelScreenHandler::new);
    }

}
