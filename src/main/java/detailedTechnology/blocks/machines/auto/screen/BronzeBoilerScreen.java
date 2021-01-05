package detailedTechnology.blocks.machines.auto.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import detailedTechnology.blocks.machines.auto.screenHandler.BronzeBoilerScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BronzeBoilerScreen extends HandledScreen<ScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("dt", "textures/gui/container/single.png");
    private final BronzeBoilerScreenHandler screenHandler;
    public BronzeBoilerScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.screenHandler = (BronzeBoilerScreenHandler) handler;
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
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
        writeText(matrices,"Bucket", 240, 62);
        writeText(matrices,"Water: "+Integer.toString(screenHandler.getLiquidAmount())+" mb", 40, 40);
        writeText(matrices,"Heat Capacitance: "+Integer.toString(screenHandler.getHeatCapacitance()), 40, 60);
        writeText(matrices,"Temperature: "+Integer.toString(screenHandler.getTemperature())+" celsius", 40, 80);
        writeText(matrices,"Steam Pressure: "+Integer.toString(screenHandler.getSteamPressure())+" kPa", 40, 100);
        //textRenderer.draw(matrices,Integer.toString(mouseX)+":"+Integer.toString(mouseY),0,0,0xffffff);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}