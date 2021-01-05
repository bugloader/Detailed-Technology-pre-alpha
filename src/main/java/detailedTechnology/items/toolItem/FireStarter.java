package detailedTechnology.items.toolItem;

import detailedTechnology.DetailedTechnology;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;

public class FireStarter extends FlintAndSteelItem {

    int level;

    public FireStarter(ItemGroup itemGroup, int level) {
        super(new FabricItemSettings().group(itemGroup).maxCount(1));
        this.level = level;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getPlayer()!=null)
        {
            MutableText blockName = context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName();
            Inventory inventory = context.getPlayer().inventory;
            int slot = context.getPlayer().inventory.selectedSlot;
            int number = inventory.getStack(slot).getCount();
            if(blockName.equals(Blocks.STRIPPED_ACACIA_LOG.getName())||
                    blockName.equals(Blocks.STRIPPED_BIRCH_LOG.getName())||
                    blockName.equals(Blocks.STRIPPED_DARK_OAK_LOG.getName())||
                    blockName.equals(Blocks.STRIPPED_OAK_LOG.getName())||
                    blockName.equals(Blocks.STRIPPED_JUNGLE_LOG.getName())||
                    blockName.equals(Blocks.STRIPPED_SPRUCE_LOG.getName())) {
                context.getWorld().setBlockState(context.getBlockPos(),
                        DetailedTechnology.charcoalHeap.getDefaultState());
            }
            else if(RANDOM.nextInt()%150<=20*(level+4)) {
                super.useOnBlock(context);
            }
            if(RANDOM.nextInt()%150>20*(level+4)) {
                inventory.setStack(slot,new ItemStack(Items.AIR));
            }
        }
        return ActionResult.PASS;
    }
}