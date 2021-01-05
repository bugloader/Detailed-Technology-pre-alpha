package detailedTechnology.blocks.pipes.block;

import detailedTechnology.blocks.pipes.blockEntity.BronzeSixWayPipeEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class SixWayPipe extends Block implements BlockEntityProvider {

    private static final VoxelShape SHAPE = Block.createCuboidShape(4, 4, 4, 12, 12, 12);
    String material;

    public SixWayPipe(String material) {
        super(FabricBlockSettings.of(Material.METAL, MaterialColor.STONE)
                .strength(2f, 3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
        this.material = material;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BronzeSixWayPipeEntity();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    public boolean[] getConnected(int facing) {
        boolean[] connection = new boolean[6];
        for (int i = 0; i < 6; i++) {
            connection[i] = true;
        }
        return connection;
    }
}