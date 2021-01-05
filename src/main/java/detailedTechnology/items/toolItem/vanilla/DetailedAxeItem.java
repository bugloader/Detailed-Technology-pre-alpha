package detailedTechnology.items.toolItem.vanilla;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;

public class DetailedAxeItem extends AxeItem {
    public DetailedAxeItem(ToolMaterial material, int attackDamage, float attackSpeed, ItemGroup itemGroup) {
        super(material, attackDamage, attackSpeed, new FabricItemSettings().group(itemGroup));
    }
}