package detailedTechnology;

import detailedTechnology.blockEntity.KilnEntity;
import detailedTechnology.blockEntity.StoneMileEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public class Registration {

    public static String MOD_ID;

    public Registration(String MOD_ID) {
        Registration.MOD_ID = MOD_ID;
    }

    public static void item(String name, Item item){
        Registry.register(Registry.ITEM, new Identifier(MOD_ID,name),item);
    }

    public static void block(String name, Block block){
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,name),block);
    }

    public static void blockWithItem(String name, Block block, ItemGroup itemGroup){
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,name),block);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, name),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
    }

}
