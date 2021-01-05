package detailedTechnology.blocks.machines.manual.blockEntity;

import detailedTechnology.blocks.machines.manual.block.BrickModel;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.machine.Manual;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Hand;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class BrickModelEntity extends BlockEntity implements ImplementedInventory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public String content;
    public int waitingTime;
    public boolean ready;

    public BrickModelEntity() {
        super(Manual.brickModelEntity);
        content = "air";
        waitingTime = -1;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, inventory);
        content = tag.getString("content");
        waitingTime = tag.getInt("waiting time");
        ready = tag.getBoolean("ready");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putString("content", content);
        tag.putInt("waiting time", waitingTime);
        tag.putBoolean("ready",ready);
        return tag;
    }

    public void receiveMaterial(String content, PlayerEntity player, Hand hand){
        int itemCount = player.getStackInHand(hand).getCount();
        if(itemCount==1){
            player.setStackInHand(hand,Items.AIR.getDefaultStack());
        }else {
            player.getStackInHand(hand).setCount(itemCount-1);
        }
        this.content=content;
        waitingTime=0;
        BlockState currentState = Objects.requireNonNull(world).getBlockState(pos);
        Objects.requireNonNull(world).setBlockState(pos,currentState.with(BrickModel.CONTENT_STATUS,1));
    }

    private void checkUpdate(){
        if(waitingTime!=-1){
            waitingTime++;
            if(waitingTime==360){
                ready=true;
                waitingTime=-1;
                BlockState currentState = Objects.requireNonNull(world).getBlockState(pos);
                Objects.requireNonNull(world).setBlockState(pos,currentState.with(BrickModel.CONTENT_STATUS,2));
            }
        }
    }

    public void giveProduct(PlayerEntity playerEntity){
        switch (content){
            case "brick":
                playerEntity.giveItemStack(Materials.rawBrick.getDefaultStack());
                break;
            case "firebrick":
                playerEntity.giveItemStack(Materials.rawFirebrick.getDefaultStack());
                break;
            case "high_alumina_firebrick":
                playerEntity.giveItemStack(Materials.rawHighAluminaFirebrick.getDefaultStack());
                break;
        }
        content="air";
        BlockState currentState = Objects.requireNonNull(world).getBlockState(pos);
        Objects.requireNonNull(world).setBlockState(pos,currentState.with(BrickModel.CONTENT_STATUS,0));
    }

    @Override
    public void tick() {
        checkUpdate();
    }
}
