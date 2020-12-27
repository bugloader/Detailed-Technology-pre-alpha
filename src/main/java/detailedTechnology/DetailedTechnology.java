package detailedTechnology;

import detailedTechnology.blockEntity.*;
import detailedTechnology.blocks.*;
import detailedTechnology.gui.*;
import detailedTechnology.items.*;
import detailedTechnology.items.generalclass.*;
import detailedTechnology.group.Recipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DetailedTechnology implements ModInitializer {

	public static final String MOD_ID = "dt";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"), () -> new ItemStack(Blocks.COBBLESTONE));

	public static final CharcoalHeap charcoalHeap = new CharcoalHeap();

	@Override
	public void onInitialize() {

		Registration.blockWithItem("Charcoal Heap",charcoalHeap,ITEM_GROUP);


		new Recipes();

		System.out.println("Hello! Detailed world!");

	}
}
