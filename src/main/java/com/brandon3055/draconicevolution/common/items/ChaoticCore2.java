package com.brandon3055.draconicevolution.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.draconicevolution.common.ModItems2;
import net.minecraft.item.ItemStack;

/**
 * Created by brandon3055 on 2/10/2015.
 */
public class ChaoticCore2 extends ItemDE2 {
    public ChaoticCore2() {
        this.setUnlocalizedName("chaoticCore");
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        ModItems2.register(this);
    }

    @Override
    public boolean hasEffect(ItemStack par1ItemStack, int pass) {
        return true;
    }
}
