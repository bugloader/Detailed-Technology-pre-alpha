package detailedTechnology;

import detailedTechnology.group.Machines;
import detailedTechnology.gui.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.ModelLoadingRegistry;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

@Environment(EnvType.CLIENT)
public class DetailedTechnologyModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Machines.crucibleScreenHandler, CrucibleScreen::new);
        ScreenRegistry.register(Machines.stoneMileScreenHandler, StoneMileScreen::new);
        ScreenRegistry.register(Machines.fireStarterBlockScreenHandler, FireStarterBlockScreen::new);
        ScreenRegistry.register(Machines.bronzeAnvilScreenHandler, BronzeAnvilScreen::new);
        ScreenRegistry.register(Machines.kilnScreenHandler, KilnScreen::new);
        ScreenRegistry.register(Machines.combustionChamberScreenHandler, CombustionChamberScreen::new);

    }
}