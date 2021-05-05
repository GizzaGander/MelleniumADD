package com.brandon3055.draconicevolution.common;

import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.items.*;
import com.brandon3055.draconicevolution.common.items.armor.ChaoticArmor;
import com.brandon3055.draconicevolution.common.items.tools.*;
import com.brandon3055.draconicevolution.common.items.weapons.ChaoticBow;
import com.brandon3055.draconicevolution.common.items.weapons.ChaoticSword;
import com.brandon3055.draconicevolution.common.lib.References;
import com.brandon3055.draconicevolution.common.lib.Strings;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;

@GameRegistry.ObjectHolder(References.MODID)
public class ModItems {
    public static ArmorMaterial CHAOTIC_ARMOR = EnumHelper.addArmorMaterial("CHAOTIC_ARMOR", -1, new int[]{3, 8, 6, 3}, 30);
    public static ToolMaterial CHAOTIC = EnumHelper.addToolMaterial("CHAOTIC", 10, -1, 400.0F, 60.0F, 45);

    public static Item chaoticPickaxe;
    public static Item chaoticShovel;
    public static Item chaoticHoe;
    public static Item chaoticAxe;
    public static Item chaoticSword;
    public static Item chaoticBow;
    public static ItemArmor chaoticHelm;
    public static ItemArmor chaoticChest;
    public static ItemArmor chaoticLeggs;
    public static ItemArmor chaoticBoots;
    public static Item chaoticDestructionStaff;
    public static ItemDE chaotiumIngot;
    public static ItemDE enderArrow;

    public static ItemDE chaotiumEnergyCore;
    public static ItemDE chaotiumFluxCapacitor;
    public static ItemDE chaoticCore;

    public static ItemStack chaoticFluxCapacitor;
    public static ItemStack chaoticEnergyCore;

    public static ItemStack nuggetChaotic;
    public static ItemDE nugget;

    public static void init() {
        enderArrow = new EnderArrow();
        chaotiumIngot = new ChaotiumIngot();
        chaoticDestructionStaff = new ChaoticDistructionStaff();
        chaoticPickaxe = new ChaoticPickaxe();
        chaoticAxe = new ChaoticAxe();
        chaoticShovel = new ChaoticShovel();
        chaoticHoe = new ChaoticHoe();
        chaoticSword = new ChaoticSword();
        chaoticBow = new ChaoticBow();
        chaoticHelm = new ChaoticArmor(CHAOTIC_ARMOR, 0, Strings.chaoticHelmName);
        chaoticChest = new ChaoticArmor(CHAOTIC_ARMOR, 1, Strings.chaoticChestName);
        chaoticLeggs = new ChaoticArmor(CHAOTIC_ARMOR, 2, Strings.chaoticLeggsName);
        chaoticBoots = new ChaoticArmor(CHAOTIC_ARMOR, 3, Strings.chaoticBootsName);

        chaoticCore = new ChaoticCore();
        chaotiumEnergyCore = new ChaotiumEnergyCore();
        chaotiumFluxCapacitor = new ChaotiumFluxCapacitor();

        //Custom ItemStacks
        chaoticEnergyCore = new ItemStack(ModItems.chaotiumEnergyCore, 1, 1);
        chaoticFluxCapacitor = new ItemStack(ModItems.chaotiumFluxCapacitor, 1, 1);

        nuggetChaotic = new ItemStack(nugget, 1, 1);
    }

    public static void register(final ItemDE item) {
        String name = item.getUnwrappedUnlocalizedName(item.getUnlocalizedName());
        if (isEnabled(item)) GameRegistry.registerItem(item, name.substring(name.indexOf(":") + 1));
    }

    public static boolean isEnabled(Item item) {
        return !ConfigHandler.disabledNamesList.contains(item.getUnlocalizedName());
    }
}
