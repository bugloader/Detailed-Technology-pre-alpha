package detailedTechnology.blocks.machines.auto.block;

import detailedTechnology.blocks.machines.auto.blockEntity.BronzeWattDoubleActingSteamEngineEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class BronzeWattDoubleActingSteamEngine extends HorizontalFacingBlock implements BlockEntityProvider{
    private static final VoxelShape SHAPE1 =
            Block.createCuboidShape(0, 0, 4, 16, 14, 12),
            SHAPE2 = Block.createCuboidShape(4, 0, 0, 12, 14, 16);
    public static final IntProperty WORK_PROGRESS = IntProperty.of("work",0,1);
    String material;
    public BronzeWattDoubleActingSteamEngine()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,6f));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        .with(WORK_PROGRESS,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(WORK_PROGRESS);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        switch (dir) {
            case NORTH:
            case SOUTH:
                return SHAPE1;
            default:
                return SHAPE2;
        }
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BronzeWattDoubleActingSteamEngineEntity();
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

}