package detailedTechnology.recipe.manual;

import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.machine.Pipes;
import detailedTechnology.group.items.Armors;
import detailedTechnology.group.items.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.items.Tools;
import detailedTechnology.recipe.general.ShapelessRecipe;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class AnvilRecipe {

    public static final ArrayList<ShapelessRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<String> TOOL_TYPE = new ArrayList<>();
    public static final ArrayList<Integer> TOOL_LEVEL = new ArrayList<>();

    public void addRecipe(String toolName,int toolLevel,Item[] ingredients, int[] ingredientsNum, Item result, int resultNum) {
        TOOL_TYPE.add(toolName);
        TOOL_LEVEL.add(toolLevel);
        CONTENTS.add(new ShapelessRecipe(ingredients,ingredientsNum,result,resultNum));
    }

    public static boolean[] newBooleanArrayWithFalse(int length)
    {
        boolean[] result = new boolean[length];
        for(int i=0;i<length;i++) result[i]=false;
        return result;
    }

    public static int getRecipeId(Inventory inventory){
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapelessRecipe content;
        boolean[] included1,included2;
        int checkNum;
        String resultItemName = inventory.getStack(9).getName().getString();
        int resultItemNum = inventory.getStack(9).getCount();

        for(int i=0;i<9;i++) {
            if(!inventory.getStack(i).getName().equals(Blocks.AIR.getName())){
                itemNames[stackCount] = inventory.getStack(i).getName().getString();
                itemNums[stackCount] = inventory.getStack(i).getCount();
                stackCount++;
            }
        }

        for (int i=0;i<CONTENTS.size();i++) {
            content = CONTENTS.get(i);
            if (content.ingredients.length == stackCount &&
                    (resultItemName.equals(content.result.getName().getString())||resultItemName.equals(Items.AIR.getName().getString())) &&
                    content.resultNum + resultItemNum <= content.result.getMaxCount()) {
                included1 = newBooleanArrayWithFalse(9);
                included2 = newBooleanArrayWithFalse(9);
                checkNum = 0;
                for (int j = 0; j < stackCount; j++) {
                    for(int k = 0; k < stackCount; k++) {
                        if(!included1[j]&&!included2[k]&&
                                itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                itemNums[j]>=content.ingredientsNum[k]) {
                            included1[j]=true;
                            included2[k]=true;
                            checkNum++;
                        }
                    }
                }
                if(checkNum==stackCount) return i;
            }
        }
        return -1;
    }

    public static void tryCraft(Inventory inventory) {
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapelessRecipe content;
        boolean[] included1,included2;
        int checkNum;
        String resultItemName = inventory.getStack(9).getName().getString();
        int resultItemNum = inventory.getStack(9).getCount();

        for(int i=0;i<9;i++) {
            if(!inventory.getStack(i).getName().equals(Blocks.AIR.getName())){
                itemNames[stackCount] = inventory.getStack(i).getName().getString();
                itemNums[stackCount] = inventory.getStack(i).getCount();
                stackCount++;
            }
        }

        for (int i=0;i<CONTENTS.size();i++) {
            content = CONTENTS.get(i);
            if (content.ingredients.length == stackCount &&
                    (resultItemName.equals(content.result.getName().getString())||resultItemName.equals(Items.AIR.getName().getString())) &&
                    content.resultNum + resultItemNum <= content.result.getMaxCount()) {
                included1 = newBooleanArrayWithFalse(9);
                included2 = newBooleanArrayWithFalse(9);
                checkNum = 0;
                for (int j = 0; j < stackCount; j++) {
                    for(int k = 0; k < stackCount; k++) {
                        if(!included1[j]&&!included2[k]&&
                                itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                itemNums[j]>=content.ingredientsNum[k]) {
                            included1[j]=true;
                            included2[k]=true;
                            checkNum++;
                        }
                    }
                }
                if(checkNum==stackCount) {
                    included1 = newBooleanArrayWithFalse(9);
                    included2 = newBooleanArrayWithFalse(9);
                    for (int j = 0; j < stackCount; j++) {
                        for(int k = 0; k < stackCount; k++) {
                            if(!included1[j]&&!included2[k]&&
                                    itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                    itemNums[j]>=content.ingredientsNum[k]) {
                                included1[k]=true;
                                included2[k]=true;
                                itemNums[j]-=content.ingredientsNum[k];
                            }
                        }
                    }
                    stackCount=0;
                    for(int j=0;j<9;j++) {
                        if(!inventory.getStack(j).getName().equals(Blocks.AIR.getName())){
                            if(itemNums[stackCount]==0) {
                                inventory.setStack(j, Items.AIR.getDefaultStack());
                            }else{
                                inventory.getStack(j).setCount(itemNums[stackCount]);
                            }
                            stackCount++;
                        }
                    }
                    if(inventory.getStack(9).getName().equals(Blocks.AIR.getName())) {
                        inventory.setStack(9,content.result.getDefaultStack());
                        inventory.getStack(9).setCount(content.resultNum);
                    }else{
                        inventory.getStack(9).setCount(inventory.getStack(9).getCount()+content.resultNum);
                    }
                    return;
                }
            }
        }
    }

    public AnvilRecipe() {
        Item copperIngot = Ores.copperIngot;
        Item copperPlate = Ores.copperPlate;
        Item copperRod = Ores.copperRod;
        Item tinIngot = Ores.tinIngot;
        Item tinPlate = Ores.tinPlate;
        Item tinRod = Ores.tinRod;
        Item bronzeIngot = Ores.bronzeIngot;
        Item bronzePlate = Ores.bronzePlate;
        Item bronzeRod = Ores.bronzeRod;
        Item ironIngot = Items.IRON_INGOT;
        Item ironPlate = Ores.ironPlate;
        Item ironRod = Ores.ironRod;
        Item steelIngot = Ores.steelIngot;
        Item steelPlate = Ores.steelPlate;
        Item steelRod = Ores.steelRod;
        Item stick = Items.STICK;
        Item smallBronzePlate = Materials.smallBronzePlate;
        Item bronzeHalfPipe = Materials.bronzeHalfPipe;

        addRecipe("hammer",1,new Item[]{copperIngot, copperIngot},
                new int[]{1, 1}, copperPlate,1);
        addRecipe("hammer",1,new Item[]{tinIngot, tinIngot},
                new int[]{1, 1}, tinPlate,1);
        addRecipe("hammer",1,new Item[]{bronzeIngot, bronzeIngot},
                new int[]{1, 1}, bronzePlate,1);

        addRecipe("hammer",1,new Item[]{bronzeRod, bronzeRod,bronzeRod, bronzeRod},
                new int[]{3,3,3,3}, Materials.bronzeRodFrame,1);
        addRecipe("hammer",1,new Item[]{copperRod, copperRod,copperRod, copperRod},
                new int[]{3,3,3,3}, Materials.copperRodFrame,1);

        addRecipe("hammer",1,new Item[]{copperPlate, copperPlate,copperPlate},
                new int[]{1,1,1}, Tools.copperBucket,1);
        addRecipe("hammer",1,new Item[]{bronzePlate, bronzePlate, bronzePlate},
                new int[]{2,2,2}, Auto.bronzeBoiler.asItem(),1);

        addRecipe("hammer",1,new Item[]{smallBronzePlate,smallBronzePlate,smallBronzePlate,smallBronzePlate},
                new int[]{1,1,1,1}, bronzeHalfPipe,1);
        addRecipe("hammer",1,new Item[]{smallBronzePlate,bronzeHalfPipe},
                new int[]{1,1}, Materials.bronzeCylinder,1);
        addRecipe("hammer",1,new Item[]{bronzeHalfPipe,bronzeHalfPipe},
                new int[]{1,1}, Pipes.bronzeStraightPipe.asItem(),1);
        addRecipe("hammer",1,new Item[]{Pipes.bronzeStraightPipe.asItem()},
                new int[]{1}, Pipes.bronzeBentPipe.asItem(),1);
        addRecipe("hammer",1,new Item[]{bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe},
                new int[]{1,1,1}, Pipes.bronzeTPipe.asItem(),1);
        addRecipe("hammer",1,new Item[]{bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe},
                new int[]{1,1,1,1}, Pipes.bronzeCrossPipe.asItem(),1);
        addRecipe("hammer",1,new Item[]{bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe},
                new int[]{1,1,1,1,1}, Pipes.bronzeFiveWayPipe.asItem(),1);
        addRecipe("hammer",1,new Item[]{bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe,bronzeHalfPipe},
                new int[]{1,1,1,1,1,1}, Pipes.bronzeSixWayPipe.asItem(),1);


        addRecipe("hammer",1,new Item[]{bronzePlate, bronzePlate,bronzePlate, bronzePlate},
                new int[]{1,1,1,1}, Armors.bronzeBoots,1);
        addRecipe("hammer",1,new Item[]{copperPlate, copperPlate,copperPlate, copperPlate},
                new int[]{1,1,1,1}, Armors.copperBoots,1);
        addRecipe("hammer",1,new Item[]{bronzePlate, bronzePlate,bronzePlate, bronzePlate, bronzePlate},
                new int[]{1,1,1,1,1}, Armors.bronzeHelmet,1);
        addRecipe("hammer",1,new Item[]{copperPlate, copperPlate,copperPlate, copperPlate, copperPlate},
                new int[]{1,1,1,1,1}, Armors.copperHelmet,1);
        addRecipe("hammer",1,new Item[]{bronzePlate, bronzePlate,bronzePlate, bronzePlate, bronzePlate, bronzePlate, bronzePlate},
                new int[]{1,1,1,1,1,1,1}, Armors.bronzeLeg,1);
        addRecipe("hammer",1,new Item[]{copperPlate, copperPlate,copperPlate, copperPlate, copperPlate, copperPlate, copperPlate},
                new int[]{1,1,1,1,1,1,1}, Armors.copperLeg,1);
        addRecipe("hammer",1,new Item[]{bronzePlate, bronzePlate,bronzePlate, bronzePlate, bronzePlate, bronzePlate, bronzePlate, bronzePlate},
                new int[]{1,1,1,1,1,1,1,1}, Armors.bronzeChest,1);
        addRecipe("hammer",1,new Item[]{copperPlate, copperPlate,copperPlate, copperPlate, copperPlate, copperPlate, copperPlate, copperPlate},
                new int[]{1,1,1,1,1,1,1,1}, Armors.copperChest,1);


        addRecipe("rasper",1,new Item[]{copperPlate,stick},
                new int[]{1,1}, Tools.copperKnife,1);
        addRecipe("rasper",1,new Item[]{bronzePlate,stick},
                new int[]{1,1}, Tools.bronzeKnife,1);
        addRecipe("rasper",1,new Item[]{copperPlate, copperPlate,stick},
                new int[]{1,1,1}, Tools.copperSword,1);
        addRecipe("rasper",1,new Item[]{bronzePlate, bronzePlate,stick},
                new int[]{1,1,1}, Tools.bronzeSword,1);
        addRecipe("rasper",1,new Item[]{copperPlate, copperPlate,stick,stick,stick},
                new int[]{1,1,1,1,1}, Tools.copperSaw,1);
        addRecipe("rasper",1,new Item[]{bronzePlate, bronzePlate,stick,stick,stick},
                new int[]{1,1,1,1,1}, Tools.bronzeSaw,1);
        addRecipe("rasper",1,new Item[]{copperPlate,stick,stick},
                new int[]{1,1,1}, Tools.copperShovel,1);
        addRecipe("rasper",1,new Item[]{bronzePlate,stick,stick},
                new int[]{1,1,1}, Tools.bronzeShovel,1);
        addRecipe("rasper",1,new Item[]{copperPlate, copperIngot,stick,stick},
                new int[]{1,1,1,1}, Tools.copperHoe,1);
        addRecipe("rasper",1,new Item[]{bronzePlate, bronzeIngot,stick,stick},
                new int[]{1,1,1,1}, Tools.bronzeHoe,1);
        addRecipe("rasper",1,new Item[]{copperPlate,copperPlate, copperIngot,stick,stick},
                new int[]{1,1,1,1,1}, Tools.copperAxe,1);
        addRecipe("rasper",1,new Item[]{bronzePlate,bronzePlate, bronzeIngot,stick,stick},
                new int[]{1,1,1,1,1}, Tools.bronzeAxe,1);
        addRecipe("rasper",1,new Item[]{copperPlate,copperIngot, copperIngot,stick,stick},
                new int[]{1,1,1,1,1},Tools.copperPickaxe,1);
        addRecipe("rasper",1,new Item[]{bronzePlate,bronzeIngot, bronzeIngot,stick,stick},
                new int[]{1,1,1,1,1}, Tools.bronzePickaxe,1);

        addRecipe("rasper",1,new Item[]{copperIngot},
                new int[]{1}, copperRod,1 );
        addRecipe("rasper",1,new Item[]{tinIngot},
                new int[]{1}, tinRod,1);
        addRecipe("rasper",1,new Item[]{bronzeIngot},
                new int[]{1}, bronzeRod,1);

        addRecipe("rasper",1,new Item[]{copperPlate},
                new int[]{1}, Ores.copperGear,1 );
        addRecipe("rasper",1,new Item[]{tinPlate},
                new int[]{1}, Ores.tinGear,1);
        addRecipe("rasper",1,new Item[]{bronzePlate},
                new int[]{1}, Ores.bronzeGear,1);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        addRecipe("hammer",1,new Item[]{ironIngot, ironIngot},
                new int[]{1, 1}, ironPlate,1);
        addRecipe("hammer",2,new Item[]{steelIngot, steelIngot},
                new int[]{1, 1}, steelPlate,1);

        addRecipe("hammer",1,new Item[]{ironPlate, ironPlate,ironPlate},
                new int[]{1,1,1}, Items.BUCKET,1);

        addRecipe("hammer",1,new Item[]{ironRod, ironRod,ironRod, ironRod,ironRod,ironRod},
                new int[]{1,1,1,1,1,1}, Items.IRON_BARS,1);
        addRecipe("hammer",1,new Item[]{ironRod, ironRod,ironRod, ironRod},
                new int[]{3,3,3,3}, Materials.ironRodFrame,1);
        addRecipe("hammer",2,new Item[]{steelRod, steelRod,steelRod, steelRod},
                new int[]{3,3,3,3}, Materials.steelRodFrame,1);

        addRecipe("hammer",1,new Item[]{ironPlate, ironPlate,ironPlate, ironPlate},
                new int[]{1,1,1,1}, Items.IRON_BOOTS,1);
        addRecipe("hammer",2,new Item[]{steelPlate, steelPlate,steelPlate, steelPlate},
                new int[]{1,1,1,1}, Armors.steelBoots,1);
        addRecipe("hammer",1,new Item[]{ironPlate, ironPlate,ironPlate, ironPlate, ironPlate},
                new int[]{1,1,1,1,1}, Items.IRON_HELMET,1);
        addRecipe("hammer",2,new Item[]{steelPlate, steelPlate,steelPlate, steelPlate, steelPlate},
                new int[]{1,1,1,1,1}, Armors.steelHelmet,1);
        addRecipe("hammer",1,new Item[]{ironPlate, ironPlate,ironPlate, ironPlate, ironPlate, ironPlate, ironPlate},
                new int[]{1,1,1,1,1,1,1}, Items.IRON_LEGGINGS,1);
        addRecipe("hammer",2,new Item[]{steelPlate, steelPlate,steelPlate, steelPlate, steelPlate, steelPlate, steelPlate},
                new int[]{1,1,1,1,1,1,1}, Armors.steelLeg,1);
        addRecipe("hammer",1,new Item[]{ironPlate, ironPlate,ironPlate, ironPlate, ironPlate, ironPlate, ironPlate, ironPlate},
                new int[]{1,1,1,1,1,1,1,1}, Items.IRON_CHESTPLATE,1);
        addRecipe("hammer",2,new Item[]{steelPlate, steelPlate,steelPlate, steelPlate, steelPlate, steelPlate, steelPlate, steelPlate},
                new int[]{1,1,1,1,1,1,1,1}, Armors.steelChest,1);

        addRecipe("rasper",2,new Item[]{steelPlate,stick},
                new int[]{1,1}, Tools.steelKnife,1);
        addRecipe("rasper",1,new Item[]{ironPlate, ironPlate,stick},
                new int[]{1,1,1}, Items.IRON_SWORD,1);
        addRecipe("rasper",2,new Item[]{steelPlate, steelPlate,stick},
                new int[]{1,1,1}, Tools.steelSword,1);
        addRecipe("rasper",1,new Item[]{ironPlate,stick,stick},
                new int[]{1,1,1}, Items.IRON_SHOVEL,1);
        addRecipe("rasper",2,new Item[]{steelPlate,stick,stick},
                new int[]{1,1,1}, Tools.steelShovel,1);
        addRecipe("rasper",1,new Item[]{ironPlate, ironIngot,stick,stick},
                new int[]{1,1,1,1}, Items.IRON_HOE,1);
        addRecipe("rasper",2,new Item[]{steelPlate, steelIngot,stick,stick},
                new int[]{1,1,1,1}, Tools.bronzeHoe,1);
        addRecipe("rasper",1,new Item[]{ironPlate,ironPlate, ironIngot,stick,stick},
                new int[]{1,1,1,1,1}, Items.IRON_AXE,1);
        addRecipe("rasper",2,new Item[]{steelPlate,steelPlate, steelIngot,stick,stick},
                new int[]{1,1,1,1,1}, Tools.bronzeAxe,1);
        addRecipe("rasper",1,new Item[]{ironPlate,ironIngot, ironIngot,stick,stick},
                new int[]{1,1,1,1,1},Items.IRON_PICKAXE,1);
        addRecipe("rasper",2,new Item[]{steelPlate,steelIngot, steelIngot,stick,stick},
                new int[]{1,1,1,1,1}, Tools.bronzePickaxe,1);
        addRecipe("rasper",2,new Item[]{steelPlate, steelPlate,stick,stick,stick},
                new int[]{1,1,1,1,1}, Tools.steelSaw,1);

        addRecipe("rasper",1,new Item[]{ironIngot},
                new int[]{1}, ironRod,1 );
        addRecipe("rasper",2,new Item[]{steelIngot},
                new int[]{1}, steelRod,1);

        addRecipe("rasper",2,new Item[]{Items.QUARTZ,Items.QUARTZ,Items.QUARTZ,Items.QUARTZ,Items.QUARTZ},
                new int[]{1,1,1,1,1},Auto.quartzCrucible.asItem(),1);
    }
}