package detailedTechnology.recipe;

public class ShapedRecipe {
    public String[] ingredients;
    public int[] ingredientsNum;
    public String result;
    public int resultNum;

    public ShapedRecipe(String[] ingredients, int[] ingredientsNum, String result, int resultNum) {
        this.ingredients = ingredients;
        this.ingredientsNum = ingredientsNum;
        this.result = result;
        this.resultNum = resultNum;
    }
}
