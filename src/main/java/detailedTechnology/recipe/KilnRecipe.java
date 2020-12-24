package detailedTechnology.recipe;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blockEntity.KilnEntity;
import detailedTechnology.blocks.Ores;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class KilnRecipe {

    public static final ArrayList<ShapedRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<Integer> charcoalCosts = new ArrayList<>();
    public static final ArrayList<Item> PRODUCTS = new ArrayList<>();

    public void addRecipe(int charcoalCost,String[] ingredients, int[] ingredientsNum, String result, int resultNum, Item productStack) {
        charcoalCosts.add(charcoalCost);
        CONTENTS.add(new ShapedRecipe(ingredients,ingredientsNum,result,resultNum));
        PRODUCTS.add(productStack);
    }

    public KilnRecipe() {
        addRecipe(1, new String[]{"Clay Ball","Air","Clay Ball","Clay Ball","Air","Clay Ball","Clay Ball","Clay Ball","Clay Ball"},
                new int[]{1,0,1,1,0,1,1,1,1},"item.dt.clay_small_crucible",8,DetailedTechnology.claySmallCrucible);
    }
}