package com.brandon3055.draconicevolution.common.items;

import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class ItemDE2 extends Item {
    public String getUnwrappedUnlocalizedName(final String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
    public static final EnumRarity EnumRarity2 = EnumHelper.addRarity("legendary", EnumChatFormatting.GOLD, "Legendary");

    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity2;
    }
    @Override
    public String getUnlocalizedName() {
        return String.format("item.%s%s", References2.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public String getUnlocalizedName(final ItemStack itemStack) {
        return getUnlocalizedName();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(References2.RESOURCESPREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public boolean hasCustomEntity(ItemStack stack) {
        return false;
    }
}
