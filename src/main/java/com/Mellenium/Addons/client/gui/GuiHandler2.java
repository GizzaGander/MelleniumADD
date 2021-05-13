package com.Mellenium.Addons.client.gui;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.container.ContainerDecrypter;
import com.Mellenium.Addons.common.tileentity.TileDecrypter;
import com.brandon3055.draconicevolution.DraconicEvolution;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler2 implements IGuiHandler {

    public static final int GUIID_DECRYPTER = 0;

    public GuiHandler2() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MelleniumAddons.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUIID_DECRYPTER:
                TileEntity decrypter = world.getTileEntity(x, y, z);
                if (decrypter != null && decrypter instanceof TileDecrypter) {
                    return new ContainerDecrypter(player.inventory, (TileDecrypter) decrypter);
                }
                break;
        }

        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUIID_DECRYPTER:
                TileEntity decrypter = world.getTileEntity(x, y, z);
                if (decrypter != null && decrypter instanceof TileDecrypter) {
                    return new GUIDecrypter(player.inventory, (TileDecrypter) decrypter);
                }
                break;
        }
        return null;
    }
}
