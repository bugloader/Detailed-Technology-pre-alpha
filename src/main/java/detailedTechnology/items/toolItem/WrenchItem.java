package detailedTechnology.items.toolItem;

import detailedTechnology.blocks.pipes.block.*;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.machine.Pipes;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;

import java.util.Objects;

public class WrenchItem extends ToolItem {
    private final int wrenchLevel;
    public WrenchItem(ToolMaterial material, ItemGroup itemGroup){
        super(material,  new FabricItemSettings().group(itemGroup));
        wrenchLevel = material.getMiningLevel()-1;
    }

    public int getWrenchLevel() {
        return wrenchLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        String blockName = context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString();
        int facing;
        boolean sneaking = Objects.requireNonNull(context.getPlayer()).isSneaking();
        if(blockName.equals(Pipes.bronzeStraightPipe.getName().getString())) {
            facing=context.getWorld().getBlockState(context.getBlockPos()).get(StraightPipe.PIPE_FACING);
            facing = sneaking?(facing+1)%3:(facing+2)%3;
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(StraightPipe.PIPE_FACING,facing));
            return ActionResult.SUCCESS;
        }else if(blockName.equals(Pipes.bronzeBentPipe.getName().getString())) {
            facing=context.getWorld().getBlockState(context.getBlockPos()).get(BentPipe.PIPE_FACING);
            facing = sneaking?(facing+1)%12:(facing+11)%12;
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(BentPipe.PIPE_FACING,facing));
            return ActionResult.SUCCESS;
        }else if(blockName.equals(Pipes.bronzeTPipe.getName().getString())) {
            facing=context.getWorld().getBlockState(context.getBlockPos()).get(TPipe.PIPE_FACING);
            facing = sneaking?(facing+1)%12:(facing+11)%12;
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(TPipe.PIPE_FACING,facing));
            return ActionResult.SUCCESS;
        }else if(blockName.equals(Pipes.bronzeCrossPipe.getName().getString())) {
            facing=context.getWorld().getBlockState(context.getBlockPos()).get(CrossPipe.PIPE_FACING);
            facing = sneaking?(facing+1)%3:(facing+2)%3;
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(CrossPipe.PIPE_FACING,facing));
            return ActionResult.SUCCESS;
        }else if(blockName.equals(Pipes.bronzeFiveWayPipe.getName().getString())) {
            facing=context.getWorld().getBlockState(context.getBlockPos()).get(FiveWayPipe.PIPE_FACING);
            facing = sneaking?(facing+1)%6:(facing+5)%6;
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(FiveWayPipe.PIPE_FACING,facing));
            return ActionResult.SUCCESS;
        }else if(blockName.equals(Auto.bronzeWattDoubleActingSteamEngine.getName().getString())) {
            Direction f =context.getWorld().getBlockState(context.getBlockPos()).get(Properties.HORIZONTAL_FACING);
            switch (f){
                case NORTH:
                    f=Direction.EAST;
                    break;
                case EAST:
                    f=Direction.SOUTH;
                    break;
                case SOUTH:
                    f=Direction.WEST;
                    break;
                case WEST:
                    f=Direction.NORTH;
                    break;
            }
            context.getWorld().setBlockState(context.getBlockPos(),
                    context.getWorld().getBlockState(context.getBlockPos()).with(Properties.HORIZONTAL_FACING,f));
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }

}