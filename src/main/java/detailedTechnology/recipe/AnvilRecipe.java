package detailedTechnology.recipe;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blocks.Ores;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class AnvilRecipe {

    public static final ArrayList<ShapelessRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<String> TOOL_TYPE = new ArrayList<>();
    public static final ArrayList<Integer> TOOL_LEVEL = new ArrayList<>();
    public static final ArrayList<Item> PRODUCTS = new ArrayList<>();

    public void addRecipe(String toolName,int toolLevel,String[] ingredients, int[] ingredientsNum, String result, int resultNum, Item productStack) {

        TOOL_TYPE.add(toolName);
        TOOL_LEVEL.add(toolLevel);
        CONTENTS.add(new ShapelessRecipe(ingredients,ingredientsNum,result,resultNum));
        PRODUCTS.add(productStack);
    }

    public boolean[] newBooleanArrayWithFalse(int length)
    {
        boolean[] result = new boolean[length];
        for(int i=0;i<length;i++) result[i]=false;
        return result;
    }

    public boolean tryCraft(String toolName,int toolLevel,Inventory inventory) {
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
            if (TOOL_TYPE.get(i).equals(toolName)&&TOOL_LEVEL.get(i).equals(toolLevel)&&
                    content.ingredients.length == stackCount &&
                    (resultItemName.equals(content.result)||resultItemName.equals("Air")) &&
                    content.resultNum + resultItemNum <= 64) {
                included1 = newBooleanArrayWithFalse(9);
                included2 = newBooleanArrayWithFalse(9);
                checkNum = 0;
                for (int j = 0; j < stackCount; j++) {
                    for(int k = 0; k < stackCount; k++) {
                        if(!included1[j]&&!included2[k]&&
                                itemNames[j].equals(content.ingredients[k])&&
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
                                    itemNames[j].equals(content.ingredients[k])&&
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
                        inventory.setStack(9,PRODUCTS.get(i).getDefaultStack());
                        inventory.getStack(9).setCount(content.resultNum);
                    }else{
                        inventory.getStack(9).setCount(inventory.getStack(9).getCount()+content.resultNum);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public AnvilRecipe() {
        addRecipe("hammer",1,new String[]{"item.dt.copper_ingot", "item.dt.copper_ingot"},
                new int[]{1, 1}, "item.dt.copper_plate",1, Ores.copperPlate);
        addRecipe("hammer",1,new String[]{"item.dt.tin_ingot", "item.dt.tin_ingot"},
                new int[]{1, 1}, "item.dt.tin_plate",1,Ores.tinPlate);
        addRecipe("hammer",1,new String[]{"item.dt.bronze_ingot", "item.dt.bronze_ingot"},
                new int[]{1, 1}, "item.dt.bronze_plate",1,Ores.bronzePlate);

        addRecipe("hammer",1,new String[]{"item.dt.bronze_rod", "item.dt.bronze_rod","item.dt.bronze_rod", "item.dt.bronze_rod"},
                new int[]{3,3,3,3}, "item.dt.bronze_rod_frame",1,DetailedTechnology.bronzeRodFrame);
        addRecipe("hammer",1,new String[]{"item.dt.copper_rod", "item.dt.copper_rod","item.dt.copper_rod", "item.dt.copper_rod"},
                new int[]{3,3,3,3}, "item.dt.copper_rod_frame",1,DetailedTechnology.copperRodFrame);

        addRecipe("hammer",1,new String[]{"item.dt.bronze_plate", "item.dt.bronze_plate","item.dt.bronze_plate", "item.dt.bronze_plate"},
                new int[]{1,1,1,1}, "null",1,DetailedTechnology.bronzeBoots);
        addRecipe("hammer",1,new String[]{"item.dt.copper_plate", "item.dt.copper_plate","item.dt.copper_plate", "item.dt.copper_plate"},
                new int[]{1,1,1,1}, "null",1,DetailedTechnology.copperBoots);
        addRecipe("hammer",1,new String[]{"item.dt.bronze_plate", "item.dt.bronze_plate","item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate"},
                new int[]{1,1,1,1,1}, "null",1,DetailedTechnology.bronzeHelmet);
        addRecipe("hammer",1,new String[]{"item.dt.copper_plate", "item.dt.copper_plate","item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate"},
                new int[]{1,1,1,1,1}, "null",1,DetailedTechnology.copperHelmet);
        addRecipe("hammer",1,new String[]{"item.dt.bronze_plate", "item.dt.bronze_plate","item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate"},
                new int[]{1,1,1,1,1,1,1}, "null",1,DetailedTechnology.bronzeLeg);
        addRecipe("hammer",1,new String[]{"item.dt.copper_plate", "item.dt.copper_plate","item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate"},
                new int[]{1,1,1,1,1,1,1}, "null",1,DetailedTechnology.copperLeg);
        addRecipe("hammer",1,new String[]{"item.dt.bronze_plate", "item.dt.bronze_plate","item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate", "item.dt.bronze_plate"},
                new int[]{1,1,1,1,1,1,1,1}, "null",1,DetailedTechnology.bronzeChest);
        addRecipe("hammer",1,new String[]{"item.dt.copper_plate", "item.dt.copper_plate","item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate", "item.dt.copper_plate"},
                new int[]{1,1,1,1,1,1,1,1}, "null",1,DetailedTechnology.copperChest);

        addRecipe("rasper",1,new String[]{"item.dt.copper_ingot"},
                new int[]{1, 1}, "item.dt.copper_rod",1, Ores.copperRod);
        addRecipe("rasper",1,new String[]{"item.dt.tin_ingot"},
                new int[]{1, 1}, "item.dt.tin_rod",1,Ores.tinRod);
        addRecipe("rasper",1,new String[]{"item.dt.bronze_ingot"},
                new int[]{1, 1}, "item.dt.bronze_rod",1,Ores.bronzeRod);
    }
}