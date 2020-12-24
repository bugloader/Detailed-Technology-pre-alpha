package detailedTechnology.items;

import detailedTechnology.blocks.Ores;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class BronzeMaterial implements ToolMaterial {

    public static final BronzeMaterial INSTANCE = new BronzeMaterial();

    @Override
    public int getDurability()
    {
        return 128;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }
    @Override
    public float getAttackDamage() {
        return 1.0F;
    }
    @Override
    public int getMiningLevel() {
        return 2;
    }
    @Override
    public int getEnchantability() {
        return 8;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Ores.bronzeIngot);
    }
}
