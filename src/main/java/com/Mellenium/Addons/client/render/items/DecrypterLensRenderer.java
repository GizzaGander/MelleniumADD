package com.Mellenium.Addons.client.render.items;

import com.Mellenium.Addons.client.lib.UtilsFX;
import com.Mellenium.Addons.common.items.DecrypterLens;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.Iterator;

public class DecrypterLensRenderer implements IItemRenderer {

    private IModelCustom model;
    private static final ResourceLocation LENSES = new ResourceLocation("melads", "models/lenses.obj");



    public DecrypterLensRenderer() {
        this.model = AdvancedModelLoader.loadModel(LENSES);
    }
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    public void renderItem(ItemRenderType type, ItemStack item, Object ... data) {
        Minecraft mc = Minecraft.getMinecraft();
        int rve_id = 0;
        int player_id = 0;
        if(type == ItemRenderType.EQUIPPED) {
            rve_id = mc.renderViewEntity.getEntityId();
            player_id = ((EntityLivingBase)data[1]).getEntityId();
        }

        EntityClientPlayerMP playermp = mc.thePlayer;
        float par1 = UtilsFX.getTimer(mc).renderPartialTicks;
        float var7 = 0.8F;
        EntityPlayerSP playersp = (EntityPlayerSP)playermp;
        GL11.glPushMatrix();
        int sw;
        int scale;
        if(type == ItemRenderType.EQUIPPED_FIRST_PERSON && player_id == rve_id && mc.gameSettings.thirdPersonView == 0) {
            GL11.glTranslatef(1.0F, 0.75F, -1.0F);
            GL11.glRotatef(135.0F, 0.0F, -1.0F, 0.0F);
            float j = playersp.prevRenderArmPitch + (playersp.renderArmPitch - playersp.prevRenderArmPitch) * par1;
            float k = playersp.prevRenderArmYaw + (playersp.renderArmYaw - playersp.prevRenderArmYaw) * par1;
            GL11.glRotatef((playermp.rotationPitch - j) * 0.1F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef((playermp.rotationYaw - k) * 0.1F, 0.0F, 1.0F, 0.0F);
            float var10000 = playermp.prevRotationPitch + (playermp.rotationPitch - playermp.prevRotationPitch) * par1;
            float text = UtilsFX.getPrevEquippedProgress(mc.entityRenderer.itemRenderer) + (UtilsFX.getEquippedProgress(mc.entityRenderer.itemRenderer) - UtilsFX.getPrevEquippedProgress(mc.entityRenderer.itemRenderer)) * par1;
            GL11.glTranslatef(-0.7F * var7, -(-0.65F * var7) + (1.0F - text) * 1.5F, 0.9F * var7);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(0.0F, 0.0F * var7, -0.9F * var7);
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);
            GL11.glEnable('\u803a');
            GL11.glPushMatrix();
            GL11.glScalef(5.0F, 5.0F, 5.0F);
            mc.renderEngine.bindTexture(mc.thePlayer.getLocationSkin());

            for(sw = 0; sw < 2; ++sw) {
                scale = sw * 2 - 1;
                GL11.glPushMatrix();
                GL11.glTranslatef(-0.0F, -0.6F, 1.1F * (float)scale);
                GL11.glRotatef((float)(-45 * scale), 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(59.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef((float)(-65 * scale), 0.0F, 1.0F, 0.0F);
                Render stack = RenderManager.instance.getEntityRenderObject(mc.thePlayer);
                RenderPlayer aa = (RenderPlayer)stack;
                float baseX = 1.0F;
                GL11.glScalef(baseX, baseX, baseX);
                aa.renderFirstPersonArm(mc.thePlayer);
                GL11.glPopMatrix();
            }

            GL11.glPopMatrix();
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(0.4F, -0.4F, 0.0F);
            GL11.glEnable('\u803a');
            GL11.glScalef(2.0F, 2.0F, 2.0F);
        } else {
            GL11.glScalef(0.5F, 0.5F, 0.5F);
            if(type == ItemRenderType.EQUIPPED) {
                GL11.glTranslatef(1.6F, 0.3F, 2.0F);
                GL11.glRotatef(90.0F, -1.0F, 0.0F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 0.0F, -1.0F);
            } else if(type == ItemRenderType.INVENTORY) {
                GL11.glRotatef(60.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(30.0F, 0.0F, 0.0F, -1.0F);
                GL11.glRotatef(248.0F, 0.0F, -1.0F, 0.0F);
            }
        }

        UtilsFX.bindTexture("models/lenses.png");
        this.model.renderAll();
        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.11F, 0.0F);
        GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
        GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
        UtilsFX.renderQuadCenteredFromTexture("models/" + DecrypterLens.names[item.getItemDamage()] + ".png", 2.5F, 1.0F, 1.0F, 1.0F, (int)(190.0F + MathHelper.sin((float)(playermp.ticksExisted - playermp.worldObj.rand.nextInt(2))) * 10.0F + 10.0F), 771, 1.0F);
        if(playermp instanceof EntityPlayer && type == ItemRenderType.EQUIPPED_FIRST_PERSON && player_id == rve_id && mc.gameSettings.thirdPersonView == 0) {
            RenderHelper.disableStandardItemLighting();
            int var28 = (int)(190.0F + MathHelper.sin((float)(playermp.ticksExisted - playermp.worldObj.rand.nextInt(2))) * 10.0F + 10.0F);
            int var29 = var28 % 65536;
            int l = var28 / 65536;
            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var29 / 1.0F, (float)l / 1.0F);
            ScanResult scan = this.doScan(playermp.inventory.getCurrentItem(), playermp);
            if(scan != null) {
                AspectList aspects = null;
                GL11.glTranslatef(0.0F, 0.0F, -0.01F);
                String var30 = "?";
                ItemStack var31 = null;
                if(scan.id > 0) {
                    var31 = new ItemStack(Item.getItemById(scan.id), 1, scan.meta);
                    if(ScanManager.hasBeenScanned(playermp, scan)) {
                        aspects = ScanManager.getScanAspects(scan, playermp.worldObj);
                    }
                }

                if(scan.type == 2) {
                    if(scan.entity instanceof EntityItem) {
                        var31 = ((EntityItem)scan.entity).getEntityItem();
                    } else {
                        var30 = scan.entity.getCommandSenderName();
                    }

                    if(ScanManager.hasBeenScanned(playermp, scan)) {
                        aspects = ScanManager.getScanAspects(scan, playermp.worldObj);
                    }
                }

                int var37;
                if(scan.type == 3 && scan.phenomena.startsWith("NODE") && ScanManager.hasBeenScanned(playermp, scan)) {
                    MovingObjectPosition var32 = null;
                    if(var31 != null && var31.getItem() != null) {
                        var32 = EntityUtils.getMovingObjectPositionFromPlayer(playermp.worldObj, playermp, true);
                    }

                    if(var32 != null && var32.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                        TileEntity var33 = playermp.worldObj.getTileEntity(var32.blockX, var32.blockY, var32.blockZ);
                        if(var33 != null && var33 instanceof INode) {
                            aspects = ((INode)var33).getAspects();
                            GL11.glPushMatrix();
                            GL11.glEnable(3042);
                            GL11.glBlendFunc(770, 1);
                            String var35 = StatCollector.translateToLocal("nodetype." + ((INode)var33).getNodeType() + ".name");
                            if(((INode)var33).getNodeModifier() != null) {
                                var35 = var35 + ", " + StatCollector.translateToLocal("nodemod." + ((INode)var33).getNodeModifier() + ".name");
                            }

                            var37 = mc.fontRenderer.getStringWidth(var35);
                            float arr$ = 0.004F;
                            GL11.glScalef(arr$, arr$, arr$);
                            mc.fontRenderer.drawString(var35, -var37 / 2, -40, 15642134);
                            GL11.glDisable(3042);
                            GL11.glPopMatrix();
                        }
                    }
                }

                if(var31 != null) {
                    if(var31.getItem() != null) {
                        try {
                            var30 = var31.getDisplayName();
                        } catch (Exception var27) {
                            ;
                        }
                    } else if(var31.getItem() != null) {
                        try {
                            var30 = var31.getItem().getItemStackDisplayName(var31);
                        } catch (Exception var26) {
                            ;
                        }
                    }
                }

                if(aspects != null) {
                    sw = 0;
                    scale = 0;
                    int var36 = aspects.size();
                    var37 = Math.min(5, var36) * 8;
                    Aspect[] var38 = aspects.getAspectsSorted();
                    int len$ = var38.length;

                    for(int i$ = 0; i$ < len$; ++i$) {
                        Aspect aspect = var38[i$];
                        GL11.glPushMatrix();
                        GL11.glScalef(0.0075F, 0.0075F, 0.0075F);
                        var28 = (int)(190.0F + MathHelper.sin((float)(sw + playermp.ticksExisted - playermp.worldObj.rand.nextInt(2))) * 10.0F + 10.0F);
                        var29 = var28 % 65536;
                        l = var28 / 65536;
                        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)var29 / 1.0F, (float)l / 1.0F);
                        UtilsFX.drawTag(-var37 + sw * 16, -8 + scale * 16, aspect, (float)aspects.getAmount(aspect), 0, 0.01D, 1, 1.0F, false);
                        GL11.glPopMatrix();
                        ++sw;
                        if(sw >= 5 - scale) {
                            sw = 0;
                            ++scale;
                            var36 -= 5 - scale;
                            var37 = Math.min(5 - scale, var36) * 8;
                        }
                    }
                }

                if(var30 == null) {
                    var30 = "?";
                }

                if(var30.length() > 0) {
                    RenderHelper.disableStandardItemLighting();
                    GL11.glPushMatrix();
                    GL11.glEnable(3042);
                    GL11.glBlendFunc(770, 1);
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    sw = mc.fontRenderer.getStringWidth(var30);
                    float var34 = 0.005F;
                    if(sw > 90) {
                        var34 -= 2.5E-5F * (float)(sw - 90);
                    }

                    GL11.glScalef(var34, var34, var34);
                    mc.fontRenderer.drawString(var30, -sw / 2, 0, 16777215);
                    GL11.glDisable(3042);
                    GL11.glPopMatrix();
                }
            }

            RenderHelper.enableGUIStandardItemLighting();
        }

        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }

    private ScanResult doScan(ItemStack stack, EntityPlayer p) {
        if(stack != null && p != null) {
            Entity pointedEntity = EntityUtils.getPointedEntity(p.worldObj, p, 0.5D, 10.0D, 0.0F, true);
            if(pointedEntity != null) {
                ScanResult mop1 = new ScanResult((byte)2, 0, 0, pointedEntity, "");
                return mop1;
            } else {
                MovingObjectPosition mop = EntityUtils.getMovingObjectPositionFromPlayer(p.worldObj, p, true);
                if(mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                    Block i$ = p.worldObj.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                    TileEntity seh = p.worldObj.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                    ScanResult sr;
                    if(seh != null && seh instanceof INode) {
                        int scan2 = i$.getDamageValue(p.worldObj, mop.blockX, mop.blockY, mop.blockZ);
                        sr = new ScanResult((byte)3, Block.getIdFromBlock(i$), scan2, (Entity)null, "NODE" + ((INode)seh).getId());
                        return sr;
                    }

                    if(i$ != Blocks.air) {
                        ItemStack scan1 = i$.getPickBlock(mop, p.worldObj, mop.blockX, mop.blockY, mop.blockZ);
                        sr = null;
                        int md = p.worldObj.getBlockMetadata(mop.blockX, mop.blockY, mop.blockZ);

                        try {
                            if(scan1 == null) {
                                scan1 = BlockUtils.createStackedBlock(i$, md);
                            }
                        } catch (Exception var12) {
                            ;
                        }

                        try {
                            if(scan1 == null) {
                                sr = new ScanResult((byte)1, Block.getIdFromBlock(i$), md, (Entity)null, "");
                            } else {
                                sr = new ScanResult((byte)1, Item.getIdFromItem(scan1.getItem()), scan1.getItemDamage(), (Entity)null, "");
                            }
                        } catch (Exception var11) {
                            ;
                        }

                        return sr;
                    }
                }

                Iterator i$1 = ThaumcraftApi.scanEventhandlers.iterator();

                ScanResult scan;
                do {
                    if(!i$1.hasNext()) {
                        return null;
                    }

                    IScanEventHandler seh1 = (IScanEventHandler)i$1.next();
                    scan = seh1.scanPhenomena(stack, p.worldObj, p);
                } while(scan == null);

                return scan;
            }
        } else {
            return null;
        }
    }

}
