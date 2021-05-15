package com.Mellenium.Addons.common.blocks;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.ModBlocks;
import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import com.brandon3055.draconicevolution.common.lib.References2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;

public class Decrypter extends BlockCustomDrop {
    IIcon top;
    IIcon bottom;
    public Decrypter() {
        super(Material.iron);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        this.setBlockName(Strings2.DecrypterName);
        this.setLightLevel(0.1F);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setStepSound(soundTypeMetal);
        this.setHarvestLevel("pickaxe", 2);
        ModBlocks.register(this);
    }

    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(References2.RESOURCESPREFIX + "Decrypter_side");
        this.top = iconRegister.registerIcon(References2.RESOURCESPREFIX + "Decrypter_top");
        this.bottom = iconRegister.registerIcon(References2.RESOURCESPREFIX + "Decrypter_bottom");
    }

    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        return side == 0 ? this.bottom : (side == 1 ? this.top : this.blockIcon);
    }

    @Override
    protected boolean dropInventory() {
        return true;
    }

    @Override
    protected boolean hasCustomDropps() {
        return false;
    }

    @Override
    protected void getCustomTileEntityDrops(TileEntity te, List<ItemStack> droppes) {
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
        if(!world.isRemote){
            player.openGui(MelleniumAddons.instance, 0, world, x,y,z);
        }
        return true;
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityDecrypter();
    }
    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return super.getRenderType();
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
    }
}
