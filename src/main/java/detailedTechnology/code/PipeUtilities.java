package detailedTechnology.code;

import detailedTechnology.blocks.machines.auto.blockEntity.BronzeBoilerEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.BronzeWattDoubleActingSteamEngineEntity;
import detailedTechnology.blocks.pipes.blockEntity.*;
import detailedTechnology.blocks.tanks.blockEntity.BronzeBarrelEntity;
import detailedTechnology.blocks.tanks.blockEntity.FirebrickBarrelEntity;
import detailedTechnology.blocks.tanks.blockEntity.WoodBarrelEntity;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.machine.Pipes;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class PipeUtilities {

    public World world;
    public BlockPos pos;
    public int maximumCapacitance;
    public int liquidAmount;
    public String liquidName;
    public boolean[] connection;
    BlockPos[] blocksPos;

    public PipeUtilities(World world, BlockPos pos, int maximumCapacitance, int liquidAmount, String liquidName) {
        this.world = world;
        this.pos = pos;
        this.maximumCapacitance = maximumCapacitance;
        this.liquidAmount = liquidAmount;
        this.liquidName = liquidName;
        this.blocksPos = new BlockPos[]{pos.north(), pos.south(), pos.west(), pos.east(), pos.up(), pos.down()};
    }

    public void setConnection(boolean[] connection) {
        this.connection = connection;
    }

    public boolean tankToThis(TankUtilities tank) {
        if ((!tank.liquidName.equals("air")) && this.liquidAmount == 0) {
            this.liquidName = tank.liquidName;
            if (tank.liquidAmount > this.maximumCapacitance) {
                tank.liquidAmount -= this.maximumCapacitance;
                this.liquidAmount = this.maximumCapacitance;
            } else {
                this.liquidAmount = tank.liquidAmount;
                tank.liquidAmount = 0;
                tank.liquidName = "air";
            }
            return true;
        }
        if (tank.liquidName.equals(this.liquidName)) {
            if (tank.liquidAmount > this.maximumCapacitance - liquidAmount) {
                tank.liquidAmount -= this.maximumCapacitance - liquidAmount;
                this.liquidAmount = this.maximumCapacitance;
            } else {
                this.liquidAmount += tank.liquidAmount;
                tank.liquidAmount = 0;
                tank.liquidName = "air";
            }
            return true;
        }
        return false;
    }

    public boolean receiveLiquidFromTank() {
        if (connection[4] && this.liquidAmount < this.maximumCapacitance) {
            BlockPos up = pos.up();
            String upBlock = world.getBlockState(up).getBlock().getName().getString();
            TankUtilities tank;
            boolean empty = this.liquidAmount == 0;
            if (upBlock.equals(Pipes.woodBarrel.getName().getString())) {
                tank = ((WoodBarrelEntity) (Objects.requireNonNull(world.getBlockEntity(up)))).tankUtilities;
                return tankToThis(tank);
            } else if (upBlock.equals(Pipes.bronzeBarrel.getName().getString())) {
                tank = ((BronzeBarrelEntity) (Objects.requireNonNull(world.getBlockEntity(up)))).tankUtilities;
                return tankToThis(tank);
            } else if (upBlock.equals(Pipes.firebrickBarrel.getName().getString())) {
                tank = ((FirebrickBarrelEntity) (Objects.requireNonNull(world.getBlockEntity(up)))).tankUtilities;
                return tankToThis(tank);
            }
        }
        return false;
    }

    public boolean receiveGasFromTank() {
        boolean result = false;
        if (connection[5] && this.liquidAmount < this.maximumCapacitance &&
                (this.liquidName.equals("steam") || this.liquidAmount == 0)) {
            String downBlock = world.getBlockState(blocksPos[5]).getBlock().getName().getString();
            int steamPressureDifference;
            boolean empty = this.liquidAmount == 0;
            if (downBlock.equals(Auto.bronzeBoiler.getName().getString())) {
                BronzeBoilerEntity bronzeBoilerEntity =
                        (BronzeBoilerEntity) Objects.requireNonNull(world.getBlockEntity(blocksPos[5]));
                steamPressureDifference = (int) (bronzeBoilerEntity.steamPressure - 104-this.liquidAmount);
                if (steamPressureDifference > 0) {
                    if (this.liquidAmount == 0) {
                        this.liquidName = "steam";
                    }
                    if (steamPressureDifference/2 > this.maximumCapacitance - this.liquidAmount) {
                        bronzeBoilerEntity.steamPressure -= (this.maximumCapacitance - this.liquidAmount);
                        this.liquidAmount = this.maximumCapacitance;
                    } else {
                        bronzeBoilerEntity.steamPressure -= (float)(steamPressureDifference/2);
                        this.liquidAmount += steamPressureDifference/2;
                    }
                    result = true;
                }
            }
        }
        return result;
    }

    public boolean thisToTank(TankUtilities tank) {
        if (this.liquidAmount == 0) {
            return false;
        }
        if (tank.liquidName.equals(this.liquidName)) {
            if (this.liquidAmount > tank.MaximumCapacitance - tank.liquidAmount) {
                this.liquidAmount -= tank.MaximumCapacitance - tank.liquidAmount;
                tank.liquidAmount = tank.MaximumCapacitance;
            } else {
                tank.liquidAmount += this.liquidAmount;
                this.liquidAmount = 0;
                this.liquidName = "air";
            }
            return true;
        }
        if (tank.liquidName.equals("air")) {
            tank.liquidName = this.liquidName;
            if (this.liquidAmount > tank.MaximumCapacitance) {
                this.liquidAmount -= tank.MaximumCapacitance;
                tank.liquidAmount = tank.MaximumCapacitance;
            } else {
                tank.liquidAmount = this.liquidAmount;
                this.liquidAmount = 0;
                this.liquidName = "air";
            }
            return true;
        }
        return false;
    }

    public boolean giveLiquidToTank() {
        if (this.liquidName.equals("steam")) {
            return giveSteamToTank();
        }
        boolean result = false;
        for (int i = 0; i < 6; i++) {
            if (i != 4 && connection[i]) {
                String theBlock = world.getBlockState(blocksPos[i]).getBlock().getName().getString();
                TankUtilities tank;
                if (theBlock.equals(Pipes.woodBarrel.getName().getString())) {
                    tank = ((WoodBarrelEntity)
                            (Objects.requireNonNull(world.getBlockEntity(blocksPos[i])))).tankUtilities;
                    result = result | thisToTank(tank);
                } else if (theBlock.equals(Pipes.bronzeBarrel.getName().getString())) {
                    tank = ((BronzeBarrelEntity)
                            (Objects.requireNonNull(world.getBlockEntity(blocksPos[i])))).tankUtilities;
                    result = result | thisToTank(tank);
                } else if (theBlock.equals(Pipes.firebrickBarrel.getName().getString())) {
                    tank = ((FirebrickBarrelEntity)
                            (Objects.requireNonNull(world.getBlockEntity(blocksPos[i])))).tankUtilities;
                    result = result | thisToTank(tank);
                } else if (theBlock.equals(Auto.bronzeBoiler.getName().getString())) {
                    tank = ((BronzeBoilerEntity)
                            (Objects.requireNonNull(world.getBlockEntity(blocksPos[i])))).tankUtilities;
                    result = result | thisToTank(tank);
                }
            }
        }
        return result;
    }

    public boolean giveSteamToTank() {
        boolean result = false;
        for (int i = 0; i < 5; i++) {
            if (connection[i]) {
                int oppositeIndex = 2 * (i / 2) + (i + 1) % 2;
                String theBlock = world.getBlockState(blocksPos[i]).getBlock().getName().getString();
                if (theBlock.equals(Auto.bronzeWattDoubleActingSteamEngine.getName().getString())) {
                    BronzeWattDoubleActingSteamEngineEntity entity = (BronzeWattDoubleActingSteamEngineEntity)
                            (Objects.requireNonNull(world.getBlockEntity(blocksPos[i])));
                    if(entity.connection[oppositeIndex]){
                        int difference = BronzeWattDoubleActingSteamEngineEntity.maximumCapacitance-entity.liquidAmount;
                        if(this.liquidAmount>difference){
                            this.liquidAmount-=difference;
                            entity.liquidAmount+=difference;
                        }else{
                            entity.liquidAmount+=this.liquidAmount;
                            this.liquidAmount=0;
                        }
                        result = true;
                    }
                }
            }
        }
        return result;
    }

    public boolean thisToPipe(int i, PipeUtilities pipe) {
        int oppositeIndex = 2 * (i / 2) + (i + 1) % 2;
        boolean empty = this.liquidAmount == 0;
        if (pipe == null || !pipe.connection[oppositeIndex]) {
            return false;
        }
        int exchange = (liquidAmount - pipe.liquidAmount) / 2;
        if (empty) {
            if (pipe.liquidAmount == 0) {
                return false;
            }
            this.liquidName = pipe.liquidName;
            liquidAmount = -exchange;
            pipe.liquidAmount += exchange;
            return true;
        }
        if (this.liquidName.equals(pipe.liquidName)) {
            liquidAmount -= exchange;
            pipe.liquidAmount += exchange;
            return true;
        }
        if (pipe.liquidAmount == 0) {
            pipe.liquidName = this.liquidName;
            liquidAmount -= exchange;
            pipe.liquidAmount = exchange;
            return true;
        }
        return false;
    }

    public boolean transportWithPipes() {
        int oppositeIndex;
        boolean result = false;
        for (int i = 0; i < 6; i++) {
            if (connection[i]) {
                String upBlock = world.getBlockState(blocksPos[i]).getBlock().getName().getString();
                PipeUtilities pipe;
                if (upBlock.equals(Pipes.bronzeStraightPipe.getName().getString())) {
                    pipe = ((BronzeStraightPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                } else if (upBlock.equals(Pipes.bronzeBentPipe.getName().getString())) {
                    pipe = ((BronzeBentPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                } else if (upBlock.equals(Pipes.bronzeTPipe.getName().getString())) {
                    pipe = ((BronzeTPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                } else if (upBlock.equals(Pipes.bronzeCrossPipe.getName().getString())) {
                    pipe = ((BronzeCrossPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                } else if (upBlock.equals(Pipes.bronzeFiveWayPipe.getName().getString())) {
                    pipe = ((BronzeFiveWayPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                } else if (upBlock.equals(Pipes.bronzeSixWayPipe.getName().getString())) {
                    pipe = ((BronzeSixWayPipeEntity)
                            Objects.requireNonNull(world.getBlockEntity(blocksPos[i]))).pipeUtilities;
                    result = result | thisToPipe(i, pipe);
                }
            }
        }
        return result;
    }

    public void checkLeakage() {
        for (int i = 0; i < 6; i++) {
            if (connection[i] && world.getBlockState(blocksPos[i]).getBlock().getName().getString()
                    .equals(Blocks.AIR.getName().getString())) {
                this.liquidAmount -= this.liquidAmount / 10;
            }
        }
    }

    public void standardTickTasks() {
        receiveLiquidFromTank();
        receiveGasFromTank();
        checkLeakage();
        giveLiquidToTank();
        transportWithPipes();
        if (this.liquidAmount == 0) {
            this.liquidName = "air";
        }
    }
}
