package com.Mellenium.Addons.common.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.gui.IUpdatePlayerListBox;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityDecrypter extends TileEntity implements ISidedInventory, IUpdatePlayerListBox {


    @Override
    public void update() {

    }

    public enum slotEnum {
        BOOK_SLOT, LENS_SLOT, OUTPUT_SLOT
    }

    private ItemStack[] slots = new ItemStack[2];

    @Override
    public int[] getAccessibleSlotsFromSide(int i) {
        return new int[0];
    }

    @Override
    public boolean canInsertItem(int i, ItemStack itemStack, int i1) {
        return true;
    }

    @Override
    public boolean canExtractItem(int i, ItemStack itemStack, int i1) {
        return true;
    }

    @Override
    public int getSizeInventory() {
        return slots.length;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return slots[i];
    }

    @Override
    public ItemStack decrStackSize(int idx, int count) {
        if(slots[idx] != null){
            ItemStack taken;
            if(slots[idx].stackSize <= count){
                taken = slots[idx];
                slots[idx] = null;
            }else{
                taken = slots[idx].splitStack(count);
                if(slots[idx].stackSize == 0){
                    slots[idx] = null;
                }
            }
            return taken;
        }else{
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int i) {
        if(slots[i] != null){
            ItemStack is = slots[i];
            slots[i] = null;
            return is;
        }else {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        slots[i] = itemStack;
    }

    @Override
    public String getInventoryName() {
        return "Hello inventory";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemStack) {
        return true;
    }

    @Override
    public void writeToNBT(NBTTagCompound p_145841_1_) {
        super.writeToNBT(p_145841_1_);
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_) {
        super.readFromNBT(p_145839_1_);
    }
}
