package detailedTechnology.blocks.machines.manual.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import detailedTechnology.blocks.machines.manual.screenHandler.CarpenterWorkbenchScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class CarpenterWorkbenchScreen extends HandledScreen<ScreenHandler> {
    //A path to the gui texture. In this example we use the texture from the dispenser
    private static final Identifier TEXTURE = new Identifier("dt", "textures/gui/container/bronze_anvil.png");
    private final CarpenterWorkbenchScreenHandler screenHandler;
    public CarpenterWorkbenchScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        screenHandler = (CarpenterWorkbenchScreenHandler)handler;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        assert client != null;
        client.getTextureManager().bindTexture(TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
    }

    public void writeText(MatrixStack matrices,String text,int x,int y){
        textRenderer.draw(matrices,text,x+1,y+1,0x000000);
        textRenderer.draw(matrices,text,x,y,0xffffff);
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta){
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);

        writeText(matrices,Integer.toString(screenHandler.getTime())+"/4 times", 41, 41);
        writeText(matrices,"Goal:"+screenHandler.getName(), 41, 61);
        writeText(matrices,screenHandler.getTool(), 41, 81);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}