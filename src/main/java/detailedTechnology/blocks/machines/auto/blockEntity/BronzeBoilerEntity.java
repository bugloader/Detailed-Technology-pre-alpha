package detailedTechnology.blocks.machines.auto.blockEntity;

import detailedTechnology.code.TankUtilities;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.items.Materials;
import detailedTechnology.blocks.machines.auto.screenHandler.BronzeBoilerScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;


public class BronzeBoilerEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public static final int MaximumCapacitance = 1000;
    public static final float BoilerHeatCapacitance = 14.08f;
    public static final String liquidName = "water";
    public static final int MaximumPressure = 1000;
    public int liquidAmount;
    public int miliLiquidAmount;
    public float temperature;
    public float steamPressure;
    public float tempEnergy;
    public TankUtilities tankUtilities;
    float heatCapacitance;
    boolean stable=false;
    float boilingPoint;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return liquidAmount;
            }
            if (index == 1) {
                return (int) temperature;
            }
            if (index == 2) {
                return (int) heatCapacitance;
            } else {
                return (int) steamPressure;
            }
        }

        @Override
        public void set(int index, int value) {
            liquidAmount = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 4;
        }
    };

    public BronzeBoilerEntity() {
        super(Auto.bronzeBoilerEntity);
        liquidAmount = 0;
        tankUtilities = new TankUtilities(MaximumCapacitance, liquidAmount, liquidName);
        temperature = 20;
        steamPressure = 101;
        tempEnergy = 0;
        miliLiquidAmount = 0;
        boilingPoint = Materials.WATER.getBoilingPoint();
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        liquidAmount = tag.getInt("liquid amount");
        temperature = tag.getFloat("temperature");
        steamPressure = tag.getFloat("steam pressure");
        tankUtilities = new TankUtilities(MaximumCapacitance, liquidAmount, liquidName);
        heatCapacitance = liquidAmount * Materials.WATER.getSpecificHeatCapacity() / 1000 + BoilerHeatCapacitance;
        tempEnergy = 0;
        miliLiquidAmount = 0;
        boilingPoint = Materials.WATER.getBoilingPoint();
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        Inventories.fromTag(tag, inventory);
        tag.putInt("liquid amount", liquidAmount);
        tag.putFloat("temperature", temperature);
        tag.putFloat("steam pressure", steamPressure);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BronzeBoilerScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }


    private void checkBreak() {
        if (steamPressure > MaximumPressure || temperature > Materials.BRONZE.getMeltingPoint()) {
            System.out.println(temperature);
            Objects.requireNonNull(world).breakBlock(pos, false);
        }
    }

    public void loadTank(TankUtilities tankUtilties) {
        this.tankUtilities = tankUtilties;
        this.liquidAmount = tankUtilties.liquidAmount;
    }

    private void updateHeatCapacitance() {
        heatCapacitance = liquidAmount * Materials.WATER.getSpecificHeatCapacity() / 1000 + BoilerHeatCapacitance;
    }

    private void updateTemperature() {
        float temperature = 20;
        double specificHeatRatio = 0.1f;
        assert this.world != null;
        String baseName = this.world.getBlockState(pos.down()).getBlock().getName().getString();
        if (baseName.equals(Auto.bronzeCombustionChamber.getName().getString())) {
            temperature = ((BronzeCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.BRONZE.getSpecificHeatCapacityOfVolume()/2 / heatCapacitance;
            ((BronzeCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 100.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 100.0 * specificHeatRatio;
        } else if (baseName.equals(Auto.firebrickCombustionChamber.getName().getString())) {
            temperature = ((FirebrickCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.FIREBRICK.getSpecificHeatCapacityOfVolume()/2 / heatCapacitance;
            ((FirebrickCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 100.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 100.0 * specificHeatRatio;
        }else if (baseName.equals(Auto.steelCombustionChamber.getName().getString())) {
            temperature = ((SteelCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.STEEL.getSpecificHeatCapacityOfVolume()/2 / heatCapacitance;
            ((SteelCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 100.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 100.0 * specificHeatRatio;
        }else if (baseName.equals(Auto.highAluminaFirebrickCombustionChamber.getName().getString())) {
            temperature = ((HighAluminaFirebrickCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.HIGH_ALUMINA_FIREBRICK.getSpecificHeatCapacityOfVolume()/2 / heatCapacitance;
            ((HighAluminaFirebrickCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 100.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 100.0 * specificHeatRatio;
        }else{
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        }
    }

    private void updateTempEnergy() {
        double c = Math.log10(steamPressure/0.611);
        boilingPoint = (float)(235*(c/(7.45-c)));
        if (liquidAmount > 0 && temperature > boilingPoint) {
            tempEnergy += (this.temperature - boilingPoint) * heatCapacitance;
            this.temperature = boilingPoint;
        }
    }

    //18.01528
    //pv=nrt
    //delta pv = delta n rt
    //delta p = delta n rt/v
    private void updateSteam() {
        if (temperature < boilingPoint) {
            steamPressure = 101;
        } else {
            int waterLost = (int) (tempEnergy / 2266);
            if (miliLiquidAmount < waterLost) {
                int miliWater = 1000 * liquidAmount + miliLiquidAmount;
                if (miliWater < waterLost) {
                    waterLost = miliWater;
                }
                miliWater -= waterLost;
                liquidAmount = miliWater / 1000;
                miliLiquidAmount = miliWater % 1000;
            } else {
                miliLiquidAmount -= waterLost;
            }
            steamPressure += 55.56 * waterLost * Materials.R * (temperature + 273.15) / 1000000.0;
            tempEnergy -= waterLost * 2266;
        }
        this.tankUtilities.liquidAmount=liquidAmount;
    }

    @Override
    public void tick() {
        tankUtilities.inputOnlyInventoryManipulate((Inventory) this);
        this.liquidAmount = tankUtilities.liquidAmount;
        updateHeatCapacitance();
        updateTemperature();
        updateTempEnergy();
        updateSteam();
        checkBreak();
    }
}
