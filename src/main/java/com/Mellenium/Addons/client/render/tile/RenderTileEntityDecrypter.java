package com.Mellenium.Addons.client.render.tile;

import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

public class RenderTileEntityDecrypter extends TileEntitySpecialRenderer {

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        GL11.glPushMatrix();

        GL11.glTranslatef((float) x, (float) y, (float) z);
        TileEntityDecrypter tile = (TileEntityDecrypter) tileEntity;
        renderBlock(tile, tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, f);

        GL11.glPopMatrix();
    }

    public void renderBlock(TileEntityDecrypter tile, World world, int x, int y, int z, float f) {
        Tessellator tessellator = Tessellator.instance;
        //bindTexture(texture);

        tessellator.setColorRGBA(255, 255, 255, 255);
        tessellator.setBrightness(200);
        int l = world.getLightBrightnessForSkyBlocks(x, y, z, 0);
        int l1 = l % 65536;
        int l2 = l / 65536;
        tessellator.setColorOpaque_F(1f, 1f, 1f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) l1, (float) l2);

        GL11.glPushMatrix();
        GL11.glRotatef(90, 1, 0, 0);
        GL11.glTranslated(0, 1.2, -0.27);
        GL11.glRotatef(180, 1, 0, 0);
        GL11.glPopMatrix();
        if (Minecraft.isFancyGraphicsEnabled()) {
            GL11.glPushMatrix();
            GL11.glTranslated(0, 0.4, 0);
            GL11.glPopMatrix();
        } else {
            GL11.glPushMatrix();
            GL11.glRotatef(90, 1, 0, 0);
            GL11.glTranslated(0, 1.2, -0.37);
            GL11.glRotatef(180, 1, 0, 0);
            GL11.glPopMatrix();
        }
    }
}