package detailedTechnology.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class ExampleMixin{

	@Shadow static @Final Identifier EDITION_TITLE_TEXTURE;
	@Inject(at = @At("HEAD"), method = "render")
	private void render(CallbackInfo info) {
		EDITION_TITLE_TEXTURE = new Identifier("dt", "textures/gui/title/edition.png");
	}
}
