package detailedTechnology.blocks.machines.auto.structure;

import detailedTechnology.DetailedTechnology;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class PrimitiveBlastFurnaceStructure {

    public World world;
    public BlockPos controllerPos;
    public BlockPos[] airBlocksPos = new BlockPos[3];
    public BlockPos[] highAluminaFirebrickBlocksPos = new BlockPos[24];

    private void initialise(Direction direction){
        switch (direction){
            case NORTH:
                airBlocksPos[0] = controllerPos.north();
                highAluminaFirebrickBlocksPos[17] = airBlocksPos[0].north();
                highAluminaFirebrickBlocksPos[18] = highAluminaFirebrickBlocksPos[17].west();
                highAluminaFirebrickBlocksPos[19] = highAluminaFirebrickBlocksPos[17].east();
                highAluminaFirebrickBlocksPos[20] = controllerPos.west();
                highAluminaFirebrickBlocksPos[21] = controllerPos.east();
                highAluminaFirebrickBlocksPos[22] = airBlocksPos[0].west();
                highAluminaFirebrickBlocksPos[23] = airBlocksPos[0].east();
                break;
            case SOUTH:
                airBlocksPos[0] = controllerPos.south();
                highAluminaFirebrickBlocksPos[17] = airBlocksPos[0].south();
                highAluminaFirebrickBlocksPos[18] = highAluminaFirebrickBlocksPos[17].west();
                highAluminaFirebrickBlocksPos[19] = highAluminaFirebrickBlocksPos[17].east();
                highAluminaFirebrickBlocksPos[20] = controllerPos.west();
                highAluminaFirebrickBlocksPos[21] = controllerPos.east();
                highAluminaFirebrickBlocksPos[22] = airBlocksPos[0].west();
                highAluminaFirebrickBlocksPos[23] = airBlocksPos[0].east();
                break;
            case WEST:
                airBlocksPos[0] = controllerPos.west();
                highAluminaFirebrickBlocksPos[17] = airBlocksPos[0].west();
                highAluminaFirebrickBlocksPos[18] = highAluminaFirebrickBlocksPos[17].north();
                highAluminaFirebrickBlocksPos[19] = highAluminaFirebrickBlocksPos[17].south();
                highAluminaFirebrickBlocksPos[20] = controllerPos.north();
                highAluminaFirebrickBlocksPos[21] = controllerPos.south();
                highAluminaFirebrickBlocksPos[22] = airBlocksPos[0].north();
                highAluminaFirebrickBlocksPos[23] = airBlocksPos[0].south();
                break;
            case EAST:
                airBlocksPos[0] = controllerPos.east();
                highAluminaFirebrickBlocksPos[17] = airBlocksPos[0].east();
                highAluminaFirebrickBlocksPos[18] = highAluminaFirebrickBlocksPos[17].north();
                highAluminaFirebrickBlocksPos[19] = highAluminaFirebrickBlocksPos[17].south();
                highAluminaFirebrickBlocksPos[20] = controllerPos.north();
                highAluminaFirebrickBlocksPos[21] = controllerPos.south();
                highAluminaFirebrickBlocksPos[22] = airBlocksPos[0].north();
                highAluminaFirebrickBlocksPos[23] = airBlocksPos[0].south();
                break;
        }
        airBlocksPos[1] = airBlocksPos[0].up();
        airBlocksPos[2] = airBlocksPos[1].up();
        highAluminaFirebrickBlocksPos[0] = airBlocksPos[0].down();
        highAluminaFirebrickBlocksPos[1] = airBlocksPos[1].north();
        highAluminaFirebrickBlocksPos[2] = airBlocksPos[1].south();
        highAluminaFirebrickBlocksPos[3] = airBlocksPos[1].west();
        highAluminaFirebrickBlocksPos[4] = airBlocksPos[1].east();
        highAluminaFirebrickBlocksPos[5] = airBlocksPos[2].north();
        highAluminaFirebrickBlocksPos[6] = airBlocksPos[2].south();
        highAluminaFirebrickBlocksPos[7] = airBlocksPos[2].west();
        highAluminaFirebrickBlocksPos[8] = airBlocksPos[2].east();
        highAluminaFirebrickBlocksPos[9] = highAluminaFirebrickBlocksPos[0].north();
        highAluminaFirebrickBlocksPos[10] = highAluminaFirebrickBlocksPos[0].south();
        highAluminaFirebrickBlocksPos[11] = highAluminaFirebrickBlocksPos[0].west();
        highAluminaFirebrickBlocksPos[12] = highAluminaFirebrickBlocksPos[0].east();
        highAluminaFirebrickBlocksPos[13] = highAluminaFirebrickBlocksPos[9].west();
        highAluminaFirebrickBlocksPos[14] = highAluminaFirebrickBlocksPos[9].east();
        highAluminaFirebrickBlocksPos[15] = highAluminaFirebrickBlocksPos[10].west();
        highAluminaFirebrickBlocksPos[16] = highAluminaFirebrickBlocksPos[10].east();
    }

    private static String getBlockName(World world,BlockPos pos){
        return world.getBlockState(pos).getBlock().getName().getString();
    }

    public boolean isValid(){
        if(world==null) return false;
        for(int i=0;i<3;i++){
            if(!getBlockName(world,airBlocksPos[i]).equals(Blocks.AIR.getName().getString())) {
                return false;
            }
        }
        for(int i=0;i<24;i++){
            if(!getBlockName(world,highAluminaFirebrickBlocksPos[i]).equals(
                    DetailedTechnology.highAluminaFirebrickBlock.getName().getString())) {
                return false;
            }
        }
        return true;
    }

    public PrimitiveBlastFurnaceStructure(World world, BlockPos controllerPos, Direction facing){
        this.world=world;
        this.controllerPos=controllerPos;
        initialise(facing);
    }

}
