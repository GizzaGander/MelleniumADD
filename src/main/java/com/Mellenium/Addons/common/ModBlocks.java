package com.Mellenium.Addons.common;

import com.Mellenium.Addons.common.blocks.BlockMel;
import com.Mellenium.Addons.common.blocks.Decrypter;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

@GameRegistry.ObjectHolder(References2.MODID)
public class ModBlocks {
public static Block decrypter;

    public static void init() {
        decrypter = new Decrypter();
    }
    public static void register(BlockMel block) {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }

    public static void register(BlockMel block, Class<? extends ItemBlock> item) {
        String name = block.getUnwrappedUnlocalizedName(block.getUnlocalizedName());
        GameRegistry.registerBlock(block, item, name.substring(name.indexOf(":") + 1));
    }

    public static void registerOther(Block block) {
        String name = block.getUnlocalizedName().substring(block.getUnlocalizedName().indexOf(".") + 1);
        GameRegistry.registerBlock(block, name.substring(name.indexOf(":") + 1));
    }
}
