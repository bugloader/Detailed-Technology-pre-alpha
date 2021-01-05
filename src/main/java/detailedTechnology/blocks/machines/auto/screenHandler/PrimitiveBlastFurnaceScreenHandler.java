package detailedTechnology.blocks.machines.auto.screenHandler;

import detailedTechnology.group.machine.Auto;
import detailedTechnology.recipe.auto.PrimitiveBlaseFurnaceRecipe;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;


public class PrimitiveBlastFurnaceScreenHandler extends ScreenHandler {
    private final Inventory inventory;
    private final PropertyDelegate propertyDelegate;

    public PrimitiveBlastFurnaceScreenHandler(int syncId, PlayerInventory playerInventory) {
        this(syncId, playerInventory, new SimpleInventory(4),new ArrayPropertyDelegate(5));
    }

    public PrimitiveBlastFurnaceScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Auto.primitiveBlastFurnaceScreenHandler, syncId);
        checkSize(inventory, 2);
        this.inventory = inventory;
        this.propertyDelegate = propertyDelegate;
        inventory.onOpen(playerInventory.player);
        this.addProperties(this.propertyDelegate);

        int m;
        int l;

        this.addSlot(new Slot(inventory,0,44,17));
        this.addSlot(new Slot(inventory,1,44,51));
        this.addSlot(new Slot(inventory,3,116,17));
        this.addSlot(new Slot(inventory,2,116,51));

        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(playerInventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(playerInventory, m, 8 + m * 18, 142));
        }
    }


    public int getBurningTime(){
        return propertyDelegate.get(0);
    }

    public int getTemperature(){
        return propertyDelegate.get(1);
    }

    public int getRequiredTemperature(){
        int i = propertyDelegate.get(3);
        if(i==-1) {
            return 0;
        }
        return PrimitiveBlaseFurnaceRecipe.temperatureRequires.get(i);
    }

    public int getWorkingTime(){
        return propertyDelegate.get(2);
    }

    public int getRequiredTime(){
        int i = propertyDelegate.get(3);
        if(i==-1) {
            return 0;
        }
        return PrimitiveBlaseFurnaceRecipe.timeRequires.get(i);
    }

    public String getName(){
        int i = propertyDelegate.get(3);
        if(i==-1) {
            return "none";
        }
        return PrimitiveBlaseFurnaceRecipe.products.get(i).getName().getString();
    }

    public boolean getValid(){
        return propertyDelegate.get(4)==1;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return this.inventory.canPlayerUse(player);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int invSlot) {    // Shift + Player Inv Slot
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(invSlot);
        if (slot != null && slot.hasStack()) {
            ItemStack originalStack = slot.getStack();
            newStack = originalStack.copy();
            if (invSlot < 1) {
                if (!this.insertItem(originalStack, this.inventory.size(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.insertItem(originalStack, 0, 1, false)) {
                return ItemStack.EMPTY;
            }
            if (originalStack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }
        return newStack;
    }
}