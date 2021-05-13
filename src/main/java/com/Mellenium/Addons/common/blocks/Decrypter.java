package com.Mellenium.Addons.common.blocks;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.client.gui.GuiHandler2;
import com.Mellenium.Addons.common.ModBlocks;
import com.Mellenium.Addons.common.tileentity.TileDecrypter;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.gui.GuiHandler;
import com.brandon3055.draconicevolution.common.lib.References2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;

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
        return side == 0 ? bottom : (side == 1 ? top : blockIcon);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileDecrypter();
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float prx, float pry, float prz) {
        if (!world.isRemote) {
            FMLNetworkHandler.openGui(player, MelleniumAddons.instance, GuiHandler2.GUIID_DECRYPTER, world, x, y, z);
        }
        return true;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderType() {
        return super.getRenderType();
    }

    @Override
    public boolean isOpaqueCube() {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock() {
        return false;
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
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        super.randomDisplayTick(world, x, y, z, rand);

        for (int x1 = x - 2; x1 <= x + 2; ++x1) {
            for (int z1 = z - 2; z1 <= z + 2; ++z1) {
                if (x1 > x - 2 && x1 < x + 2 && z1 == z - 1) {
                    z1 = z + 2;
                }

                if (rand.nextInt(16) == 0) {
                    for (int y1 = y; y1 <= y + 1; ++y1) {
                        if (world.getBlock(x1, y1, z1) == Blocks.bookshelf) {
                            if (!world.isAirBlock((x1 - x) / 2 + x, y1, (z1 - z) / 2 + z)) {
                                break;
                            }

                            //world.spawnParticle("enchantmenttable", x + 0.5D, y + 2.0D, z + 0.5D, l - x + rand.nextFloat() - 0.5D, j1 - y - rand.nextFloat() - 1.0F, i1 - z	+ rand.nextFloat() - 0.5D);
                            world.spawnParticle("enchantmenttable", x1 + 0.4 + (rand.nextFloat() * 0.2), y1 + 0.8, z1 + 0.4 + (rand.nextFloat() * 0.2), x - x1 + rand.nextFloat() - 0.5, y - y1 + rand.nextFloat(), z - z1 + rand.nextFloat() - 0.5);

                        }
                    }
                }
            }
        }
    }
}
