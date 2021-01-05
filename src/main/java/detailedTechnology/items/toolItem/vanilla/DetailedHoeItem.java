package detailedTechnology.items.toolItem.vanilla;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;

public class DetailedHoeItem extends HoeItem {
    public DetailedHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, ItemGroup itemGroup) {
        super(material, attackDamage, attackSpeed, new FabricItemSettings().group(itemGroup));
    }
}