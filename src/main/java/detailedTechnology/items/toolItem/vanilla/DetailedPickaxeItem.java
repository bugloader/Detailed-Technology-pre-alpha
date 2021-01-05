package detailedTechnology.items.toolItem.vanilla;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class DetailedPickaxeItem extends PickaxeItem {
    public DetailedPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, ItemGroup itemGroup) {
        super(material, attackDamage, attackSpeed, new FabricItemSettings().group(itemGroup));
    }
}