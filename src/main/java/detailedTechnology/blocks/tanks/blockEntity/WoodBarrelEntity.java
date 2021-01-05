package detailedTechnology.blocks.tanks.blockEntity;

import detailedTechnology.code.TankUtilities;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.machine.Pipes;
import detailedTechnology.group.items.Materials;
import detailedTechnology.blocks.tanks.screenHandler.BarrelScreenHandler;
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


public class WoodBarrelEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public static final int MaximumCapacitance = 16000;

    public String liquidName;
    public int liquidAmount;
    public TankUtilities tankUtilities;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if(index==0){
                for (int i = 0; i < Materials.LiquidList.size(); i++) {
                    if(liquidName.equals(Materials.LiquidList.get(i))) return i;
                }
                return -1;
            } else{
                return liquidAmount;
            }
        }
        @Override
        public void set(int index, int value) {
            liquidAmount = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 2;
        }
    };

    public WoodBarrelEntity(){
        super(Pipes.woodBarrelEntity);
        liquidName="air";
        liquidAmount=0;
        tankUtilities = new TankUtilities(MaximumCapacitance,liquidAmount,liquidName);
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
        liquidName = tag.getString("liquid name");
        liquidAmount = tag.getInt("liquid amount");
        tankUtilities = new TankUtilities(MaximumCapacitance,liquidAmount,liquidName);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        Inventories.fromTag(tag,inventory);
        tag.putString("liquid name",liquidName);
        tag.putInt("liquid amount",liquidAmount);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BarrelScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }


    private void checkBreak() {
        if (this.liquidName.equals("lava") ) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    public void loadTank(TankUtilities tankUtilties){
        this.tankUtilities = tankUtilties;
        this.liquidName = tankUtilties.liquidName;
        this.liquidAmount = tankUtilties.liquidAmount;
    }

    @Override
    public void tick() {
        tankUtilities.InventoryManipulate((Inventory)this);
        this.liquidName = tankUtilities.liquidName;
        this.liquidAmount = tankUtilities.liquidAmount;
        checkBreak();
    }
}
