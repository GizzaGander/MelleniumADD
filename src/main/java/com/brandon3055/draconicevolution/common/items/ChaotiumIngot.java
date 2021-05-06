package com.brandon3055.draconicevolution.common.items;

import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.Strings2;

public class ChaotiumIngot extends ItemDE2 {
    public ChaotiumIngot() {
        this.setUnlocalizedName(Strings2.chaotiumIngotName);
        this.setCreativeTab(DraconicEvolution.tabBlocksItems);
        ModItems2.register(this);
    }
}
