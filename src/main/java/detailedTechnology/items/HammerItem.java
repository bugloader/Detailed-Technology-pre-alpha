package detailedTechnology.items;

import detailedTechnology.DetailedTechnology;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;


public class HammerItem extends ToolItem {
    private final int hammerLevel;
    private int usingTime=0;
    public HammerItem(ToolMaterial material, Settings settings){
        super(material, settings);
        hammerLevel = material.getMiningLevel()-1;
    }

    public int getHammerLevel() {
        return hammerLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                .equals("block.dt.bronze_anvil")) {
            usingTime++;
            if(usingTime<7) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_ANVIL_PLACE,0.5f,1.0f);
            }else{
                usingTime=0;
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.ENTITY_EXPERIENCE_ORB_PICKUP,1.0f,1.0f);
                DetailedTechnology.anvilRecipe.tryCraft("hammer",hammerLevel,
                        (Inventory) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())));
            }
            context.getPlayer().getStackInHand(context.getHand()).damage(1,context.getPlayer(),(playerEntity -> {
                playerEntity.sendToolBreakStatus(context.getHand());
            }));
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }

}
