package com.Mellenium.Addons.client.gui;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.containers.ContainerDecrypter;
import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerMel implements IGuiHandler {

    public static final int GUIID_DECRYPTER = 0;
    public static final int GUIID_BOOKS = 1;

    public GuiHandlerMel() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MelleniumAddons.instance, this);
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        switch (ID) {
            case GUIID_DECRYPTER:
                TileEntity te = world.getTileEntity(i1, i2, i3);
                if (te != null && te instanceof TileEntityDecrypter) {
                    return new ContainerDecrypter(entityPlayer.inventory, (TileEntityDecrypter) te);
                }
                break;
        }
        return null;
    }


    @Override
    public Object getClientGuiElement(int ID, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        switch (ID) {
            case GUIID_DECRYPTER:
                TileEntity te = world.getTileEntity(i1,i2,i3);
                if(te != null && te instanceof TileEntityDecrypter){
                    return new GuiDecrypter(entityPlayer.inventory, (TileEntityDecrypter) te);
                }
                break;
            case GUIID_BOOKS:
                    return new GuiBooks();
        }
        return null;
    }
}
