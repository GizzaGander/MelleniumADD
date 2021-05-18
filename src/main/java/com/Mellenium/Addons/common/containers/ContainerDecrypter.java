package com.Mellenium.Addons.common.containers;

import com.Mellenium.Addons.common.recipes.DecrypterRecipes;
import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDecrypter extends Container {

    private final IInventory blockInv;
    private final int sizeInventory;

    public ContainerDecrypter(InventoryPlayer playerInv, IInventory inv) {
        blockInv = inv;
        sizeInventory = inv.getSizeInventory();
        addSlotToContainer(new Slot(blockInv, TileEntityDecrypter.slotEnum.INPUT_SLOT.ordinal(), 27, 23));
        addSlotToContainer(new Slot(blockInv, TileEntityDecrypter.slotEnum.INPUT_SLOT.ordinal(), 134, 23));
        addSlotToContainer(new Slot(blockInv, TileEntityDecrypter.slotEnum.OUTPUT_SLOT.ordinal(), 80, 24));
        // add player inventory slots
        // note that the slot numbers are within the player inventory so can
        // be same as the tile entity inventory
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                addSlotToContainer(new Slot(playerInv, j + i * 9 + 9,
                        8 + j * 18, 60 + i * 18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i) {
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18,
                    118));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn,
                                         int slotIndex) {
        ItemStack itemStack1 = null;
        Slot slot = (Slot) inventorySlots.get(slotIndex);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemStack2 = slot.getStack();
            itemStack1 = itemStack2.copy();

            if (slotIndex == TileEntityDecrypter.slotEnum.OUTPUT_SLOT
                    .ordinal()) {
                if (!mergeItemStack(itemStack2, sizeInventory,
                        sizeInventory + 36, true)) {
                    return null;
                }

                slot.onSlotChange(itemStack2, itemStack1);
            } else if (slotIndex != TileEntityDecrypter.slotEnum.INPUT_SLOT
                    .ordinal()) {
                // check if there is a grinding recipe for the stack
                if (DecrypterRecipes.getInstance().getDecriptingResult(itemStack2) != null) {
                    if (!mergeItemStack(itemStack2, 0, 1, false)) {
                        return null;
                    }
                } else if (slotIndex >= sizeInventory
                        && slotIndex < sizeInventory + 27) // player inventory slots
                {
                    if (!mergeItemStack(itemStack2, sizeInventory + 27,
                            sizeInventory + 36, false)) {
                        return null;
                    }
                } else if (slotIndex >= sizeInventory + 27
                        && slotIndex < sizeInventory + 36
                        && !mergeItemStack(itemStack2, sizeInventory + 1,
                        sizeInventory + 27, false)) // hotbar slots
                {
                    return null;
                }
            } else if (!mergeItemStack(itemStack2, sizeInventory,
                    sizeInventory + 36, false)) {
                return null;
            }

            if (itemStack2.stackSize == 0) {
                slot.putStack((ItemStack) null);
            } else {
                slot.onSlotChanged();
            }

            if (itemStack2.stackSize == itemStack1.stackSize) {
                return null;
            }

            slot.onPickupFromSlot(playerIn, itemStack2);
        }

        return itemStack1;
    }
}
