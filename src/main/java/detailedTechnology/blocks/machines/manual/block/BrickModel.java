package detailedTechnology.blocks.machines.manual.block;

import detailedTechnology.blocks.machines.manual.blockEntity.BrickModelEntity;
import detailedTechnology.group.items.Materials;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
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

import java.util.Objects;

public class BrickModel extends HorizontalFacingBlock implements BlockEntityProvider {
    private static final VoxelShape SHAPE1 =
            Block.createCuboidShape(0, 0, 3, 16, 5, 13),
            SHAPE2 = Block.createCuboidShape(3, 0, 0, 13, 5, 16);
    public static final IntProperty CONTENT_STATUS = IntProperty.of("cs", 0, 2);

    public BrickModel() {
        super(FabricBlockSettings.of(Material.WOOD, MaterialColor.STONE)
                .strength(2f, 3f));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH)
        .with(CONTENT_STATUS,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
        stateManager.add(CONTENT_STATUS);
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
        return new BrickModelEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            int status = state.get(CONTENT_STATUS);
            String itemName = player.getStackInHand(hand).getName().getString();
            BrickModelEntity entity = ((BrickModelEntity)world.getBlockEntity(pos));
            if(status==0){
                if (itemName.equals(Items.CLAY_BALL.getName().getString())){
                    Objects.requireNonNull(entity).receiveMaterial("brick",player,hand);
                }else if (itemName.equals(Materials.rawFirebrickMixture.getName().getString())){
                    Objects.requireNonNull(entity).receiveMaterial("firebrick",player,hand);
                }else if (itemName.equals(Materials.rawHighAluminaFirebrickMixture.getName().getString())){
                    Objects.requireNonNull(entity).receiveMaterial("high_alumina_firebrick",player,hand);
                }
            }else if(status==2){
                Objects.requireNonNull(entity).giveProduct(player);
            }
        }
        return ActionResult.PASS;
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BrickModelEntity) {
                ItemScatterer.spawn(world, pos, (BrickModelEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState) this.getDefaultState().with(FACING, ctx.getPlayerFacing()).with(CONTENT_STATUS,0);
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

}
