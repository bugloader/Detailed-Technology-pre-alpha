package detailedTechnology.items;

import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;


public class SawItem extends ToolItem {
    private int sawLevel;
    public SawItem(ToolMaterial material, Settings settings){
        super(material, settings);
        sawLevel = material.getMiningLevel()-1;
    }

    public int getHammerLevel() {
        return sawLevel;
    }
}
