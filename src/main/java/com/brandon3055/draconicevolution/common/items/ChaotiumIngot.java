package com.brandon3055.draconicevolution.common.items;

import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.Strings;

public class ChaotiumIngot extends ItemDE {
    public ChaotiumIngot() {
        this.setUnlocalizedName(Strings.chaotiumIngotName);
        this.setCreativeTab(DraconicEvolution.tabBlocksItems);
        ModItems2.register(this);
    }
}
