package com.brandon3055.draconicevolution.client;

import com.Mellenium.Addons.client.render.items.DecrypterLensRenderer;
import com.Mellenium.Addons.client.render.tile.RenderTileEntityDecrypter;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.tiles.TileEntityDecrypter;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.client.handler.*;
import com.brandon3055.draconicevolution.client.keybinding.KeyInputHandler;
import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.client.render.item.*;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyBeam;
import com.brandon3055.draconicevolution.client.render.particle.ParticleEnergyField;
import com.brandon3055.draconicevolution.client.render.particle.ParticleReactorBeam;
import com.brandon3055.draconicevolution.common.CommonProxy2;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.blocks.multiblock.IReactorPart;
import com.brandon3055.draconicevolution.common.handler.ConfigHandler;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.MinecraftForge;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.client.renderers.item.ItemThaumometerRenderer;
import thaumcraft.common.config.ConfigItems;

public class ClientProxy2 extends CommonProxy2 {
    private final static boolean debug = DraconicEvolution.debug;
    public static String downloadLocation;

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        if (debug) System.out.println("on Client side");
        super.preInit(event);

        ResourceHandler2.init(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);
        FMLCommonHandler.instance().bus().register(new KeyInputHandler());
        FMLCommonHandler.instance().bus().register(new ClientEventHandler());
        MinecraftForge.EVENT_BUS.register(new HudHandler());
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
        registerRenderIDs();
        registerRendering();
        ResourceHandler2.instance.tick(null);
    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
        ResourceHandler2.instance.tick(null);
    }

    public void registerRendering() {
        //Item Renderers
        MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticBow, new RenderBow2());
        MinecraftForgeClient.registerItemRenderer(ModItems.decrypterLens, new DecrypterLensRenderer());

        if (!ConfigHandler.useOldArmorModel) {
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticHelm, new RenderArmor2(ModItems2.chaoticHelm));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticChest, new RenderArmor2(ModItems2.chaoticChest));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticLeggs, new RenderArmor2(ModItems2.chaoticLeggs));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticBoots, new RenderArmor2(ModItems2.chaoticBoots));
        }

        if (!ConfigHandler.useOldD2DToolTextures) {
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticSword, new RenderTool2("models/tools/ChaoticSword.obj", "textures/models/tools/ChaoticSword.png", (IRenderTweak) ModItems2.chaoticSword));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticPickaxe, new RenderTool2("models/tools/ChaoticPickaxe.obj", "textures/models/tools/ChaoticPickaxe.png", (IRenderTweak) ModItems2.chaoticPickaxe));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticAxe, new RenderTool2("models/tools/ChaoticLumberAxe.obj", "textures/models/tools/ChaoticLumberAxe.png", (IRenderTweak) ModItems2.chaoticAxe));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticShovel, new RenderTool2("models/tools/ChaoticShovel.obj", "textures/models/tools/ChaoticShovel.png", (IRenderTweak) ModItems2.chaoticShovel));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticDestructionStaff, new RenderTool2("models/tools/ChaoticStaffOfPower.obj", "textures/models/tools/ChaoticStaffOfPower.png", (IRenderTweak) ModItems2.chaoticDestructionStaff));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticHoe, new RenderTool2("models/tools/ChaoticHoe.obj", "textures/models/tools/ChaoticHoe.png", (IRenderTweak) ModItems2.chaoticHoe));
            MinecraftForgeClient.registerItemRenderer(ModItems2.chaoticBow, new RenderBowModel2(true));
        }
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDecrypter.class, new RenderTileEntityDecrypter());

    }

    public void registerRenderIDs() {}

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
