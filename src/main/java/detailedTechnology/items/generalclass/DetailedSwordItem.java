package detailedTechnology.items.generalclass;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolMaterial;

public class DetailedSwordItem extends HoeItem {
    public DetailedSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, ItemGroup itemGroup) {
        super(material, attackDamage, attackSpeed, new FabricItemSettings().group(itemGroup));
    }
}