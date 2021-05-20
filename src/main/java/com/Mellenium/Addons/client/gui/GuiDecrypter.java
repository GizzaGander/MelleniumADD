package com.Mellenium.Addons.client.gui;

import com.Mellenium.Addons.client.font.FontRendererElph;
import com.Mellenium.Addons.common.containers.ContainerDecrypter;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class GuiDecrypter extends GuiContainer {

    private static final ResourceLocation guiTexture = new ResourceLocation(References2.MODID+":textures/gui/decrypter.png");
    private  final InventoryPlayer playerInv;
    private final IInventory decrypterInv;
    private final FontRenderer fontRenderer2;

    public GuiDecrypter(InventoryPlayer ip, IInventory tileInv){
        super(new ContainerDecrypter(ip, tileInv));
        playerInv = ip;
        decrypterInv = tileInv;
        fontRenderer2 = new FontRendererElph(Minecraft.getMinecraft().gameSettings, new ResourceLocation(References2.MODID+":textures/font/ascii_elf.png"), Minecraft.getMinecraft().renderEngine, false);
        ySize = 142;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float v, int i, int i1) {
        mc.getTextureManager().bindTexture(guiTexture);
        int marginHorizontal = (width - xSize) / 2;
        int marginVertical = (height - ySize) / 2;
        drawTexturedModalRect(marginHorizontal, marginVertical, 0, 0,
                xSize, ySize);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_) {
        String s = decrypterInv.getInventoryName();
        fontRenderer2.drawString("абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ", 0, 16, 0xFFFFFF);
        fontRenderer2.drawString("н", 16, 32, 0xFFFFFF);
        fontRenderer2.drawString(s, xSize/2-fontRendererObj
                .getStringWidth(s)/2, 6, 4210752);
        fontRendererObj.drawString(playerInv.getInventoryName(),
                8, ySize - 96 + 2, 4210752);
    }
}
