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
        FMLNetworkHandler.openGui(player, MelleniumAddons.instance, GuiHandlerMel.GUIID_BOOKS, world, isDecripted(p_77659_1_) ? 0 : 1, (int) player.posY, (int) player.posZ);
        return super.onItemRightClick(p_77659_1_, world, player);
    }

    private final static String TAG_DECRYPTED = "isDecripted";

    public static boolean isDecripted(ItemStack stack){
        return stack.getTagCompound().getBoolean(TAG_DECRYPTED);
    }

}
