package detailedTechnology.blocks;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;

public class CharcoalHeap extends Block {
    public CharcoalHeap()
    {
        super(FabricBlockSettings.of(Material.SOIL, MaterialColor.STONE)
                .strength(1f,1f)
                .breakByTool(FabricToolTags.SHOVELS, 0));
    }

}
