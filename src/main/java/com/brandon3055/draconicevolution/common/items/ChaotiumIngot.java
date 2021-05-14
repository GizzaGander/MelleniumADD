package com.brandon3055.draconicevolution.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ChaotiumIngot extends ItemDE2 {
    public ChaotiumIngot() {
        this.setUnlocalizedName(Strings2.chaotiumIngotName);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        ModItems2.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extended) {
        list.add("Hello");
    }
}
