package detailedTechnology.mixin;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.ShapedRecipe;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ShapedRecipe.class)
public  class RecipeEdit {
    private static final Item[] contents = new Item[]{
            Items.FURNACE,Items.IRON_BOOTS,Items.IRON_HOE,Items.IRON_HELMET,
            Items.IRON_CHESTPLATE,Items.IRON_LEGGINGS,Items.IRON_AXE,Items.IRON_PICKAXE,
            Items.IRON_SHOVEL,Items.IRON_SWORD,Items.BUCKET,Items.IRON_BARS};

    @Shadow @Final private ItemStack output;
    @Inject(at = @At("HEAD"), method = "getOutput",cancellable = true)
    private void getOutput(CallbackInfoReturnable<ItemStack> callbackInfoReturnable){
        Item product = output.getItem();
        for (Item content : contents) {
            if (content.equals(product)) {
                callbackInfoReturnable.setReturnValue(Items.AIR.getDefaultStack());
            }
        }
    }
}
