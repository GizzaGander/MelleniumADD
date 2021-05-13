package com.Mellenium.Addons.common;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.blocks.Decrypter;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlocksRegistry {

    public static final Block
            COUNTER = new Decrypter("counter", Material.rock, 5.0F, 5.0F, Block.soundTypeStone).setCreativeTab(MelleniumAddons.tabMelleniumAds);

    public static final Block[] BLOCKS = new Block[] {

            COUNTER
    };

    public static void register() {

        registerBlocks(BLOCKS);
    }

    private static void registerBlocks(Block... blocks) {

        for (int i = 0; i < blocks.length; i++) {

            GameRegistry.registerBlock(blocks[i], blocks[i].getUnlocalizedName());
        }
    }
}
