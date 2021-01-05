package detailedTechnology.blocks.machines.manual.utilities;

import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.items.Materials;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.screen.PropertyDelegate;

public class ModelUtilities {
    public static final int GEAR_NUM = 1;
    public static final int INGOT_NUM = 1;
    public static final int PLATE_NUM = 1;
    public static final int ROD_NUM = 2;
    Inventory inventory;
    public String liquidName;
    public float temperature;
    public String type;

    public final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return (int) temperature;
            }
            for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                if (liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) return i;
            }
            return -1;
        }

        @Override
        public void set(int index, int value) {
            temperature = value;
        }

        @Override
        public int size() {
            return 2;
        }
    };

    public ModelUtilities(String type) {
        liquidName = "air";
        temperature = 20;
        this.type = type;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public void receiveUnitLiquid(String liquidName, float temperature) {
        float heatCapacitance = 15;
        for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
            if (liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) {
                heatCapacitance = Materials.MATERIAL_STATUSES.get(i).getSpecificHeatCapacityOfVolume() / 9;
            }
        }
        this.temperature = (this.temperature * 15 + temperature * heatCapacitance) / (15 + heatCapacitance);
        this.liquidName = liquidName;
    }

    private void updateTemperature() {
        float temperature = 20;
        this.temperature += (temperature - this.temperature) / 100.0;
    }

    private void solidInto(Item item, int num){
        int itemCount = inventory.getStack(0).getCount();
        if (itemCount == 0) {
            inventory.setStack(0, item.getDefaultStack());
            inventory.getStack(0).setCount(num);
            liquidName = "air";
        } else if (itemCount+num <= 64 &&
                inventory.getStack(0).getName().getString().equals(item.getName().getString())) {
            inventory.getStack(0).setCount(itemCount + num);
            liquidName = "air";
        }
    }

    private void gearSolid() {
        switch (liquidName) {
            case "copper":
                solidInto(Ores.copperGear,GEAR_NUM);
                break;
            case "tin":
                solidInto(Ores.tinGear,GEAR_NUM);
                break;
            case "bronze":
                solidInto(Ores.bronzeGear,GEAR_NUM);
                break;
            case "iron":
                solidInto(Ores.ironGear,GEAR_NUM);
                break;
        }
    }

    private void rodSolid() {
        switch (liquidName) {
            case "copper":
                solidInto(Ores.copperRod,ROD_NUM);
                break;
            case "tin":
                solidInto(Ores.tinRod,ROD_NUM);
                break;
            case "bronze":
                solidInto(Ores.bronzeRod,ROD_NUM);
                break;
            case "iron":
                solidInto(Ores.ironRod,ROD_NUM);
                break;
            case "steel":
                solidInto(Ores.steelRod,ROD_NUM);
                break;
        }
    }

    private void ingotSolid(){
        switch (liquidName) {
            case "copper":
                solidInto(Ores.copperIngot,INGOT_NUM);
                break;
            case "tin":
                solidInto(Ores.tinIngot,INGOT_NUM);
                break;
            case "bronze":
                solidInto(Ores.bronzeIngot,INGOT_NUM);
                break;
            case "iron":
                solidInto(Items.IRON_INGOT,INGOT_NUM);
                break;
            case "steel":
                solidInto(Ores.steelIngot,INGOT_NUM);
                break;
            case "gold":
                solidInto(Items.GOLD_INGOT,INGOT_NUM);
                break;
            case "silver":
                solidInto(Ores.silverIngot,INGOT_NUM);
                break;
            case "platinum":
                solidInto(Ores.platinumIngot,INGOT_NUM);
                break;
            case "aluminium":
                solidInto(Ores.aluminiumIngot,INGOT_NUM);
                break;
        }
    }

    private void plateSolid(){
        switch (liquidName) {
            case "copper":
                solidInto(Ores.copperPlate,PLATE_NUM);
                break;
            case "tin":
                solidInto(Ores.tinPlate,PLATE_NUM);
                break;
            case "bronze":
                solidInto(Ores.bronzePlate,PLATE_NUM);
                break;
            case "iron":
                solidInto(Ores.ironPlate,ROD_NUM);
                break;
            case "steel":
                solidInto(Ores.steelPlate,ROD_NUM);
                break;
        }
    }

    private void updateLiquid() {
        float solidifyTemp = 0;
        for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
            if (Materials.MATERIAL_STATUSES.get(i).getName().equals(liquidName)) {
                solidifyTemp = Materials.MATERIAL_STATUSES.get(i).getMeltingPoint();
                break;
            }
        }
        if (temperature <= solidifyTemp) {
            switch (type) {
                case "gear":
                    gearSolid();
                    break;
                case "rod":
                    rodSolid();
                    break;
                case "ingot":
                    ingotSolid();
                    break;
                case "plate":
                    plateSolid();
                    break;
            }
        }
    }

    public void normalTick() {
        updateTemperature();
        updateLiquid();
    }
}
