package detailedTechnology.blocks.machines.manual.blockEntity;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.blocks.machines.manual.screenHandler.FireStarterBlockScreenHandler;
import detailedTechnology.group.machine.Manual;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;

public class FireStarterBlockEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public FireStarterBlockEntity(){
        super(Manual.fireStarterBlockEntity);
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
        return new FireStarterBlockScreenHandler(syncId, playerInventory, this);
    }



}
