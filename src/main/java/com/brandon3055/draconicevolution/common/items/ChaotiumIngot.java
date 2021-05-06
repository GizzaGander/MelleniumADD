package com.brandon3055.draconicevolution.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.Strings2;

public class ChaotiumIngot extends ItemDE2 {
    public ChaotiumIngot() {
        this.setUnlocalizedName(Strings2.chaotiumIngotName);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        ModItems2.register(this);
    }
}
