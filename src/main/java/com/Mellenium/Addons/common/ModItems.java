package com.Mellenium.Addons.common;

import com.Mellenium.Addons.common.items.DecrypterLens;
import com.Mellenium.Addons.common.items.ItemMel;
import com.Mellenium.Addons.common.items.books.BookOfTerra;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {
    public static Item decrypterLens;
    public static Item bookOfTerra;

    public static void init(){
        decrypterLens = new DecrypterLens();
        bookOfTerra = new BookOfTerra();
    }

    public static void register(final ItemMel item) {
        String name = item.getUnwrappedUnlocalizedName(item.getUnlocalizedName());
        GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
    }
}
