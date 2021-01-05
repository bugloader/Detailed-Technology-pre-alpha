package detailedTechnology.items.toolItem.crafting;

import detailedTechnology.blocks.machines.manual.blockEntity.BronzeAnvilEntity;
import detailedTechnology.blocks.machines.manual.blockEntity.SteelAnvilEntity;
import detailedTechnology.group.machine.Manual;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;
import java.util.Random;


public class RasperItem extends ToolItem {
    private final int rasperLevel;
    public RasperItem(ToolMaterial material, ItemGroup itemGroup){
        super(material, new FabricItemSettings().group(itemGroup));
        rasperLevel = material.getMiningLevel()-1;
    }

    public int getRasperLevel() {
        return rasperLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        String blockName = context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString();
        if(blockName.equals(Manual.bronzeAnvil.getName().getString())) {
            if(!((BronzeAnvilEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                    .addWorkingTime("rasper",rasperLevel,context.getPlayer())) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_ANVIL_BREAK,0.5f,1.0f);
            }
            Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).damage(1,
                    context.getPlayer(),(playerEntity -> {
                    playerEntity.sendToolBreakStatus(context.getHand());
                }));
            return ActionResult.SUCCESS;
        } else if(blockName.equals(Manual.steelAnvil.getName().getString())) {
            if(!((SteelAnvilEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                    .addWorkingTime("rasper",rasperLevel, context.getPlayer())) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_ANVIL_BREAK,0.5f,1.0f);
            }
            if(new Random().nextInt()%2==0) {
                Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).damage(1,
                        context.getPlayer(),(playerEntity -> {
                            playerEntity.sendToolBreakStatus(context.getHand());
                        }));
            }
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }
}
