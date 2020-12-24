package detailedTechnology.items;

import detailedTechnology.DetailedTechnology;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;


public class RasperItem extends ToolItem {
    private int rasperLevel;
    private int usingTime=0;
    public RasperItem(ToolMaterial material, Settings settings){
        super(material, settings);
        rasperLevel = material.getMiningLevel()-1;
    }

    public int getRasperLevel() {
        return rasperLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                .equals("block.dt.bronze_anvil")) {
            usingTime++;
            if(usingTime<7) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_ANVIL_BREAK,0.5f,1.0f);
            }else{
                usingTime=0;
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,1.0f,1.0f);
                DetailedTechnology.anvilRecipe.tryCraft("rasper",rasperLevel,
                        (Inventory) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())));
            }
            if(usingTime%2==0)
            {
                context.getPlayer().getStackInHand(context.getHand()).damage(1,context.getPlayer(),(playerEntity -> {
                    playerEntity.sendToolBreakStatus(context.getHand());
                }));
            }
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }
}
