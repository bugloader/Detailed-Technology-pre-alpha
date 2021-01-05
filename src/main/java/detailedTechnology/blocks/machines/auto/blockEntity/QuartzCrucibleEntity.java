package detailedTechnology.blocks.machines.auto.blockEntity;

import detailedTechnology.blocks.machines.auto.screenHandler.CrucibleScreenHandler;
import detailedTechnology.blocks.machines.auto.utilities.CrucibleUtilities;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.machine.Auto;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

public class QuartzCrucibleEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private boolean needInitialise;
    public CrucibleUtilities utilities;

    public QuartzCrucibleEntity() {
        super(Auto.quartzCrucibleEntity);
        utilities = new CrucibleUtilities();
        needInitialise = true;
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
        utilities.liquidName = tag.getString("liquid name");
        utilities.liquidAmount = tag.getInt("liquid amount");
        utilities.temperature = tag.getFloat("temperature");
        needInitialise = true;
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putString("liquid name", utilities.liquidName);
        tag.putInt("liquid amount", utilities.liquidAmount);
        tag.putFloat("temperature", utilities.temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrucibleScreenHandler(syncId, playerInventory, this, utilities.propertyDelegate);
    }

    private void checkBreak() {
        if (utilities.temperature > Materials.QUARTZ.getMeltingPoint()) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    @Override
    public void tick() {
        if(needInitialise){
            utilities.initialise(pos,world,(Inventory) this);
            needInitialise = false;
        }
        utilities.normalTick();
        checkBreak();
    }
}
