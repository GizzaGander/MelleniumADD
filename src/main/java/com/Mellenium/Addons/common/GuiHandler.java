package com.Mellenium.Addons.common;

import com.Mellenium.Addons.common.containers.ContainerDecrypter;
import com.Mellenium.Addons.common.guis.GuiDecrypter;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

@SideOnly(Side.CLIENT)
public class GuiHandler implements IGuiHandler {

    public enum GUIs {
        Decrypter
    };

    @Override
    public Object getServerGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        TileEntity te = world.getTileEntity(i1,i2,i3);
        if(te != null){
            if(i == GUIs.Decrypter.ordinal()){
                return new ContainerDecrypter(entityPlayer.inventory, (IInventory) te);
            }
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int i, EntityPlayer entityPlayer, World world, int i1, int i2, int i3) {
        TileEntity te = world.getTileEntity(i1,i2,i3);
        if(te != null){
            if(i == GUIs.Decrypter.ordinal()){
                return new GuiDecrypter(entityPlayer.inventory, (IInventory) te);
            }
        }
        return null;
    }
}
