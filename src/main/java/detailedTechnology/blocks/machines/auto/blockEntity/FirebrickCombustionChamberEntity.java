package detailedTechnology.blocks.machines.auto.blockEntity;

import detailedTechnology.blocks.machines.auto.utilities.CombustionChamberUtilities;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.items.Materials;
import detailedTechnology.blocks.machines.auto.screenHandler.CombustionChamberScreenHandler;
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

public class FirebrickCombustionChamberEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    public final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    public static final float breakTemperature = Materials.FIREBRICK.getMeltingPoint();
    public CombustionChamberUtilities utilities;

    public FirebrickCombustionChamberEntity(){
        super(Auto.firebrickCombustionChamberEntity);
        utilities = new CombustionChamberUtilities(1.75f,(Inventory)this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return this.inventory;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state,tag);
        Inventories.fromTag(tag, this.inventory);
        utilities.burningTime = tag.getInt("burning time");
        utilities.fuelName = tag.getString("fuel name");
        utilities.temperature = tag.getFloat("temperature");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("burning time", utilities.burningTime);
        tag.putString("fuel name", utilities.fuelName);
        tag.putFloat("temperature", utilities.temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CombustionChamberScreenHandler(syncId, playerInventory, this, utilities.propertyDelegate);
    }

    private void checkBreak() {
        if (utilities.temperature > breakTemperature) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    @Override
    public void tick() {
        utilities.normalTick();
        checkBreak();
    }
}