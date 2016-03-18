package com.mattdahepic.ringkydinks.item.ui;

import com.mattdahepic.mdecore.helpers.TranslationHelper;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/*public class GUIRubberHand extends GuiContainer {
    public GUIRubberHand () {
        super();
    }
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        //the parameters for drawString are: string, x, y, color
        fontRendererObj.drawString(TranslationHelper.getTranslatedString("item.rubber_hand.name"), 8, 6, 4210752);
        //draws "Inventory" or your regional equivalent
        fontRendererObj.drawString(TranslationHelper.getTranslatedString("container.inventory"), 8, ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(new ResourceLocation("ringkydinks","gui/guiRubberHand"));
        int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
    }
}
*/