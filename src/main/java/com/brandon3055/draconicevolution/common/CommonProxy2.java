package com.brandon3055.draconicevolution.common;

import com.Mellenium.Addons.client.gui.GuiHandlerMel;
import com.Mellenium.Addons.common.ModItems;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.creativetab.DETab2;
import com.brandon3055.draconicevolution.client.gui.GuiHandler;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyBeam;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyField;
import com.brandon3055.draconicevolution.client.render.particle.ParticleReactorBeam;
import com.brandon3055.draconicevolution.common.achievements.Achievements;
import com.brandon3055.draconicevolution.common.entity.*;
import com.brandon3055.draconicevolution.common.handler.*;
import com.brandon3055.draconicevolution.common.lib.OreDoublingRegistry;
import com.brandon3055.draconicevolution.common.magic.PotionHandler;
import com.brandon3055.draconicevolution.common.network.*;
import com.brandon3055.draconicevolution.common.utills.LogHelper;
import com.brandon3055.draconicevolution.integration.ModHelper;
import com.brandon3055.draconicevolution.integration.computers.CCOCIntegration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.client.audio.ISound;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;

public class CommonProxy2 {

    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.init(event.getSuggestedConfigurationFile());
        BalanceConfigHandler2.init(event.getModConfigurationDirectory());
        registerEventListeners(event.getSide());
        ModItems2.init();
        ModItems.init();
        registerTileEntities();
        initializeNetwork();
        registerOres();
        LogHelper.info("Finished PreInitialization");
    }

    public void init(FMLInitializationEvent event) {
        registerGuiHandeler();
        registerEntities();
        DETab2.initialize();
        PotionHandler.init();
        CCOCIntegration.init();
        ModHelper.init();
        LogHelper.info("Finished Initialization");
    }

    public void postInit(FMLPostInitializationEvent event) {
        BalanceConfigHandler2.finishLoading();
        OreDoublingRegistry.init();
        LogHelper.info("Finished PostInitialization");
    }

    public void initializeNetwork() {
        DraconicEvolution.network = NetworkRegistry.INSTANCE.newSimpleChannel(DraconicEvolution.networkChannelName);
        DraconicEvolution.network.registerMessage(ButtonPacket.Handler.class, ButtonPacket.class, 0, Side.SERVER);
        DraconicEvolution.network.registerMessage(ParticleGenPacket.Handler.class, ParticleGenPacket.class, 1, Side.SERVER);
        DraconicEvolution.network.registerMessage(PlacedItemPacket.Handler.class, PlacedItemPacket.class, 2, Side.SERVER);
        DraconicEvolution.network.registerMessage(PlayerDetectorButtonPacket.Handler.class, PlayerDetectorButtonPacket.class, 3, Side.SERVER);
        DraconicEvolution.network.registerMessage(PlayerDetectorStringPacket.Handler.class, PlayerDetectorStringPacket.class, 4, Side.SERVER);
        DraconicEvolution.network.registerMessage(TeleporterPacket.Handler.class, TeleporterPacket.class, 5, Side.SERVER);
        DraconicEvolution.network.registerMessage(TileObjectPacket.Handler.class, TileObjectPacket.class, 6, Side.CLIENT);
        DraconicEvolution.network.registerMessage(MountUpdatePacket.Handler.class, MountUpdatePacket.class, 7, Side.CLIENT);
        DraconicEvolution.network.registerMessage(MountUpdatePacket.Handler.class, MountUpdatePacket.class, 8, Side.SERVER);
        DraconicEvolution.network.registerMessage(ItemConfigPacket.Handler.class, ItemConfigPacket.class, 9, Side.SERVER);
        DraconicEvolution.network.registerMessage(TileObjectPacket.Handler.class, TileObjectPacket.class, 10, Side.SERVER);
        DraconicEvolution.network.registerMessage(BlockUpdatePacket.Handler.class, BlockUpdatePacket.class, 11, Side.SERVER);
        DraconicEvolution.network.registerMessage(SpeedRequestPacket.Handler.class, SpeedRequestPacket.class, 12, Side.SERVER);
        DraconicEvolution.network.registerMessage(SpeedRequestPacket.Handler.class, SpeedRequestPacket.class, 13, Side.CLIENT);
        DraconicEvolution.network.registerMessage(ToolModePacket.Handler.class, ToolModePacket.class, 14, Side.SERVER);
        DraconicEvolution.network.registerMessage(GenericParticlePacket.Handler.class, GenericParticlePacket.class, 15, Side.CLIENT);
        DraconicEvolution.network.registerMessage(ShieldHitPacket.Handler.class, ShieldHitPacket.class, 16, Side.CLIENT);
        DraconicEvolution.network.registerMessage(ContributorPacket.Handler.class, ContributorPacket.class, 17, Side.CLIENT);
        DraconicEvolution.network.registerMessage(ContributorPacket.Handler.class, ContributorPacket.class, 18, Side.SERVER);

    }

    public void registerTileEntities() {
        }

    public void registerEventListeners(Side s) {
        MinecraftForge.EVENT_BUS.register(new MinecraftForgeEventHandler());
        MinecraftForge.EVENT_BUS.register(new Achievements());
        FMLCommonHandler.instance().bus().register(new Achievements());
        FMLCommonHandler.instance().bus().register(new FMLEventHandler());
    }

    public void registerGuiHandeler() {
        new GuiHandlerMel();
        new GuiHandler();
    }

    public void registerOres() {
        if (ModBlocks.isEnabled(ModBlocks.draconiumOre))
            OreDictionary.registerOre("oreDraconium", ModBlocks.draconiumOre);
        if (ModBlocks.isEnabled(ModBlocks.draconiumBlock))
            OreDictionary.registerOre("blockDraconium", new ItemStack(ModBlocks.draconiumBlock));
        if (ModBlocks.isEnabled(ModBlocks.draconicBlock))
            OreDictionary.registerOre("blockDraconiumAwakened", new ItemStack(ModBlocks.draconicBlock));
    }

    //@Callback
    public void registerEntities() {
        EntityRegistry.registerModEntity(EntityChaoticArrow2.class, "Arrow", 2, DraconicEvolution.instance, 32, 5, true);
    }

    public ParticleEnergyBeam energyBeam(World worldObj, double x, double y, double z, double tx, double ty, double tz, int powerFlow, boolean advanced, ParticleEnergyBeam oldBeam, boolean render, int beamType) {
        return null;
    }

    public ParticleEnergyField energyField(World worldObj, double x, double y, double z, int type, boolean advanced, ParticleEnergyField oldBeam, boolean render) {
        return null;
    }

    public ParticleReactorBeam reactorBeam(TileEntity tile, ParticleReactorBeam oldBeam, boolean render) {
        return null;
    }

    public void spawnParticle(Object particle, int range) {

    }

    public ISound playISound(ISound sound) {
        return null;
    }

}
