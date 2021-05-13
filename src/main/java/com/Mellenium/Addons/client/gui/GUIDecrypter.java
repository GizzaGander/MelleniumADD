package com.Mellenium.Addons.client.gui;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.container.ContainerDecrypter;
import com.Mellenium.Addons.common.network.ButtonPacket2;
import com.Mellenium.Addons.common.tileentity.TileDecrypter;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.gui.GuiButtonAHeight;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GUIDecrypter extends GuiContainer {

    public EntityPlayer player;
    private TileDecrypter tile;
    private boolean cachRecipeValid = false;
    private int cachCost = 0;

    public GUIDecrypter(InventoryPlayer invPlayer, TileDecrypter tile) {
        super(new ContainerDecrypter(invPlayer, tile));

        xSize = 176;
        ySize = 142;

        this.tile = tile;
        this.player = invPlayer.player;
    }

    private static final ResourceLocation texture = new ResourceLocation(References2.MODID.toLowerCase(), "textures/gui/Decrypter.png");

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int x, int y) {
        GL11.glColor4f(1F, 1F, 1F, 1F);
        Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
        drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

    }

    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        drawCenteredString(fontRendererObj, StatCollector.translateToLocal("tile.melads:Decrypter.name"), 88, 4, 0x00FFFF);

        fontRendererObj.drawString("Item", 5, 40, 0x0000ff);
        fontRendererObj.drawString("Damage: " + (40 - tile.bookPower) + "%", 5, 49, 0x0000ff);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void initGui() {
        super.initGui();
        buttonList.clear();
        int posX = (this.width - xSize) / 2;
        int posY = (this.height - ySize) / 2;
        buttonList.add(new GuiButtonAHeight(0, posX + 108, posY + 45, 60, 12, ""));
        updateButtonState();
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 0) MelleniumAddons.network.sendToServer(new ButtonPacket2((byte) 1, true));
    }

    @Override
    public void updateScreen() {
        super.updateScreen();
        if (cachRecipeValid != tile.isValidRecipe) {
            cachRecipeValid = tile.isValidRecipe;
            updateButtonState();
        }
        if (cachCost != tile.dissenchantCost) {
            cachCost = tile.dissenchantCost;
            updateButtonState();
        }
    }

    private void updateButtonState() {
        boolean flag = cachRecipeValid;
        if (flag && player.experienceLevel < tile.dissenchantCost && !player.capabilities.isCreativeMode) flag = false;
        ((GuiButtonAHeight) buttonList.get(0)).enabled = flag;
        ((GuiButtonAHeight) buttonList.get(0)).packedFGColour = cachRecipeValid ? 0x000000 : 0xdf0000;
        ((GuiButtonAHeight) buttonList.get(0)).displayString = "Cost: " + tile.dissenchantCost;
    }
}
