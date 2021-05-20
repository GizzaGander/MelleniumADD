package com.Mellenium.Addons.common;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.containers.ContainerDecrypter;
import com.Mellenium.Addons.common.guis.GuiDecrypter;
import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandlerMel implements IGuiHandler {

    public enum GUIs {
        Decrypter
    };

    public GuiHandlerMel() {
        NetworkRegistry.INSTANCE.registerGuiHandler(MelleniumAddons.instance, this);
    }

    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        TileEntity te = world.getTileEntity(i1,i2,i3);
        if(te != null && te instanceof TileEntityDecrypter){
            if(i == GUIs.Decrypter.ordinal()){
                return new ContainerDecrypter(entityPlayer.inventory, (TileEntityDecrypter) te);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        TileEntity te = world.getTileEntity(i1,i2,i3);
        if(te != null && te instanceof TileEntityDecrypter){
            if(i == GUIs.Decrypter.ordinal()){
                return new GuiDecrypter(entityPlayer.inventory, (TileEntityDecrypter) te);
            }
        }
        return null;
    }
}
