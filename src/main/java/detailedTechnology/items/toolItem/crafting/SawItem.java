package detailedTechnology.items.toolItem.crafting;

import detailedTechnology.blocks.machines.manual.blockEntity.CarpenterWorkbenchEntity;
import detailedTechnology.group.machine.Manual;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;


public class SawItem extends ToolItem {
    private final int sawLevel;
    public SawItem(ToolMaterial material, ItemGroup itemGroup){
        super(material,  new FabricItemSettings().group(itemGroup));
        sawLevel = material.getMiningLevel()-1;
    }

    public int getSawLevel() {
        return sawLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                .equals(Manual.carpenterWorkbench.getName().getString())) {
            if(!((CarpenterWorkbenchEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                    .addWorkingTime("saw",sawLevel, context.getPlayer())) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_WOOD_BREAK,0.5f,1.0f);
            }
            Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).damage(1,
                    context.getPlayer(),(playerEntity -> {
                        playerEntity.sendToolBreakStatus(context.getHand());
                    }));
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }

}
