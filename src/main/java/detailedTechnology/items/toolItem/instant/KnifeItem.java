package detailedTechnology.items.toolItem.instant;

import detailedTechnology.blocks.machines.manual.blockEntity.CarpenterWorkbenchEntity;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.machine.Manual;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;

import java.util.Objects;

public class KnifeItem extends ToolItem {

    public KnifeItem(ToolMaterial toolMaterial,ItemGroup itemGroup) {
        super(toolMaterial, new FabricItemSettings().group(itemGroup));
    }



    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getPlayer()!=null)
        {
            MutableText blockName = context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName();
            Inventory inventory = context.getPlayer().inventory;
            int slot = context.getPlayer().inventory.selectedSlot;
            int number = inventory.getStack(slot).getCount();
            boolean change = (RANDOM.nextInt()%3)==0;
            if(blockName.equals(Blocks.SPRUCE_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_SPRUCE_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.SPRUCE_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_SPRUCE_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.BIRCH_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_BIRCH_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.ACACIA_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_ACACIA_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.DARK_OAK_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_DARK_OAK_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.JUNGLE_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_JUNGLE_LOG.getDefaultState());
                }
            }else if(blockName.equals(Blocks.OAK_LOG.getName())){
                Objects.requireNonNull(context.getPlayer()).giveItemStack(Materials.tinder.getDefaultStack());
                if(change){
                    context.getWorld().setBlockState(context.getBlockPos(),Blocks.STRIPPED_OAK_LOG.getDefaultState());
                }
            }else if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                    .equals(Manual.carpenterWorkbench.getName().getString())) {
                if(!((CarpenterWorkbenchEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                        .addWorkingTime("knife",0, context.getPlayer())) {
                    Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_WOOD_BREAK,0.5f,1.0f);
                }
            }
            Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).damage(1,
                    context.getPlayer(),(playerEntity -> {
                        playerEntity.sendToolBreakStatus(context.getHand());
                    }));
        }
        return ActionResult.PASS;
    }
}