package detailedTechnology.blocks.machines.auto.block;

import detailedTechnology.blocks.machines.auto.blockEntity.BronzeCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.FirebrickCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.HighAluminaFirebrickCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.SteelCombustionChamberEntity;
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
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class CombustionChamber extends HorizontalFacingBlock implements BlockEntityProvider {
    public final String material;
    public CombustionChamber(String material)
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        this.material=material;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        if(material.equals("bronze")){
            return new BronzeCombustionChamberEntity();
        }
        if(material.equals("firebrick")) {
            return new FirebrickCombustionChamberEntity();
        }
        if(material.equals("steel")){
            return new SteelCombustionChamberEntity();
        }
        else return new HighAluminaFirebrickCombustionChamberEntity();
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
            if (screenHandlerFactory != null) {
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }

    //drop inside items when break
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(material.equals("bronze")) {
                    ItemScatterer.spawn(world, pos, (BronzeCombustionChamberEntity) Objects.requireNonNull(blockEntity));
            }else if(material.equals("firebrick")) {
                    ItemScatterer.spawn(world, pos, (FirebrickCombustionChamberEntity)Objects.requireNonNull(blockEntity));
            }else if(material.equals("steel")) {
                    ItemScatterer.spawn(world, pos, (SteelCombustionChamberEntity)Objects.requireNonNull(blockEntity));
            }else if(material.equals("high_alumina_firebrick")) {
                ItemScatterer.spawn(world, pos, (HighAluminaFirebrickCombustionChamberEntity)Objects.requireNonNull(blockEntity));
            }
            world.updateComparators(pos,this);
            super.onStateReplaced(state, world, pos, newState, moved);
        }
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
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }


}