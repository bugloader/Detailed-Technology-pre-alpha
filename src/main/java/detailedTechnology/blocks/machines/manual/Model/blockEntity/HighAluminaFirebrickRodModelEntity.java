package detailedTechnology.blocks.machines.manual.Model.blockEntity;

import detailedTechnology.blocks.machines.manual.screenHandler.ModelScreenHandler;
import detailedTechnology.blocks.machines.manual.utilities.ModelUtilities;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.machine.Manual;
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

import java.util.Objects;

public class HighAluminaFirebrickRodModelEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public ModelUtilities utilities;
    private boolean needInitialise;

    public HighAluminaFirebrickRodModelEntity() {
        super(Manual.highAluminaFirebrickRodModelEntity);
        utilities = new ModelUtilities("rod");
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
        utilities.temperature = tag.getFloat("temperature");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, inventory);
        tag.putString("liquid name", utilities.liquidName);
        tag.putFloat("temperature", utilities.temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ModelScreenHandler(syncId, playerInventory, this, utilities.propertyDelegate);
    }

    private void checkBreak() {
        if (utilities.temperature > Materials.HIGH_ALUMINA_FIREBRICK.getMeltingPoint()) {
            Objects.requireNonNull(world).breakBlock(pos, false);
        }
    }

    @Override
    public void tick() {
        if(needInitialise){
            utilities.setInventory((Inventory) this);
            needInitialise=false;
        }
        utilities.normalTick();
        checkBreak();
    }
}