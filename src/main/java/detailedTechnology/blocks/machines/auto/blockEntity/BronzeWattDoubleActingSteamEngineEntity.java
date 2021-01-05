package detailedTechnology.blocks.machines.auto.blockEntity;

import detailedTechnology.blocks.machines.auto.block.BronzeWattDoubleActingSteamEngine;
import detailedTechnology.group.machine.Auto;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.Objects;


public class BronzeWattDoubleActingSteamEngineEntity extends BlockEntity implements Tickable {

    public static final int maximumCapacitance = 2;
    public static final String liquidName = "steam";
    public int liquidAmount;
    private int workingTime = 0;
    private BlockPos outPos;
    public boolean[] connection = new boolean[6];

    public BronzeWattDoubleActingSteamEngineEntity() {
        super(Auto.bronzeWattDoubleActingSteamEngineEntity);
        liquidAmount = 0;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        liquidAmount = tag.getInt("liquid amount");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        tag.putInt("liquid amount", liquidAmount);
        return tag;
    }

    public void updateWorking() {
        updateOutPos();
        if (workingTime == 0 && liquidAmount > 0) {
            liquidAmount--;
            doWork();
        }
        workingTime = (workingTime + 1) % 5;
    }

    public void updateOutPos() {
        Direction facing = Objects.requireNonNull(world).getBlockState(pos).get(Properties.HORIZONTAL_FACING);
        for (int i = 0; i <6; i++) {
            connection[i]=false;
        }
        switch (facing) {
            case NORTH:
                outPos = pos.west();
                connection[3]=true;
                break;
            case SOUTH:
                outPos = pos.east();
                connection[2]=true;
                break;
            case EAST:
                outPos = pos.north();
                connection[1]=true;
                break;
            case WEST:
                outPos = pos.south();
                connection[0]=true;
                break;
        }
    }

    public void changeModel(){
        BlockState blockState = Objects.requireNonNull(world).getBlockState(pos);
        int currentModel = blockState.get(BronzeWattDoubleActingSteamEngine.WORK_PROGRESS);
        world.setBlockState(pos,blockState.with(BronzeWattDoubleActingSteamEngine.WORK_PROGRESS,(currentModel+1)%2));
    }

    public void doRunnerWithGear(BlockState blockState){
        assert world != null;
        Direction direction = blockState.get(Properties.HORIZONTAL_FACING);
        if (direction.equals(Direction.NORTH)) {
            world.setBlockState(outPos, blockState.with(Properties.HORIZONTAL_FACING, Direction.EAST));
        } else if (direction.equals(Direction.EAST)) {
            world.setBlockState(outPos, blockState.with(Properties.HORIZONTAL_FACING, Direction.SOUTH));
        } else if (direction.equals(Direction.SOUTH)) {
            world.setBlockState(outPos, blockState.with(Properties.HORIZONTAL_FACING, Direction.WEST));
        } else if (direction.equals(Direction.WEST)) {
            world.setBlockState(outPos, blockState.with(Properties.HORIZONTAL_FACING, Direction.NORTH));
        }
    }

    public void doWork() {
        BlockState blockState = Objects.requireNonNull(world).getBlockState(outPos);
        if(blockState.getBlock().getName().getString().equals(Auto.stoneMileRunnerWithGear.getName().getString())){
            doRunnerWithGear(blockState);
            changeModel();
        }

    }

    @Override
    public void tick() {
        updateWorking();
    }
}
