package com.Mellenium.Addons.common.containers;

import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

public class ContainerDecrypter extends Container {

    private final IInventory blockInv;

    public ContainerDecrypter(InventoryPlayer playerInv, IInventory inv){
        blockInv = inv;
        addSlotToContainer(new Slot(blockInv, TileEntityDecrypter.slotEnum.INPUT_SLOT.ordinal(), 27,23));
        addSlotToContainer(new Slot(blockInv, TileEntityDecrypter.slotEnum.OUTPUT_SLOT.ordinal(), 134,23));

        // add player inventory slots
        // note that the slot numbers are within the player inventory so can
        // be same as the tile entity inventory
        int i;
        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                addSlotToContainer(new Slot(playerInv, j+i*9+9,
                        8+j*18, 60+i*18));
            }
        }

        // add hotbar slots
        for (i = 0; i < 9; ++i)
        {
            addSlotToContainer(new Slot(playerInv, i, 8 + i * 18,
                    118));
        }
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return false;
    }
}
