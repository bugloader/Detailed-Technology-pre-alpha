package detailedTechnology.blocks.machines.manual.Model.block;

import detailedTechnology.blocks.machines.auto.blockEntity.BrickCrucibleEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.QuartzCrucibleEntity;
import detailedTechnology.blocks.machines.auto.utilities.CrucibleUtilities;
import detailedTechnology.blocks.machines.manual.Model.blockEntity.*;
import detailedTechnology.blocks.machines.manual.utilities.ModelUtilities;
import detailedTechnology.group.machine.Auto;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class Model extends HorizontalFacingBlock implements BlockEntityProvider {
    private static final VoxelShape SHAPE0 = Block.createCuboidShape(0, 0, 0, 16, 4, 16),
            SHAPE1 = Block.createCuboidShape(0, 0, 4, 16, 4, 12),
            SHAPE2 = Block.createCuboidShape(4, 0, 0, 12, 4, 16);
    private boolean useTime = false;
    public final String type;
    public final String material;

    public Model(String type, String material) {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f, 3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        this.type = type;
        this.material = material;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        switch (type){
            case "gear":
            case "plate":
                return SHAPE0;
        }
        Direction dir = state.get(FACING);
        switch (dir) {
            case NORTH:
            case SOUTH:
                return SHAPE2;
            default:
                return SHAPE1;
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        switch (material) {
            case "clay":
                switch (type) {
                    case "ingot":
                        return new ClayIngotModelEntity();
                    case "plate":
                        return new ClayPlateModelEntity();
                    case "gear":
                        return new ClayGearModelEntity();
                    case "rod":
                        return new ClayRodModelEntity();
                }
            case "high_alumina_firebrick":
                switch (type) {
                    case "ingot":
                        return new HighAluminaFirebrickIngotModelEntity();
                    case "plate":
                        return new HighAluminaFirebrickPlateModelEntity();
                    case "gear":
                        return new HighAluminaFirebrickGearModelEntity();
                    case "rod":
                        return new HighAluminaFirebrickRodModelEntity();
                }
        }
        return new ClayIngotModelEntity();
    }

    private void getLiquidFromCrucible(ModelUtilities modelUtilities, World world, BlockPos backPos) {
        if (useTime && modelUtilities.liquidName.equals("air")) {
            String backName = world.getBlockState(backPos).getBlock().getName().getString();
            CrucibleUtilities utilities;
            if (backName.equals(Auto.brickCrucible.getName().getString())) {
                utilities = ((BrickCrucibleEntity) Objects.requireNonNull(world.getBlockEntity(backPos))).utilities;
                modelUtilities.receiveUnitLiquid(utilities.getUnitLiquid(), utilities.temperature);
            } else if (backName.equals(Auto.quartzCrucible.getName().getString())) {
                utilities = ((QuartzCrucibleEntity) Objects.requireNonNull(world.getBlockEntity(backPos))).utilities;
                modelUtilities.receiveUnitLiquid(utilities.getUnitLiquid(), utilities.temperature);
            }
        }
        useTime = !useTime;
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.isSneaking()) {
                Direction direction = state.get(FACING);
                BlockPos backPos = pos.east();
                switch (direction) {
                    case NORTH:
                        backPos = pos.north();
                        break;
                    case SOUTH:
                        backPos = pos.south();
                        break;
                    case WEST:
                        backPos = pos.west();
                }
                ModelUtilities modelUtilities = null;
                switch (material) {
                    case "clay":
                        switch (type) {
                            case "ingot":
                                modelUtilities = ((ClayIngotModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "plate":
                                modelUtilities = ((ClayPlateModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "gear":
                                modelUtilities = ((ClayGearModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "rod":
                                modelUtilities = ((ClayRodModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                        }
                        getLiquidFromCrucible(modelUtilities, world, backPos);
                        break;
                    case "high_alumina_firebrick":
                        switch (type) {
                            case "ingot":
                                modelUtilities = ((HighAluminaFirebrickIngotModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "plate":
                                modelUtilities = ((HighAluminaFirebrickPlateModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "gear":
                                modelUtilities = ((HighAluminaFirebrickGearModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                            case "rod":
                                modelUtilities = ((HighAluminaFirebrickRodModelEntity)
                                        Objects.requireNonNull(world.getBlockEntity(pos))).utilities;
                                break;
                        }
                        getLiquidFromCrucible(modelUtilities, world, backPos);
                        break;
                }
            } else {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            switch (material) {
                case "clay":
                    switch (type) {
                        case "ingot":
                            ItemScatterer.spawn(world, pos, (ClayIngotModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "plate":
                            ItemScatterer.spawn(world, pos, (ClayPlateModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "gear":
                            ItemScatterer.spawn(world, pos, (ClayGearModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "rod":
                            ItemScatterer.spawn(world, pos, (ClayRodModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                    }
                    world.updateComparators(pos, this);
                    break;
                case "high_alumina_firebrick":
                    switch (type) {
                        case "ingot":
                            ItemScatterer.spawn(world, pos,
                                    (HighAluminaFirebrickIngotModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "plate":
                            ItemScatterer.spawn(world, pos,
                                    (HighAluminaFirebrickPlateModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "gear":
                            ItemScatterer.spawn(world, pos,
                                    (HighAluminaFirebrickGearModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                        case "rod":
                            ItemScatterer.spawn(world, pos,
                                    (HighAluminaFirebrickRodModelEntity) Objects.requireNonNull(blockEntity));
                            break;
                    }
                    world.updateComparators(pos, this);
                    break;
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory) blockEntity : null;
    }
}
