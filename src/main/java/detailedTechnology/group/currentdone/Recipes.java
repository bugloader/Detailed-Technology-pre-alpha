package detailedTechnology.group.currentdone;

import detailedTechnology.recipe.AnvilRecipe;
import detailedTechnology.recipe.CokeOvenRecipe;
import detailedTechnology.recipe.KilnRecipe;
import detailedTechnology.recipe.StoneMileRecipe;

public class Recipes {
    public static StoneMileRecipe stoneMileRecipe;
    public static AnvilRecipe anvilRecipe;
    public static KilnRecipe kilnRecipe;
    public static CokeOvenRecipe cokeOvenRecipe;
    public Recipes(){
        stoneMileRecipe = new StoneMileRecipe();
        anvilRecipe = new AnvilRecipe();
        kilnRecipe = new KilnRecipe();
        cokeOvenRecipe = new CokeOvenRecipe();
    }
}
