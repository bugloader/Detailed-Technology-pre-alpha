package detailedTechnology;

import detailedTechnology.blocks.machines.manual.screen.*;
import detailedTechnology.blocks.tanks.screen.BarrelScreen;
import detailedTechnology.group.machine.Auto;
import detailedTechnology.group.machine.Pipes;
import detailedTechnology.blocks.machines.auto.screen.*;
import detailedTechnology.group.machine.Manual;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class DetailedTechnologyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Auto.crucibleScreenHandler, CrucibleScreen::new);
        ScreenRegistry.register(Manual.carpenterWorkbenchScreenHandler, CarpenterWorkbenchScreen::new);
        ScreenRegistry.register(Auto.stoneMileScreenHandler, StoneMileScreen::new);
        ScreenRegistry.register(Manual.fireStarterBlockScreenHandler, FireStarterBlockScreen::new);
        ScreenRegistry.register(Manual.bronzeAnvilScreenHandler, AnvilScreen::new);
        ScreenRegistry.register(Auto.kilnScreenHandler, KilnScreen::new);
        ScreenRegistry.register(Auto.combustionChamberScreenHandler, CombustionChamberScreen::new);
        ScreenRegistry.register(Manual.clayModelScreenHandler, ModelScreen::new);
        ScreenRegistry.register(Pipes.barrelScreenHandler, BarrelScreen::new);
        ScreenRegistry.register(Auto.cokeOvenScreenHandler, CokeOvenScreen::new);
        ScreenRegistry.register(Auto.primitiveBlastFurnaceScreenHandler, PrimitiveBlastFurnaceScreen::new);
        ScreenRegistry.register(Auto.bronzeBoilerScreenHandler, BronzeBoilerScreen::new);
    }
}