package detailedTechnology;

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
        ScreenRegistry.register(DetailedTechnology.crucibleScreenHandler, CrucibleScreen::new);
        ScreenRegistry.register(DetailedTechnology.stoneMileScreenHandler, StoneMileScreen::new);
        ScreenRegistry.register(DetailedTechnology.fireStarterBlockScreenHandler, FireStarterBlockScreen::new);
        ScreenRegistry.register(DetailedTechnology.bronzeAnvilScreenHandler, BronzeAnvilScreen::new);
        ScreenRegistry.register(DetailedTechnology.kilnScreenHandler, KilnScreen::new);

    }
}