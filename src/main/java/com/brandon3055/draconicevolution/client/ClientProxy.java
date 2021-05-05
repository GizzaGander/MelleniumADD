package com.brandon3055.draconicevolution.client;

import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.handler.ClientEventHandler;
import com.brandon3055.draconicevolution.client.handler.HudHandler;
import com.brandon3055.draconicevolution.client.handler.ParticleHandler;
import com.brandon3055.draconicevolution.client.handler.ResourceHandler;
import com.brandon3055.draconicevolution.client.keybinding.KeyBindings;
import com.brandon3055.draconicevolution.client.keybinding.KeyInputHandler;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.client.render.item.*;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyBeam;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyField;
import com.brandon3055.draconicevolution.client.render.particle.ParticleReactorBeam;
import com.brandon3055.draconicevolution.client.render.tile.*;
import com.brandon3055.draconicevolution.common.CommonProxy;
import com.brandon3055.draconicevolution.common.ModItems;
import com.brandon3055.draconicevolution.common.blocks.multiblock.IReactorPart;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.lib.References;
import com.brandon3055.draconicevolution.common.utills.UpdateChecker;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy extends CommonProxy {
    private final static boolean debug = DraconicEvolution.debug;
    public static String downloadLocation;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (debug) System.out.println("on Client side");
        super.preInit(event);

        ResourceHandler.init(event);

//		downloadLocation = event.getModConfigurationDirectory().getParentFile().getAbsolutePath() + "/mods/derspack";
//		downloadLocation = downloadLocation.replaceAll("\\\\", "/");
//
//		File rescourcePack = new File(event.getModConfigurationDirectory().getParentFile(), "/mods/derspack");
//
//
//		//File file = new File(downloadLocation);
//		if (!rescourcePack.exists()) rescourcePack.mkdir();
//
//		LogHelper.info("Downloading Images");
//
//		try {
//			URL url = new URL("http://i.imgur.com/oHRx1yQ.jpg");
//			String fileName = url.getFile();
//			//String destName = downloadLocation + fileName.substring(fileName.lastIndexOf("/"));
//			File dll = new File(rescourcePack, fileName.substring(fileName.lastIndexOf("/")));
//
//			InputStream is = url.openStream();
//			OutputStream os = new FileOutputStream(dll);
//
//			ByteStreams.copy(is, os);
//
//			is.close();
//			os.close();
//		}catch (IOException e){
//			LogHelper.info(e);
//		}
//
//
//		List defaultResourcePacks = Lists.newArrayList();
//		Field f = ReflectionHelper.findField(Minecraft.class, "defaultResourcePacks", "field_110449_ao");
//		f.setAccessible(true);
//		try {
//			defaultResourcePacks = (List)f.get(Minecraft.getMinecraft());
//			defaultResourcePacks.add(new FolderResourcePack(rescourcePack));
//			for (Object o : defaultResourcePacks){
//				if (o instanceof FolderResourcePack) LogHelper.info(((FolderResourcePack) o).getPackName());
//				if (o instanceof FileResourcePack) LogHelper.info(((FileResourcePack)o).getPackName());
//			}
//
//			f.set(Minecraft.getMinecraft(), defaultResourcePacks);
//		}
//		catch (IllegalAccessException e) {
//			e.printStackTrace();
//		}


    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
        if (ConfigHandler.enableVersionChecker) FMLCommonHandler.instance().bus().register(new UpdateChecker());
        MinecraftForge.EVENT_BUS.register(new HudHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        KeyBindings.init();
        registerRenderIDs();
        registerRendering();
        ResourceHandler.instance.tick(null);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ResourceHandler.instance.tick(null);
    }

    public void registerRendering() {
        //Item Renderers
        MinecraftForgeClient.registerItemRenderer(ModItems.chaoticBow, new RenderBow());

        if (!ConfigHandler.useOldArmorModel) {
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticHelm, new RenderArmor(ModItems.chaoticHelm));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticChest, new RenderArmor(ModItems.chaoticChest));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticLeggs, new RenderArmor(ModItems.chaoticLeggs));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticBoots, new RenderArmor(ModItems.chaoticBoots));
        }

        if (!ConfigHandler.useOldD2DToolTextures) {
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticSword, new RenderTool("models/tools/ChaoticSword.obj", "textures/models/tools/ChaoticSword.png", (IRenderTweak) ModItems.chaoticSword));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticPickaxe, new RenderTool("models/tools/ChaoticPickaxe.obj", "textures/models/tools/ChaoticPickaxe.png", (IRenderTweak) ModItems.chaoticPickaxe));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticAxe, new RenderTool("models/tools/ChaoticLumberAxe.obj", "textures/models/tools/ChaoticLumberAxe.png", (IRenderTweak) ModItems.chaoticAxe));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticShovel, new RenderTool("models/tools/ChaoticShovel.obj", "textures/models/tools/ChaoticShovel.png", (IRenderTweak) ModItems.chaoticShovel));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticDestructionStaff, new RenderTool("models/tools/ChaoticStaffOfPower.obj", "textures/models/tools/ChaoticStaffOfPower.png", (IRenderTweak) ModItems.chaoticDestructionStaff));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticHoe, new RenderTool("models/tools/ChaoticHoe.obj", "textures/models/tools/ChaoticHoe.png", (IRenderTweak) ModItems.chaoticHoe));
            MinecraftForgeClient.registerItemRenderer(ModItems.chaoticBow, new RenderBowModel(true));
        }


        //ISimpleBlockRendering

        //TileEntitySpecialRenderers

        //Entitys
    }

    public void registerRenderIDs() {
        References.idTeleporterStand = RenderingRegistry.getNextAvailableRenderId();
        References.idPortal = RenderingRegistry.getNextAvailableRenderId();
    }

    @Override
    public ParticleEnergyBeam energyBeam(World worldObj, double x, double y, double z, double tx, double ty, double tz, int powerFlow, boolean advanced, ParticleEnergyBeam oldBeam, boolean render, int beamType) {
        if (!worldObj.isRemote) return null;
        ParticleEnergyBeam beam = oldBeam;
        boolean inRange = ParticleHandler.isInRange(x, y, z, 50) || ParticleHandler.isInRange(tx, ty, tz, 50);

        if (beam == null || beam.isDead) {
            if (inRange) {
                beam = new ParticleEnergyBeam(worldObj, x, y, z, tx, ty, tz, 8, powerFlow, advanced, beamType);

                FMLClientHandler.instance().getClient().effectRenderer.addEffect(beam);
            }
        } else if (!inRange) {
            beam.setDead();
            return null;
        } else {
            beam.update(powerFlow, render);
        }
        return beam;
    }

    @Override
    public ParticleEnergyField energyField(World worldObj, double x, double y, double z, int type, boolean advanced, ParticleEnergyField oldBeam, boolean render) {
        if (!worldObj.isRemote) return null;
        ParticleEnergyField beam = oldBeam;
        boolean inRange = ParticleHandler.isInRange(x, y, z, 50);

        if (beam == null || beam.isDead) {
            if (inRange) {
                beam = new ParticleEnergyField(worldObj, x, y, z, 8, type, advanced);

                FMLClientHandler.instance().getClient().effectRenderer.addEffect(beam);
            }
        } else if (!inRange) {
            beam.setDead();
            return null;
        } else {
            beam.update(render);
        }
        return beam;
    }

    @Override
    public ParticleReactorBeam reactorBeam(TileEntity tile, ParticleReactorBeam oldBeam, boolean render) {
        if (!tile.getWorldObj().isRemote || !(tile instanceof IReactorPart)) return null;
        ParticleReactorBeam beam = oldBeam;
        boolean inRange = ParticleHandler.isInRange(tile.xCoord, tile.yCoord, tile.zCoord, 50);

        if (beam == null || beam.isDead) {
            if (inRange) {
                beam = new ParticleReactorBeam(tile);

                FMLClientHandler.instance().getClient().effectRenderer.addEffect(beam);
            }
        } else if (!inRange) {
            beam.setDead();
            return null;
        } else {
            beam.update(render);
        }
        return beam;
    }


    public boolean isOp(String paramString) {
        return Minecraft.getMinecraft().theWorld.getWorldInfo().getGameType().isCreative();
    }

    @Override
    public void spawnParticle(Object particle, int range) {
        if (particle instanceof EntityFX && ((EntityFX) particle).worldObj.isRemote)
            ParticleHandler.spawnCustomParticle((EntityFX) particle, range);
    }

    @Override
    public ISound playISound(ISound sound) {
        FMLClientHandler.instance().getClient().getSoundHandler().playSound(sound);
        return sound;
    }
}
