package com.brandon3055.draconicevolution.client.creativetab;

import com.brandon3055.draconicevolution.common.ModBlocks;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.draconicevolution.common.ModItems2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DETab2 extends CreativeTabs {

    private String label;
    private int tab;

    static ItemStack iconStackStaff;

    public static void initialize() {
        if (ModItems2.isEnabled(ModItems2.chaoticDestructionStaff))
            iconStackStaff = ItemNBTHelper.setInteger(new ItemStack(ModItems2.chaoticDestructionStaff), "Energy", 70000000);
        else iconStackStaff = new ItemStack(Items.stick);
    }

    public DETab2(int id, String modid, String label, int tab) {
        super(id, modid);
        this.label = label;
        this.tab = tab;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack getIconItemStack() {

        if (tab == 0) return iconStackStaff;
        else if (ModBlocks.isEnabled(ModBlocks.energyInfuser)) return new ItemStack(ModBlocks.energyInfuser);
        return new ItemStack(Items.ender_eye);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return getIconItemStack().getItem();
    }

    @Override
    public String getTabLabel() {
        return this.label;
    }

}
