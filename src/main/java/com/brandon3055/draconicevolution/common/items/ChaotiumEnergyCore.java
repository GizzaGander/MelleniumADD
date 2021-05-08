package com.brandon3055.draconicevolution.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraftforge.common.util.EnumHelper;

import java.util.List;

/**
 * Created by Brandon on 24/11/2014.
 */
public class ChaotiumEnergyCore extends ItemDE2 {
    IIcon[] icons = new IIcon[2];

    public ChaotiumEnergyCore() {
        this.setUnlocalizedName(Strings2.chaotiumEnergyCoreName);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setHasSubtypes(true);
        ModItems2.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity2;
    }
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons[1] = iconRegister.registerIcon(getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(new ItemStack(item, 1, 1));

    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + itemStack.getItemDamage();
    }
}
