package detailedTechnology.items;

import detailedTechnology.DetailedTechnology;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.FlintAndSteelItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;

public class BasicFireStarter extends FlintAndSteelItem {


    public BasicFireStarter() {
        super(new FabricItemSettings().group(DetailedTechnology.ITEM_GROUP).maxCount(16));

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
            else if(RANDOM.nextInt()%4!=0)
            {
                super.useOnBlock(context);
            }
            if(RANDOM.nextInt()%8==0)
            {
                int count = inventory.getStack(slot).getCount();
                if(count==1)
                {
                    inventory.setStack(slot,new ItemStack(Items.AIR));
                }
                else
                {
                    System.out.println(count);
                    inventory.getStack(slot).setCount(count-1);
                }
            }
        }
        return ActionResult.PASS;
    }
}