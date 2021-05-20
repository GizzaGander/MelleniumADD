package com.Mellenium.Addons.common.items.books;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.lib.Strings;

public class BookOfTerra extends BookBase {

    public BookOfTerra() {
        super();
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Strings.bookOfTerra);
        ModItems.register(this);
    }
}
