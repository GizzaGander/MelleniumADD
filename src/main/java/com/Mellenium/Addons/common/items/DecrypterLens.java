package com.Mellenium.Addons.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.lib.Strings;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class DecrypterLens extends ItemMel {
    public static String[] names =
            {
                    "TerraLens",
                    "AquaLens",
                    "PerditioLens",
                    "MagicLens",
            };
    protected IIcon[] icons = new IIcon[DecrypterLens.names.length];

    public DecrypterLens() {
        super();
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setUnlocalizedName(Strings.decrypterLensName);
        ModItems.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        int i = 0;

        for (String name : DecrypterLens.names) {
            this.icons[i++] = iconRegister.registerIcon(References2.RESOURCESPREFIX + "mellenium/DecrypterLenses/" + name);
        }
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (this.icons.length > damage) {
            return this.icons[damage];
        }

        return super.getIconFromDamage(damage);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity.epic;
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < DecrypterLens.names.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (this.icons.length > par1ItemStack.getItemDamage()) {
            return "item." + DecrypterLens.names[par1ItemStack.getItemDamage()];
        }

        return "unnamed";
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extended) {
        list.add(I18n.format("text." + DecrypterLens.names[stack.getItemDamage()]));
    }
}