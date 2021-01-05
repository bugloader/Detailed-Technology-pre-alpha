package detailedTechnology.recipe.auto;

import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.machine.Pipes;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.items.Tools;
import detailedTechnology.group.machine.Manual;
import detailedTechnology.recipe.general.ShapedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;

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
        Item rawBrick = Materials.rawBrick;
        Item brick = Items.BRICK;
        Item rawFirebrick = Materials.rawFirebrick;
        Item fireBrick = Materials.firebrick;
        Item rawHighAluminaFirebrick = Materials.rawHighAluminaFirebrick;
        Item highAluminaFirebrick = Materials.highAluminaFirebrick;
        Item rawHighAluminaMixture = Materials.rawHighAluminaFirebrickMixture;

        addRecipe(1, new Item[]{clayBall,air,clayBall,clayBall,air,clayBall,clayBall,clayBall,clayBall},
                new int[]{1,0,1,1,0,1,1,1,1}, Materials.claySmallCrucible,8);

        addRecipe(1, new Item[]{clayBall,air,clayBall,air,clayBall,air,air,air,air},
                new int[]{1,0,1,0,1,0,0,0,0}, Tools.clayBucket,1);
        addRecipe(1, new Item[]{air,air,air,clayBall,air,clayBall,air,clayBall,air},
                new int[]{0,0,0,1,0,1,0,1,0}, Tools.clayBucket,1);

        addRecipe(4, new Item[]{air,air,air,brick,clayBall,brick,brick,brick,brick},
                new int[]{0,0,0,4,4,4,4,4,4}, Auto.brickCrucible.asItem(),1);
        addRecipe(4, new Item[]{brick,clayBall,brick,brick,brick,brick,air,air,air},
                new int[]{4,4,4,4,4,4,0,0,0}, Auto.brickCrucible.asItem(),1);

        addRecipe(1, new Item[]{rawBrick,rawBrick,rawBrick,rawBrick,rawBrick,rawBrick,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},brick,6);
        addRecipe(1, new Item[]{air,air,air,rawBrick,rawBrick,rawBrick,rawBrick,rawBrick,rawBrick},
                new int[]{0,0,0,1,1,1,1,1,1},brick,6);
        addRecipe(2, new Item[]{rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},fireBrick,6);
        addRecipe(2, new Item[]{air,air,air,rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick,rawFirebrick},
                new int[]{0,0,0,1,1,1,1,1,1},fireBrick,6);
        addRecipe(2, new Item[]{rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},highAluminaFirebrick,6);
        addRecipe(2, new Item[]{air,air,air,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick,rawHighAluminaFirebrick},
                new int[]{0,0,0,1,1,1,1,1,1},highAluminaFirebrick,6);

        addRecipe(1,new Item[]{fireBrick,air,fireBrick,fireBrick,air,fireBrick,fireBrick,fireBrick,fireBrick},
                new int[]{1,0,1,1,0,1,1,1,1}, Pipes.firebrickBarrel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,brick,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Manual.clayIngotModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,brick,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Manual.clayIngotModel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,Ores.copperPlate,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Manual.clayPlateModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,Ores.copperPlate,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Manual.clayPlateModel.asItem(),1);

        addRecipe(1,new Item[]{Ores.copperRod,clayBall,Ores.copperRod,clayBall,clayBall,clayBall,air,air,air},
                new int[]{0,1,0,1,1,1,0,0,0},Manual.clayRodModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,Ores.copperRod,clayBall,Ores.copperRod,clayBall,clayBall,clayBall},
                new int[]{0,0,0,0,1,0,1,1,1},Manual.clayRodModel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,Ores.copperGear,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Manual.clayGearModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,Ores.copperGear,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Manual.clayGearModel.asItem(),1);

        addRecipe(2,new Item[]{rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,Manual.clayIngotModel.asItem(),rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture},
                new int[]{1,1,1,1,1,1,1,1,1},Manual.highAluminaFirebrickIngotModel.asItem(),1);
        addRecipe(2,new Item[]{rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,Manual.clayPlateModel.asItem(),rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture},
                new int[]{1,1,1,1,1,1,1,1,1},Manual.highAluminaFirebrickPlateModel.asItem(),1);
        addRecipe(2,new Item[]{rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,Manual.clayRodModel.asItem(),rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture},
                new int[]{1,1,1,1,1,1,1,1,1},Manual.highAluminaFirebrickRodModel.asItem(),1);
        addRecipe(2,new Item[]{rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,Manual.clayGearModel.asItem(),rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture,rawHighAluminaMixture},
                new int[]{1,1,1,1,1,1,1,1,1},Manual.highAluminaFirebrickGearModel.asItem(),1);
    }
}