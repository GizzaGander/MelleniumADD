package com.Mellenium.Addons.common.guis;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FontRendererElph extends FontRenderer {

    private final int[] elphCharWidth = new int[256];

    public FontRendererElph(GameSettings p_i1035_1_, ResourceLocation p_i1035_2_, TextureManager p_i1035_3_, boolean p_i1035_4_) {
        super(p_i1035_1_, p_i1035_2_, p_i1035_3_, p_i1035_4_);
        BufferedImage bufferedimage;

        try
        {
            bufferedimage = ImageIO.read(getResourceInputStream(this.locationFontTexture));
        }
        catch (IOException ioexception)
        {
            throw new RuntimeException(ioexception);
        }
        final int singleCharWidth = bufferedimage.getWidth()/32/2;

        for(int i = 0; i < this.elphCharWidth.length; i++){
            switch(i) {
                case 16*8://А
                case 16*10://а
                case 16*8+5://Е
                case 16*10+5://е
                case 16*8+7:
                case 16*10+7:
                case 16*8+8:
                case 16*10+8:
                case 16*8+14:
                case 16*10+14:
                case 16*9+3:
                case 16*11+3:
                case 16*9+12:
                case 16*11+12:
                    this.elphCharWidth[i] = singleCharWidth/2;
                    break;
                case 16*8+9:
                case 16*10+9:
                case 16*8+15:
                case 16*10+15:
                case 16*9:
                case 16*11:
                case 16*9+1:
                case 16*11+1:
                case 16*9+2:
                case 16*11+2:
                case 16*9+4:
                case 16*11+4:
                case 16*9+7:
                case 16*11+7:
                case 16*9+8:
                case 16*11+8:
                case 16*9+9:
                case 16*11+9:
                case 16*9+14:
                case 16*11+14:
                case 16*9+15:
                case 16*11+15:
                    this.elphCharWidth[i] = singleCharWidth/2+singleCharWidth/4;
                    break;
                case 16*8+3:
                case 16*10+3:
                case 16*8+13:
                case 16*10+13:
                    this.elphCharWidth[i] = singleCharWidth+singleCharWidth/5;
                    break;
                default:
                    this.elphCharWidth[i] = singleCharWidth;
                    break;
            }
        }
    }

    private void loadGlyphTexture(int i){
        bindTexture(this.locationFontTexture);
    }


    @Override
    protected float renderDefaultChar(int p_78266_1_, boolean p_78266_2_) {
        float f = (float)(p_78266_1_ % 16 * 8);
        float f1 = (float)(p_78266_1_ / 16 * 8);
        float f2 = p_78266_2_ ? 1.0F : 0.0F;
        bindTexture(this.locationFontTexture);
        float f3 = (float)this.elphCharWidth[p_78266_1_] - 0.01F;
        GL11.glBegin(GL11.GL_TRIANGLE_STRIP);
        GL11.glTexCoord2f(f / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(this.posX + f2, this.posY, 0.0F);
        GL11.glTexCoord2f(f / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(this.posX - f2, this.posY + 7.99F, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, f1 / 128.0F);
        GL11.glVertex3f(this.posX + f3 - 1.0F + f2, this.posY, 0.0F);
        GL11.glTexCoord2f((f + f3 - 1.0F) / 128.0F, (f1 + 7.99F) / 128.0F);
        GL11.glVertex3f(this.posX + f3 - 1.0F - f2, this.posY + 7.99F, 0.0F);
        GL11.glEnd();
        return (float)this.elphCharWidth[p_78266_1_];
    }

    @Override
    protected float renderUnicodeChar(char p_78277_1_, boolean p_78277_2_) {
        if (this.glyphWidth[p_78277_1_] == 0)
        {
            return 0.0F;
        } else if(p_78277_1_ >= 'а' && p_78277_1_ <= 'я'){
            return this.renderDefaultChar((((int)p_78277_1_)-((int)'а'))+16*10, p_78277_2_);
        } else if(p_78277_1_ >= 'А' && p_78277_1_ <= 'Я'){
            return this.renderDefaultChar((((int)p_78277_1_)-((int)'А'))+16*8, p_78277_2_);
        }
        else
        {
            return 0f;
        }
    }

}
