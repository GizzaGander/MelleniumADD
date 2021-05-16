package com.Mellenium.Addons.common.guis;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FontRendererElph extends FontRenderer {

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
        final int singleCharWidth = bufferedimage.getWidth()/16/2;

        for(int i = 0; i < this.charWidth.length; i++){
            this.charWidth[i] = singleCharWidth;
        }
    }

    private void loadGlyphTexture(int i){
        bindTexture(this.locationFontTexture);
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
