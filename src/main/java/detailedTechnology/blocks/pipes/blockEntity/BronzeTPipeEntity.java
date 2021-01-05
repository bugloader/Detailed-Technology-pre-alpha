package detailedTechnology.blocks.pipes.blockEntity;

import detailedTechnology.blocks.pipes.block.TPipe;
import detailedTechnology.code.PipeUtilities;
import detailedTechnology.group.machine.Pipes;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Tickable;

import java.util.Objects;

public class BronzeTPipeEntity extends BlockEntity implements Tickable {

    public static int maximumCapacitance = 140;
    public String liquidName;
    public int liquidAmount;
    public PipeUtilities pipeUtilities;
    private int updateTimer = 0;
    private boolean initialised = false;

    public BronzeTPipeEntity() {
        super(Pipes.bronzeTPipeEntity);
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
            initialised = true;
        }
    }

    private void updateConnection(){
        if(updateTimer==0) {
            int facing = Objects.requireNonNull(world).getBlockState(pos).get(TPipe.PIPE_FACING);
            pipeUtilities.setConnection(((TPipe) world.getBlockState(pos).getBlock()).getConnected(facing));
        }
        updateTimer = (updateTimer+1)%20;
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
        updateConnection();
        pipeUtilities.standardTickTasks();
        updateLiquid();
        checkBreak();
    }
}
