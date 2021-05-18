package com.Mellenium.Addons.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.lib.Strings;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.items.relics.ItemThaumometer;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.Iterator;
import java.util.List;

public class DecrypterLens extends ItemThaumometer {
    public IIcon icon;
    ScanResult startScan = null;
    //Decrypter
    public static String[] names =
            {
                    "TerraLens",
                    "AquaLens",
                    "AerLens",
                    "IgnisLens",
            };
    protected IIcon[] icons = new IIcon[DecrypterLens.names.length];

    public DecrypterLens() {
        super();
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setUnlocalizedName(Strings.decrypterLensName);
        ModItems.register(this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icon = iconRegister.registerIcon(References2.RESOURCESPREFIX + "mellenium/DecrypterLenses/" + "blank");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (this.icons.length > damage) {
            return this.icons[damage];
        }

        return super.getIconFromDamage(damage);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
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

    public String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }
}
    //Thaumometer}