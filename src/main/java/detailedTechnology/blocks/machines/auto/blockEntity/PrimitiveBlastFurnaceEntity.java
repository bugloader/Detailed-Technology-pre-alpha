package detailedTechnology.blocks.machines.auto.blockEntity;

import detailedTechnology.blocks.machines.auto.screenHandler.PrimitiveBlastFurnaceScreenHandler;
import detailedTechnology.blocks.machines.auto.structure.PrimitiveBlastFurnaceStructure;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.items.Materials;
import detailedTechnology.recipe.auto.PrimitiveBlaseFurnaceRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class PrimitiveBlastFurnaceEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(4, ItemStack.EMPTY);
    public static final float charcoalUnitTemperature = 550;
    public static final float cokeUnitTemperature = 1000;
    public static final int charcoalUnitTime = 3600;
    public static final int cokeUnitTime = 2000;
    private final float burningRate = 1.75f;
    private int burningTime;
    private String fuelName;
    public float temperature;
    int checkTime = 0;
    boolean needInitialise = true;
    boolean validness;
    private int workingTime;
    private int currentCraftingId;
    public PrimitiveBlastFurnaceStructure primitiveBlastFurnaceStructure;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return burningTime;
            } else if (index == 1) {
                return (int) temperature;
            } else if (index == 2) {
                return workingTime;
            } else if (index == 3) {
                return currentCraftingId;
            } else {
                return validness ? 1 : 0;
            }
        }

        @Override
        public void set(int index, int value) {
            burningTime = value;
        }

        @Override
        public int size() {
            return 5;
        }
    };

    public PrimitiveBlastFurnaceEntity() {
        super(Auto.primitiveBlastFurnaceEntity);
        fuelName = Items.AIR.getName().getString();
        burningTime = 0;
        workingTime = 0;
        currentCraftingId = -1;
        temperature = 20;
        validness = false;
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
        Inventories.fromTag(tag, inventory);
        burningTime = tag.getInt("burning time");
        fuelName = tag.getString("fuel name");
        workingTime = tag.getInt("working time");
        validness = tag.getBoolean("validness");
        temperature = tag.getInt("temperature");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("burning time", burningTime);
        tag.putString("fuel name", fuelName);
        tag.putInt("working time", workingTime);
        tag.putBoolean("validness", validness);
        tag.putInt("temperature", (int) temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PrimitiveBlastFurnaceScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    public void getCurrentCraftingId(Inventory inventory) {
        if (inventory.getStack(0).getName().getString().equals(Items.AIR.getName().getString())) {
            currentCraftingId = -1;
        } else for (int i = 0; i < PrimitiveBlaseFurnaceRecipe.products.size(); i++) {
            if (inventory.getStack(0).getName().getString().equals(
                    PrimitiveBlaseFurnaceRecipe.ingredients.get(i).getName().getString())) {
                currentCraftingId = i;
                break;
            }
        }
    }

    private void burnNext(Inventory inventory) {
        fuelName = inventory.getStack(1).getName().getString();
        if (fuelName.equals(Items.CHARCOAL.getName().getString())) {
            this.burningTime = (int) (charcoalUnitTime / burningRate*2);
            if (inventory.getStack(1).getCount() == 1) {
                inventory.setStack(1, Items.AIR.getDefaultStack());
            } else {
                inventory.getStack(1).setCount(inventory.getStack(1).getCount() - 1);
            }
        } else if (fuelName.equals(Materials.coke.getName().getString())) {
            this.burningTime = (int) (cokeUnitTime / burningRate*2);
            if (inventory.getStack(1).getCount() == 1) {
                inventory.setStack(1, Items.AIR.getDefaultStack());
            } else {
                inventory.getStack(1).setCount(inventory.getStack(1).getCount() - 1);
            }
        }
    }

    private void getNextTemp() {
        if (fuelName.equals(Items.CHARCOAL.getName().getString())) {
            temperature += (charcoalUnitTemperature * burningRate - temperature) / 400.0;
        } else if (fuelName.equals(Materials.coke.getName().getString())) {
            temperature += (cokeUnitTemperature * burningRate - temperature) / 400.0;
        }
        burningTime--;
    }

    private void checkCrafting(Inventory inventory) {
        int tempCid = currentCraftingId;
        getCurrentCraftingId(inventory);
        if (tempCid != currentCraftingId) {
            workingTime = 0;
        } else if (currentCraftingId != -1 && validness) {
            if (temperature >= PrimitiveBlaseFurnaceRecipe.temperatureRequires.get(currentCraftingId)) {
                if (workingTime >= PrimitiveBlaseFurnaceRecipe.timeRequires.get(currentCraftingId)) {
                    if(PrimitiveBlaseFurnaceRecipe.tryCraft(inventory)){
                        workingTime=0;
                    }
                } else {
                    workingTime++;
                }
            } else{
                workingTime=0;
            }
        }else{
            workingTime=0;
        }
    }

    private void checkValid() {
        if (checkTime == 0) {
            if (needInitialise) {
                assert world != null;
                primitiveBlastFurnaceStructure =
                        new PrimitiveBlastFurnaceStructure(world, pos, world.getBlockState(pos).get(FACING));
                needInitialise = false;
            }
            validness = primitiveBlastFurnaceStructure.isValid();
        }
        checkTime = (checkTime + 1) % 100;
    }

    @Override
    public void tick() {
        Inventory inventory = (Inventory) this;
        checkValid();
        if (validness) {
            if(burningTime==0){
                burnNext((Inventory)this);
            }
            if(burningTime>0){
                getNextTemp();
                temperature-=(temperature-20)/50000.0;
            } else{
                temperature-=(temperature-20)/5000.0;
            }
            checkCrafting(inventory);
        }
    }
}
