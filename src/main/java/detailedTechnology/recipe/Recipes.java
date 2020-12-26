package detailedTechnology.recipe;

public class Recipes {
    public static StoneMileRecipe stoneMileRecipe;
    public static AnvilRecipe anvilRecipe;
    public static KilnRecipe kilnRecipe;
    public Recipes(){
        stoneMileRecipe = new StoneMileRecipe();
        anvilRecipe = new AnvilRecipe();
        kilnRecipe = new KilnRecipe();
    }
}
