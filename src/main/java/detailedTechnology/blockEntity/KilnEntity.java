package detailedTechnology.blockEntity;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.Interfaces.ImplementedInventory;
import detailedTechnology.gui.KilnScreenHandler;
import detailedTechnology.recipe.KilnRecipe;
import detailedTechnology.recipe.ShapedRecipe;
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
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class KilnEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);
    private int currentCraftingID;
    private int workingTime;


    public KilnEntity(){
        super(DetailedTechnology.kilnEntity);
        currentCraftingID=-1;
        workingTime = 0;
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
    public void fromTag(BlockState state,CompoundTag tag) {
        super.fromTag(state,tag);
        Inventories.fromTag(tag,inventory);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KilnScreenHandler(syncId, playerInventory, this);
    }

    public boolean tryCraft(Inventory inventory) {
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapedRecipe content;
        String fuelName = inventory.getStack(9).getName().getString();
        int fuelCount = inventory.getStack(9).getCount();
        String resultItemName = inventory.getStack(10).getName().getString();
        int resultItemNum = inventory.getStack(10).getCount();
        if(currentCraftingID==-1) {
            for(int i = 0; i< KilnRecipe.CONTENTS.size(); i++) {
                content = KilnRecipe.CONTENTS.get(i);
                for (int j = 0; j < 9; j++) {
                    if(!inventory.getStack(j).getName().getString().equals(content.ingredients[j])||
                            inventory.getStack(j).getCount()<content.ingredientsNum[j]) {
                        break;
                    }if(j==8&&((resultItemName.equals(content.result)&&resultItemNum+content.resultNum<=64)||
                            resultItemName.equals("Air"))){
                        if(workingTime==0){
                            if(fuelName.equals("Charcoal")&&fuelCount>=KilnRecipe.charcoalCosts.get(i)){
                                if(fuelCount==KilnRecipe.charcoalCosts.get(i)){
                                    inventory.setStack(9,Items.AIR.getDefaultStack());
                                }else{
                                    inventory.getStack(9).setCount(fuelCount-KilnRecipe.charcoalCosts.get(i));
                                }
                                currentCraftingID = i;
                                for (int k = 0; k < 9; k++) {
                                    if(content.ingredientsNum[k]==inventory.getStack(k).getCount()){
                                        inventory.setStack(k,Items.AIR.getDefaultStack());
                                    }else{
                                        inventory.getStack(k).setCount(inventory.getStack(k).getCount()-content.ingredientsNum[k]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(workingTime<400){
                workingTime++;
            }else if(resultItemName.equals("Air")){
                inventory.setStack(10,KilnRecipe.PRODUCTS.get(currentCraftingID).getDefaultStack());
                inventory.getStack(10).setCount(KilnRecipe.CONTENTS.get(currentCraftingID).resultNum);
                workingTime=0;
                currentCraftingID=-1;
            }else if(resultItemName.equals(KilnRecipe.CONTENTS.get(currentCraftingID).result)&&
                resultItemNum+KilnRecipe.CONTENTS.get(currentCraftingID).resultNum<=64){
                inventory.getStack(10).setCount(resultItemNum+KilnRecipe.CONTENTS.get(currentCraftingID).resultNum);
                workingTime=0;
                currentCraftingID=-1;
            }
        return false;
    }

    @Override
    public void tick()
    {
        tryCraft((Inventory) this);
    }



}
