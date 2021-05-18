package com.Mellenium.Addons.client.lib;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class UtilsFX extends thaumcraft.client.lib.UtilsFX {
    static Map<String, ResourceLocation> boundTextures = new HashMap();

    public static void bindTexture(String texture) {
        ResourceLocation rl = null;
        if (boundTextures.containsKey(texture)) {
            rl = (ResourceLocation)boundTextures.get(texture);
        } else {
            rl = new ResourceLocation("melads", texture);
        }

        Minecraft.getMinecraft().renderEngine.bindTexture(rl);
    }

    public static void renderQuadCenteredFromTexture(String texture, float scale, float red, float green, float blue, int brightness, int blend, float opacity) {
        bindTexture(texture);
        renderQuadCenteredFromTexture(scale, red, green, blue, brightness, blend, opacity);
    }
}
