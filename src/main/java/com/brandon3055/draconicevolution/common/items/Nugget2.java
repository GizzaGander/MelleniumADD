package com.brandon3055.draconicevolution.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.References2;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

/**
 * Created by brandon3055 on 8/10/2015.
 */
public class Nugget2 extends ItemDE2 {
    private IIcon chaotic;

    public Nugget2() {
        this.setUnlocalizedName("nugget");
        this.setHasSubtypes(true);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);

        ModItems2.register(this);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs p_150895_2_, List list) {
        list.add(new ItemStack(item, 1, 2));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + (itemStack.getItemDamage() == 0 ? ".chaotic" : ".chaotic");
    }

    @Override
    public void registerIcons(IIconRegister iconRegister) {
        chaotic = iconRegister.registerIcon(References2.RESOURCESPREFIX + "nuggetChaotic");
    }

    @Override
    public IIcon getIconFromDamage(int dmg) {
        return dmg == 0 ? chaotic : chaotic;
    }
}
