package detailedTechnology.blockEntity;

import detailedTechnology.Interfaces.ImplementedInventory;
import detailedTechnology.blocks.CombustionChamber;
import detailedTechnology.group.Machines;
import detailedTechnology.group.Materials;
import detailedTechnology.gui.CombustionChamberScreenHandler;
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

public class FirebrickCombustionChamberEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public static final float charcoalUnitTemperature = 550;
    public static final float cokeUnitTemperature = 1000;
    public static final int charcoalUnitTime = 300;
    public static final int cokeUnitTime = 600;

    //1648

    private final float burningRate=1.75f;
    private int burningTime=0;
    private String fuelName=Items.AIR.getName().getString();
    public float temperature=20;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            switch (index){
                case 0: return burningTime;
                case 1: return (int)temperature;
                case 2: if(fuelName.equals(Items.CHARCOAL.getName().getString())){
                    return (int)(burningRate*charcoalUnitTemperature);
                }else{
                    return 20;
                }
                default: return 0;
            }
        }
        @Override
        public void set(int index, int value) {
            burningTime = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 3;
        }
    };

    public FirebrickCombustionChamberEntity(){
        super(Machines.firebrickCombustionChamberEntity);
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
        return new CombustionChamberScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    private void burnNext(Inventory inventory){
        fuelName = inventory.getStack(0).getName().getString();
        if(fuelName.equals(Items.CHARCOAL.getName().getString())){
            this.burningTime=(int)(charcoalUnitTime/burningRate);
            if(inventory.getStack(0).getCount()==1){
                inventory.setStack(0,Items.AIR.getDefaultStack());
            }else{
                inventory.getStack(0).setCount(inventory.getStack(0).getCount()-1);
            }
        }
    }

    private void getNextTemp(){
        if(fuelName.equals(Items.CHARCOAL.getName().getString())){
            temperature+=(charcoalUnitTemperature*burningRate-temperature)/50.0;
        }
        burningTime--;
    }

    private void checkBreak() {
        if (this.temperature > Materials.FIREBRICK.getMeltingPoint()) {
            assert world != null;
            world.breakBlock(pos, true);
        }
    }

    @Override
    public void tick() {
        if(burningTime==0){
            burnNext((Inventory)this);
        }
        if(burningTime>0){
            getNextTemp();
        }
        temperature-=(temperature-20)/5000.0;
        checkBreak();
    }
}
