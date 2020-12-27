package detailedTechnology.recipe;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.group.Machines;
import detailedTechnology.group.Materials;
import detailedTechnology.group.Tools;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class KilnRecipe {

    public static final ArrayList<ShapedRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<Integer> charcoalCosts = new ArrayList<>();

    public void addRecipe(int charcoalCost,Item[] ingredients,int[] ingredientsNum, Item product, int resultNum){
        charcoalCosts.add(charcoalCost);
        CONTENTS.add(new ShapedRecipe(ingredients,ingredientsNum,product,resultNum));
    }

    public KilnRecipe() {
        Item clayBall = Items.CLAY_BALL;
        Item air = Items.AIR;
        Item brick = Items.BRICK;
        Item rawFirebrickMixture = Materials.rawFirebrickMixture;
        Item fireBrick = Materials.fireBrick;

        addRecipe(1, new Item[]{clayBall,air,clayBall,clayBall,air,clayBall,clayBall,clayBall,clayBall},
                new int[]{1,0,1,1,0,1,1,1,1}, Materials.claySmallCrucible,8);

        addRecipe(1, new Item[]{clayBall,clayBall,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},brick,8);
        addRecipe(1, new Item[]{air,air,air,clayBall,clayBall,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,1,1,1,1,1},brick,8);

        addRecipe(1, new Item[]{clayBall,air,clayBall,air,clayBall,air,air,air,air},
                new int[]{1,0,1,0,1,0,0,0,0}, Tools.clayBucket,1);
        addRecipe(1, new Item[]{air,air,air,clayBall,air,clayBall,air,clayBall,air},
                new int[]{0,0,0,1,0,1,0,1,0}, Tools.clayBucket,1);

        addRecipe(4, new Item[]{air,air,air,brick,clayBall,brick,brick,brick,brick},
                new int[]{0,0,0,4,4,4,4,4,4},Machines.brickCrucible.asItem(),1);
        addRecipe(4, new Item[]{brick,clayBall,brick,brick,brick,brick,air,air,air},
                new int[]{4,4,4,4,4,4,0,0,0}, Machines.brickCrucible.asItem(),1);

        addRecipe(2, new Item[]{rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},fireBrick,8);
        addRecipe(2, new Item[]{air,air,air,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture},
                new int[]{0,0,0,1,1,1,1,1,1},fireBrick,8);
    }
}