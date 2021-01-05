package detailedTechnology.blocks.pipes.blockEntity;

import detailedTechnology.code.PipeUtilities;
import detailedTechnology.group.machine.Pipes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

public class BronzeSixWayPipeEntity extends BlockEntity implements Tickable {

    public static int maximumCapacitance = 140;
    public String liquidName;
    public int liquidAmount;
    public PipeUtilities pipeUtilities;
    private int updateTimer = 0;
    private boolean initialised = false;

    public BronzeSixWayPipeEntity() {
        super(Pipes.bronzeSixWayPipeEntity);
        liquidName = "air";
        liquidAmount = 0;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        liquidName = tag.getString("liquid name");
        liquidAmount = tag.getInt("liquid amount");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putString("liquid name", liquidName);
        tag.putInt("liquid amount", liquidAmount);
        return tag;
    }

    private void checkInitialise(){
        if(!initialised){
            pipeUtilities = new PipeUtilities(world,pos,maximumCapacitance,liquidAmount,liquidName);
            pipeUtilities.setConnection(new boolean[]{true,true,true,true,true,true});
            initialised = true;
        }
    }

    private void checkBreak() {
        if (this.liquidName.equals("lava")) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    private void updateLiquid(){
        this.liquidAmount = pipeUtilities.liquidAmount;
        this.liquidName = pipeUtilities.liquidName;
    }

    @Override
    public void tick() {
        checkInitialise();
        pipeUtilities.standardTickTasks();
        updateLiquid();
        checkBreak();
    }
}
