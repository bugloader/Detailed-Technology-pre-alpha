package detailedTechnology.blocks.machines.auto.utilities;

import detailedTechnology.blocks.machines.auto.blockEntity.BronzeCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.FirebrickCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.HighAluminaFirebrickCombustionChamberEntity;
import detailedTechnology.blocks.machines.auto.blockEntity.SteelCombustionChamberEntity;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.machine.Auto;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Objects;

public class CrucibleUtilities {

    public Inventory inventory;
    public static final float OriginalHeatCapacitance = 20;
    public World world;
    public BlockPos pos;
    public String liquidName;
    public int liquidAmount;
    public float temperature;
    public float heatCapacitance;

    public final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return (int) temperature;
            }
            if (index == 1) {
                return (int) heatCapacitance;
            }
            if (index == 2) {
                for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                    if (liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) return i;
                }
                return -1;
            } else {
                return liquidAmount;
            }
        }

        @Override
        public void set(int index, int value) {
            liquidAmount = value;
        }

        @Override
        public int size() {
            return 4;
        }
    };

    public CrucibleUtilities() {
        liquidName = "air";
        temperature = 20;
        liquidAmount = 0;
        heatCapacitance = OriginalHeatCapacitance;
    }

    public void initialise(BlockPos pos, World world, Inventory inventory) {
        this.pos = pos;
        this.world = world;
        this.inventory = inventory;
    }


    private void updateHeatCapacitance() {
        if (inventory.getStack(0).getName().getString().equals(Ores.copperDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.COPPER.getSpecificHeatCapacityOfVolume() / 9;
        } else if (inventory.getStack(0).getName().getString().equals(Ores.tinDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.TIN.getSpecificHeatCapacityOfVolume() / 9;
        } else if (inventory.getStack(0).getName().getString().equals(Ores.bronzeDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.BRONZE.getSpecificHeatCapacityOfVolume() / 9;
        }else if (inventory.getStack(0).getName().getString().equals(Ores.ironDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.IRON.getSpecificHeatCapacityOfVolume() / 9;
        }else if (inventory.getStack(0).getName().getString().equals(Ores.aluminiumDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.ALUMINIUM.getSpecificHeatCapacityOfVolume() / 9;
        }else if (inventory.getStack(0).getName().getString().equals(Ores.silverDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.SILVER.getSpecificHeatCapacityOfVolume() / 9;
        }else if (inventory.getStack(0).getName().getString().equals(Ores.platinumDust.getName().getString())) {
            heatCapacitance = OriginalHeatCapacitance + Materials.PLATINUM.getSpecificHeatCapacityOfVolume() / 9;
        } else {
            heatCapacitance = OriginalHeatCapacitance;
        }

        switch (liquidName) {
            case "copper": //144*9=1296
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.COPPER.getSpecificHeatCapacityOfVolume();
                break;
            case "tin":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.TIN.getSpecificHeatCapacityOfVolume();
                break;
            case "bronze":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.BRONZE.getSpecificHeatCapacityOfVolume();
                break;
            case "iron":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.IRON.getSpecificHeatCapacityOfVolume();
                break;
            case "platinum":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.PLATINUM.getSpecificHeatCapacityOfVolume();
                break;
            case "steel":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.STEEL.getSpecificHeatCapacityOfVolume();
                break;
            case "gold":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.GOLD.getSpecificHeatCapacityOfVolume();
                break;
            case "silver":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.SILVER.getSpecificHeatCapacityOfVolume();
                break;
            case "aluminium":
                heatCapacitance += ((float) liquidAmount / 1296) * Materials.ALUMINIUM.getSpecificHeatCapacityOfVolume();
                break;
        }
    }

    private void updateTemperature() {
        float temperature = 15;
        double specificHeatRatio = 0.1f;
        if (world == null) {
            return;
        }
        String baseName = world.getBlockState(pos.down()).getBlock().getName().getString();
        if (baseName.equals(Auto.bronzeCombustionChamber.getName().getString())) {
            temperature = ((BronzeCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.BRONZE.getSpecificHeatCapacityOfVolume() / 2 / heatCapacitance;
            ((BronzeCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 50.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        } else if (baseName.equals(Auto.firebrickCombustionChamber.getName().getString())) {
            temperature = ((FirebrickCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.FIREBRICK.getSpecificHeatCapacityOfVolume() / 2 / heatCapacitance;
            ((FirebrickCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 50.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        } else if (baseName.equals(Auto.steelCombustionChamber.getName().getString())) {
            temperature = ((SteelCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.STEEL.getSpecificHeatCapacityOfVolume() / 2 / heatCapacitance;
            ((SteelCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 50.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        } else if (baseName.equals(Auto.highAluminaFirebrickCombustionChamber.getName().getString())) {
            temperature = ((HighAluminaFirebrickCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).utilities.temperature;
            specificHeatRatio = Materials.STEEL.getSpecificHeatCapacityOfVolume() / 2 / heatCapacitance;
            ((HighAluminaFirebrickCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .utilities.temperature -= (temperature - this.temperature) / 50.0 / specificHeatRatio;
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        } else {
            this.temperature += (temperature - this.temperature) / 50.0 * specificHeatRatio;
        }
    }

    private void updateLiquid() {
        int itemCount = inventory.getStack(0).getCount();
        String itemName = inventory.getStack(0).getName().getString();
        if (itemName.equals(Ores.copperDust.getName().getString()) && temperature > Materials.COPPER.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("copper") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "copper";
                }
            }
        } else if (itemName.equals(Ores.tinDust.getName().getString()) && temperature > Materials.TIN.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("tin") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "tin";
                }
            } else if (this.liquidName.equals("copper") && liquidAmount == 432) {
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidName = "bronze";
                liquidAmount = 576;
            }
        } else if (itemName.equals(Ores.bronzeDust.getName().getString()) && temperature > Materials.BRONZE.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("bronze") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "bronze";
                }
            }
        } else if (itemName.equals(Ores.ironDust.getName().getString()) && temperature > Materials.IRON.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("iron") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "iron";
                }
            }
        } else if (itemName.equals(Ores.silverDust.getName().getString()) && temperature > Materials.SILVER.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("silver") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "silver";
                }
            }
        } else if (itemName.equals(Ores.platinumDust.getName().getString()) && temperature > Materials.PLATINUM.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("platinum") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "platinum";
                }
            }
        } else if (itemName.equals(Ores.aluminiumDust.getName().getString()) && temperature > Materials.ALUMINIUM.getMeltingPoint()) {
            if (this.liquidName.equals("air") || (this.liquidName.equals("aluminium") && liquidAmount + 144 <= 576)) {//can melt
                if (itemCount == 1) {
                    inventory.setStack(0, Items.AIR.getDefaultStack());
                } else {
                    inventory.getStack(0).setCount(itemCount - 1);
                }
                this.liquidAmount += 144;
                if (this.liquidName.equals("air")) {
                    liquidName = "aluminium";
                }
            }
        }
    }

    public String getUnitLiquid() {
        float meltingPoint = 0;
        if (liquidAmount < 144) {
            return "air";
        } else {
            for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                if (Materials.MATERIAL_STATUSES.get(i).getName().equals(liquidName)) {
                    if (temperature < Materials.MATERIAL_STATUSES.get(i).getMeltingPoint()) return "air";
                    break;
                }
            }
            String result = liquidName;
            if (liquidAmount == 144) {
                liquidName = "air";
                liquidAmount = 0;
            } else {
                liquidAmount -= 144;
            }
            return result;
        }
    }

    public void normalTick() {
        float currentHeatCapacitance = heatCapacitance;
        updateHeatCapacitance();
        if (heatCapacitance > currentHeatCapacitance) {
            temperature = (temperature - 20) * currentHeatCapacitance / heatCapacitance + 20;
        }
        updateTemperature();
        updateLiquid();
    }
}
