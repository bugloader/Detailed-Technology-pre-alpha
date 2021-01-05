package detailedTechnology.blocks.machines.auto.utilities;

import detailedTechnology.group.items.Materials;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Items;
import net.minecraft.screen.PropertyDelegate;

public class CombustionChamberUtilities {

    public static final float charcoalUnitTemperature = 550;
    public static final float cokeUnitTemperature = 1000;
    public static final int charcoalUnitTime = 3600;
    public static final int cokeUnitTime = 2000;

    public final float burningRate;
    public int burningTime;
    public String fuelName;
    public float temperature;

    public final Inventory inventory;
    public final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            switch (index) {
                case 0:
                    return burningTime;
                case 1:
                    return (int) temperature;
                case 2:
                    if (fuelName.equals(Items.CHARCOAL.getName().getString())) {
                        return (int) (burningRate * charcoalUnitTemperature);
                    } else if (fuelName.equals(Materials.coke.getName().getString())) {
                        return (int) (burningRate * cokeUnitTemperature);
                    } else {
                        return 20;
                    }
                default:
                    return 0;
            }
        }

        @Override
        public void set(int index, int value) {
            burningTime = value;
        }

        @Override
        public int size() {
            return 3;
        }
    };

    public CombustionChamberUtilities(float burningRate, Inventory inventory) {
        burningTime = 0;
        fuelName = Items.AIR.getName().getString();
        temperature = 20;
        this.burningRate = burningRate;
        this.inventory = inventory;
    }

    private void burnNext() {
        fuelName = inventory.getStack(0).getName().getString();
        if (fuelName.equals(Items.CHARCOAL.getName().getString())) {
            this.burningTime = (int) (charcoalUnitTime / burningRate);
            if (inventory.getStack(0).getCount() == 1) {
                inventory.setStack(0, Items.AIR.getDefaultStack());
            } else {
                inventory.getStack(0).setCount(inventory.getStack(0).getCount() - 1);
            }
        } else if (fuelName.equals(Materials.coke.getName().getString())) {
            this.burningTime = (int) (cokeUnitTime / burningRate);
            if (inventory.getStack(0).getCount() == 1) {
                inventory.setStack(0, Items.AIR.getDefaultStack());
            } else {
                inventory.getStack(0).setCount(inventory.getStack(0).getCount() - 1);
            }
        }
    }

    private void getNextTemp() {
        if (fuelName.equals(Items.CHARCOAL.getName().getString())) {
            temperature += (charcoalUnitTemperature * burningRate - temperature) / 100.0;
        } else if (fuelName.equals(Materials.coke.getName().getString())) {
            temperature += (cokeUnitTemperature * burningRate - temperature) / 100.0;
        }
        burningTime--;
    }

    public void normalTick() {
        if (burningTime == 0) {
            burnNext();
        }
        if (burningTime > 0) {
            getNextTemp();
        }
        temperature -= (temperature - 20) / 5000.0;
    }

}
