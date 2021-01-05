package detailedTechnology.recipe.auto;

import detailedTechnology.group.items.Materials;
import detailedTechnology.group.currentdone.Ores;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;
import java.util.Random;

public class PrimitiveBlaseFurnaceRecipe {
    public static final ArrayList<Item> ingredients = new ArrayList<>();
    public static final ArrayList<Integer> ingredientNums = new ArrayList<>();
    public static final ArrayList<Item> products = new ArrayList<>();
    public static final ArrayList<Integer> productNums = new ArrayList<>();
    public static final ArrayList<Integer> temperatureRequires = new ArrayList<>();
    public static final ArrayList<Integer> timeRequires = new ArrayList<>();

    public static void addRecipe(Item ingredient,int ingredientNum,Item product, int productNum,
                                 int temperatureRequire,int timeRequire){
        ingredients.add(ingredient);
        ingredientNums.add(ingredientNum);
        productNums.add(productNum);
        products.add(product);
        temperatureRequires.add(temperatureRequire);
        timeRequires.add(timeRequire);
    }

    public static boolean tryCraft(Inventory inventory){
        ItemStack inStack = inventory.getStack(0);
        ItemStack crucibleStack = inventory.getStack(2);
        ItemStack outStack = inventory.getStack(3);
        for (int i = 0; i < ingredients.size(); i++) {
            if(inStack.getName().getString().equals(ingredients.get(i).getName().getString())&&
                    inStack.getCount()>=ingredientNums.get(i)&&
                    (outStack.getName().getString().equals(Items.AIR.getName().getString())||
                    (outStack.getName().getString().equals(products.get(i).getName().getString())&&
                            outStack.getCount()+productNums.get(i)<=64))&&
                    crucibleStack.getName().getString().equals(Materials.claySmallCrucible.getName().getString())){
                if(inStack.getCount()==ingredientNums.get(i)){
                    inventory.setStack(0,Items.AIR.getDefaultStack());
                }else {
                    inStack.setCount(inStack.getCount()-ingredientNums.get(i));
                }
                if(new Random().nextInt()%2==0){
                    if(crucibleStack.getCount()==1){
                        inventory.setStack(2,Items.AIR.getDefaultStack());
                    }else{
                        crucibleStack.setCount(crucibleStack.getCount()-1);
                    }
                }
                if(outStack.getName().getString().equals(Items.AIR.getName().getString())){
                    inventory.setStack(3,products.get(i).getDefaultStack());
                    inventory.getStack(3).setCount(productNums.get(i));
                }else{
                    outStack.setCount(outStack.getCount()+productNums.get(i));
                }
                return true;
            }
        }
        return false;
    }


    public PrimitiveBlaseFurnaceRecipe(){
        addRecipe(Ores.ironDust,1,Items.IRON_INGOT,1,1400,2400);
        addRecipe(Items.IRON_INGOT,1,Ores.steelIngot,1,1600,4800);
    }

}
