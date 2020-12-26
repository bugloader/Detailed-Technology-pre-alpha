package detailedTechnology.blockEntity;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.Interfaces.ImplementedInventory;
import detailedTechnology.gui.BronzeAnvilScreenHandler;
import detailedTechnology.recipe.AnvilRecipe;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class BronzeAnvilEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
    private int workingTime=0;
    private int currentCraftingId=-1;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            return index==0?workingTime:currentCraftingId;
        }

        @Override
        public void set(int index, int value) {
            workingTime = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 2 ;
        }
    };

    public BronzeAnvilEntity(){
        super(DetailedTechnology.bronzeAnvilEntity);
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
        return new BronzeAnvilScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    public boolean addWorkingTime(String tool,int level,PlayerEntity playerEntity){
        boolean result = false;
        if(currentCraftingId!=-1&&
        tool.equals(AnvilRecipe.TOOL_TYPE.get(currentCraftingId))&&level>=AnvilRecipe.TOOL_LEVEL.get(currentCraftingId)){
            result = workingTime==7;
            workingTime++;
        }
        if(result)Objects.requireNonNull(playerEntity).playSound(SoundEvents.BLOCK_ANVIL_PLACE,0.5f,1.0f);
        return result;
    }

    @Override
    public void tick() {
        int temp = AnvilRecipe.getRecipeId((Inventory) this);
        if(temp!=currentCraftingId){
            workingTime=0;
        }
        currentCraftingId = temp;
        if(workingTime==8){
            AnvilRecipe.tryCraft((Inventory)this);
            workingTime=0;
        }
    }
}
