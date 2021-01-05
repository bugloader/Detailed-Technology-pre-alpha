package detailedTechnology.recipe.general;

import detailedTechnology.recipe.auto.CokeOvenRecipe;
import detailedTechnology.recipe.auto.KilnRecipe;
import detailedTechnology.recipe.auto.PrimitiveBlaseFurnaceRecipe;
import detailedTechnology.recipe.auto.StoneMileRecipe;
import detailedTechnology.recipe.manual.AnvilRecipe;
import detailedTechnology.recipe.manual.CarpenterRecipe;

public class Recipes {
    public static StoneMileRecipe stoneMileRecipe;
    public static AnvilRecipe anvilRecipe;
    public static KilnRecipe kilnRecipe;
    public static CokeOvenRecipe cokeOvenRecipe;
    public static CarpenterRecipe carpenterRecipe;
    public static PrimitiveBlaseFurnaceRecipe primitiveBlaseFurnaceRecipe;

    public Recipes(){
        stoneMileRecipe = new StoneMileRecipe();
        anvilRecipe = new AnvilRecipe();
        kilnRecipe = new KilnRecipe();
        cokeOvenRecipe = new CokeOvenRecipe();
        carpenterRecipe = new CarpenterRecipe();
        primitiveBlaseFurnaceRecipe = new PrimitiveBlaseFurnaceRecipe();


    }
}
