package com.Mellenium.Addons.common.items.books;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.client.gui.GuiHandlerMel;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.lib.Strings;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class BookOfTerra extends BookBase {
    public BookOfTerra() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Strings.bookOfTerra);
        ModItems.register(this);
    }

    @Override
    public ItemStack onItemRightClick(ItemStack p_77659_1_, World world, EntityPlayer player) {
        FMLNetworkHandler.openGui(player, MelleniumAddons.instance, GuiHandlerMel.GUIID_BOOKS, world, (int) player.posX, (int) player.posY, (int) player.posZ);
        return super.onItemRightClick(p_77659_1_, world, player);
    }
}
