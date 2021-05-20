package com.Mellenium.Addons.common.items;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.ModItems;
import com.Mellenium.Addons.common.lib.Strings;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.nodes.INode;
import thaumcraft.api.research.IScanEventHandler;
import thaumcraft.api.research.ScanResult;
import thaumcraft.common.Thaumcraft;
import thaumcraft.common.lib.network.PacketHandler;
import thaumcraft.common.lib.network.playerdata.PacketScannedToServer;
import thaumcraft.common.lib.research.ScanManager;
import thaumcraft.common.lib.utils.BlockUtils;
import thaumcraft.common.lib.utils.EntityUtils;

import java.util.Iterator;
import java.util.List;

public class DecrypterLens extends ItemMel {
    private static final String TAG_PLAYER = "player";
    private static final String TAG_MELTAG = "meltag";

    public IIcon icon;
    //Decrypter
    public static String[] names =
            {
                    "TerraLens",
                    "AquaLens",
                    "AerLens",
                    "IgnisLens",
            };
    protected IIcon[] icons = new IIcon[DecrypterLens.names.length];
    ScanResult startScan = null;

    public DecrypterLens() {
        super();
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        this.setNoRepair();
        this.setUnlocalizedName(Strings.decrypterLensName);
        ModItems.register(this);
    }

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int index, boolean isCurr)
    {
        String pname = getNBTTag(stack, TAG_PLAYER);
        if (pname.equals("-")){
            setNBTData(stack, (EntityPlayer) entity);
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister) {
        this.icon = iconRegister.registerIcon(References2.RESOURCESPREFIX + "mellenium/DecrypterLenses/" + "blank");
    }

    @Override
    public IIcon getIconFromDamage(int damage) {
        if (this.icons.length > damage) {
            return this.icons[damage];
        }

        return super.getIconFromDamage(damage);
    }

    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.epic;
    }

    @Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
        for (int i = 0; i < DecrypterLens.names.length; i++) {
            par3List.add(new ItemStack(par1, 1, i));
        }
    }

    @Override
    public String getUnlocalizedName(ItemStack par1ItemStack) {
        if (this.icons.length > par1ItemStack.getItemDamage()) {
            return "item." + DecrypterLens.names[par1ItemStack.getItemDamage()];
        }

        return "unnamed";
    }

    @Override
    public int getMetadata(int par1) {
        return par1;
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extended) {
        list.add(I18n.format("text." + DecrypterLens.names[stack.getItemDamage()]));
        String name = getPlayerName(stack);
        list.add(EnumChatFormatting.RED + "Владелец: " + getNBTTag(stack, TAG_PLAYER));
    }

    //Personalize
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p) {
        String pname = getNBTTag(stack, TAG_PLAYER);
        if (pname.equals(p.getCommandSenderName())){
            if(world.isRemote) {
                ScanResult scan = this.doScan(stack, world, p, 0);
                if(scan != null) {
                    this.startScan = scan;
                }
            }
            p.setItemInUse(stack, this.getMaxItemUseDuration(stack));
            return stack;
        }

        else {
            p.addChatComponentMessage(new ChatComponentText(EnumChatFormatting.RED + "Данный предмет не ваш."));
        }
        return stack;
    }

    private String getPlayerName(ItemStack par1ItemStack) {
        return null;
    }
    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player){
        setNBTData(stack, player);
    }

    private void setNBTData(ItemStack stack, EntityPlayer player) {
        NBTTagCompound data = new NBTTagCompound();
        data.setString(TAG_PLAYER, player.getDisplayName());
        stack.setTagInfo(TAG_MELTAG, data);
    }

    private static String getNBTTag(ItemStack stack, String tag) {
        if (stack.getTagCompound() != null) {
            NBTTagCompound data = stack.getTagCompound().getCompoundTag(TAG_MELTAG);
            return data.getString(tag);
        } else {
            return "-";
        }
    }
    //Thaumometer
    public int getMaxItemUseDuration(ItemStack itemstack) {
        return 25;
    }

    public EnumAction getItemUseAction(ItemStack itemstack) {
        return EnumAction.none;
    }

    private ScanResult doScan(ItemStack stack, World world, EntityPlayer p, int count) {
        Entity pointedEntity = EntityUtils.getPointedEntity(p.worldObj, p, 0.5D, 10.0D, 0.0F, true);
        if(pointedEntity != null) {
            ScanResult mop1 = new ScanResult((byte)2, 0, 0, pointedEntity, "");
            if(ScanManager.isValidScanTarget(p, mop1, "@")) {
                Thaumcraft.proxy.blockRunes(world, pointedEntity.posX - 0.5D, pointedEntity.posY + (double)(pointedEntity.getEyeHeight() / 2.0F), pointedEntity.posZ - 0.5D, 0.3F + world.rand.nextFloat() * 0.7F, 0.0F, 0.3F + world.rand.nextFloat() * 0.7F, (int)(pointedEntity.height * 15.0F), 0.03F);
                return mop1;
            } else {
                return null;
            }
        } else {
            MovingObjectPosition mop = this.getMovingObjectPositionFromPlayer(p.worldObj, p, true);
            if(mop != null && mop.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
                TileEntity i$ = world.getTileEntity(mop.blockX, mop.blockY, mop.blockZ);
                if(i$ instanceof INode) {
                    ScanResult seh2 = new ScanResult((byte)3, 0, 0, (Entity)null, "NODE" + ((INode)i$).getId());
                    if(ScanManager.isValidScanTarget(p, seh2, "@")) {
                        Thaumcraft.proxy.blockRunes(world, (double)mop.blockX, (double)mop.blockY + 0.25D, (double)mop.blockZ, 0.3F + world.rand.nextFloat() * 0.7F, 0.0F, 0.3F + world.rand.nextFloat() * 0.7F, 15, 0.03F);
                        return seh2;
                    }

                    return null;
                }

                Block seh = world.getBlock(mop.blockX, mop.blockY, mop.blockZ);
                if(seh != Blocks.air) {
                    int scan1 = seh.getDamageValue(world, mop.blockX, mop.blockY, mop.blockZ);
                    ItemStack is = seh.getPickBlock(mop, p.worldObj, mop.blockX, mop.blockY, mop.blockZ);
                    ScanResult sr = null;

                    try {
                        if(is == null) {
                            is = BlockUtils.createStackedBlock(seh, scan1);
                        }
                    } catch (Exception var14) {
                        ;
                    }

                    try {
                        if(is == null) {
                            sr = new ScanResult((byte)1, Block.getIdFromBlock(seh), scan1, (Entity)null, "");
                        } else {
                            sr = new ScanResult((byte)1, Item.getIdFromItem(is.getItem()), is.getItemDamage(), (Entity)null, "");
                        }
                    } catch (Exception var13) {
                        ;
                    }

                    if(ScanManager.isValidScanTarget(p, sr, "@")) {
                        Thaumcraft.proxy.blockRunes(world, (double)mop.blockX, (double)mop.blockY + 0.25D, (double)mop.blockZ, 0.3F + world.rand.nextFloat() * 0.7F, 0.0F, 0.3F + world.rand.nextFloat() * 0.7F, 15, 0.03F);
                        return sr;
                    }

                    return null;
                }
            }

            Iterator i$1 = ThaumcraftApi.scanEventhandlers.iterator();

            ScanResult scan;
            do {
                if(!i$1.hasNext()) {
                    return null;
                }

                IScanEventHandler seh1 = (IScanEventHandler)i$1.next();
                scan = seh1.scanPhenomena(stack, world, p);
            } while(scan == null);

            return scan;
        }
    }


    public void onUsingTick(ItemStack stack, EntityPlayer p, int count) {
        if(p.worldObj.isRemote && p.getCommandSenderName() == Minecraft.getMinecraft().thePlayer.getCommandSenderName()) {
            ScanResult scan = this.doScan(stack, p.worldObj, p, count);
            if(scan != null && scan.equals(this.startScan)) {
                if(count <= 5) {
                    this.startScan = null;
                    p.stopUsingItem();
                    if(ScanManager.completeScan(p, scan, "@")) {
                        PacketHandler.INSTANCE.sendToServer(new PacketScannedToServer(scan, p, "@"));
                    }
                }

                if(count % 2 == 0) {
                    p.worldObj.playSound(p.posX, p.posY, p.posZ, "thaumcraft:cameraticks", 0.2F, 0.45F + p.worldObj.rand.nextFloat() * 0.1F, false);
                }
            } else {
                this.startScan = null;
            }
        }

    }

    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4) {
        super.onPlayerStoppedUsing(par1ItemStack, par2World, par3EntityPlayer, par4);
        this.startScan = null;
    }
}