package detailedTechnology.blocks.machines.auto.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import detailedTechnology.blocks.machines.auto.screenHandler.PrimitiveBlastFurnaceScreenHandler;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class PrimitiveBlastFurnaceScreen extends HandledScreen<ScreenHandler> {

    private static final Identifier TEXTURE = new Identifier("dt", "textures/gui/container/primitive_blast_furnace.png");
    private final PrimitiveBlastFurnaceScreenHandler screenHandler;
    public PrimitiveBlastFurnaceScreen(ScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
        this.screenHandler=(PrimitiveBlastFurnaceScreenHandler) handler;
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
        writeText(matrices,Integer.toString(screenHandler.getTemperature())+"/"+
                Integer.toString(screenHandler.getRequiredTemperature())+"celsius", 41, 41);
        writeText(matrices,Integer.toString(screenHandler.getWorkingTime())+"/"+
                Integer.toString(screenHandler.getRequiredTime())+"ticks", 41, 61);
        writeText(matrices,"Burning Time:"+Integer.toString(screenHandler.getBurningTime())+"ticks", 41, 81);
        writeText(matrices,"Goal:"+screenHandler.getName(), 41, 101);
        writeText(matrices,"Valid:"+screenHandler.getValid(), 41, 121);
        writeText(matrices,"ingredient", 210, 61);
        writeText(matrices,"fuel", 210, 98);
        writeText(matrices,"product", 290, 61);
        writeText(matrices,"small crucible", 290, 98);
        //textRenderer.draw(matrices,Integer.toString(mouseX)+":"+Integer.toString(mouseY),0,0,0xffffff);
    }

    @Override
    protected void init() {
        super.init();
        // Center the title
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }
}