package com.Mellenium.Addons;

import com.Mellenium.Addons.Thaum.MelCrafts;
import com.Mellenium.Addons.Thaum.MelTab;
import com.brandon3055.draconicevolution.client.creativetab.DETab2;
import com.brandon3055.draconicevolution.common.CommonProxy2;
import com.brandon3055.draconicevolution.common.lib.OreDoublingRegistry;
import com.brandon3055.draconicevolution.common.lib.References2;
import com.brandon3055.draconicevolution.common.utills.LogHelper;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;

@Mod(modid = References2.MODID, name = References2.MODNAME, version = References2.VERSION, canBeDeactivated = false, guiFactory = References2.GUIFACTORY, dependencies = "after:NotEnoughItems;" +
        "after:NotEnoughItems;" +
        "after:ThermalExpansion;" +
        "after:ThermalFoundation;" +
        "required-after:BrandonsCore@[1.0.0.11,);")
public class MelleniumAddons {

    @Mod.Instance(References2.MODID)
    public static MelleniumAddons instance;

    @SidedProxy(clientSide = References2.CLIENTPROXYLOCATION, serverSide = References2.SERVERPROXYLOCATION)
    public static CommonProxy2 proxy;

    public static CreativeTabs tabMelleniumAds = new DETab2(CreativeTabs.getNextID(), References2.MODID, "tabMelleniumAds", 0);

    public static SimpleNetworkWrapper network;

    public static boolean debug = false;


    public MelleniumAddons() {
        LogHelper.info("Hello Minecraft!!!");
    }

    @Mod.EventHandler
    public static void preInit(final FMLPreInitializationEvent event) {
        if (debug) LogHelper.info("Initialization");

        proxy.preInit(event);

    }

    @Mod.EventHandler
    public void init(final FMLInitializationEvent event) {
        if (debug) System.out.println("init()");
        proxy.init(event);

        //	===

        //	===
    }

    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent event) {
        if (debug) System.out.println("postInit()");
        MelTab.init();
        MelCrafts.init();
        proxy.postInit(event);
    }

    //FMLInterModComms.sendMessage("DraconicEvolution", "addChestRecipe:item.coal", new ItemStack(Items.diamond, 2));
    @Mod.EventHandler
    public void processMessage(FMLInterModComms.IMCEvent event) {
        for (FMLInterModComms.IMCMessage m : event.getMessages()) {
            LogHelper.info(m.key);
            if (m.isItemStackMessage() && m.key.contains("addChestRecipe:")) {
                String s = m.key.substring(m.key.indexOf("addChestRecipe:") + 15);
                OreDoublingRegistry.resultOverrides.put(s, m.getItemStackValue());
                LogHelper.info("Added Chest recipe override: " + s + " to " + m.getItemStackValue());
            }
        }
    }
}