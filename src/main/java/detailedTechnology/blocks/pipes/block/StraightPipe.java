package detailedTechnology.blocks.pipes.block;

import detailedTechnology.blocks.pipes.blockEntity.BronzeStraightPipeEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class StraightPipe extends Block implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 4, 4, 12, 12, 12);
    public static final IntProperty PIPE_FACING = IntProperty.of("pf", 0, 2);
    String material;

    public StraightPipe(String material) {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
                .strength(2f, 3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
        setDefaultState(this.stateManager.getDefaultState().with(PIPE_FACING, 0));
        this.material = material;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(PIPE_FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BronzeStraightPipeEntity();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        Direction facingDirection = ctx.getPlayerFacing();
        int pipeFacing = 0;
        switch (facingDirection) {
            case NORTH:
            case SOUTH:
                pipeFacing = 0;
                break;
            case WEST:
            case EAST:
                pipeFacing = 1;
                break;
        }
        return (BlockState) this.getDefaultState().with(PIPE_FACING, pipeFacing);
    }

    public boolean[] getConnected(int facing) {
        boolean[] connection = new boolean[6];
        for (int i = 0; i < 6; i++) {
            connection[i] = false;
        }
        switch (facing) {
            case 0:
                connection[2] = true;
                connection[3] = true;
                break;
            case 1:
                connection[0] = true;
                connection[1] = true;
                break;
            case 2:
                connection[4] = true;
                connection[5] = true;
                break;
        }
        return connection;
    }
}