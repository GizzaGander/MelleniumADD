package com.Mellenium.Addons.common.blocks;

import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;

public class BlockMel extends Block {

    public BlockMel(final Material material) {
        super(material);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    public BlockMel() {
        super(Material.iron);
        this.setHardness(5F);
        this.setResistance(10.0F);
    }

    @Override
    public String getUnlocalizedName() {
        return String.format("tile.%s%s", References2.MODID.toLowerCase() + ":", getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }
    public String getUnwrappedUnlocalizedName(String unlocalizedName) {
        return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister) {
        this.blockIcon = iconRegister.registerIcon(References2.RESOURCESPREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

}
