package com.Mellenium.Addons.common.items.books;

import com.Mellenium.Addons.MelleniumAddons;
import com.Mellenium.Addons.common.items.ItemMel;
import com.brandon3055.draconicevolution.common.lib.References2;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import java.util.List;

public class BookBase extends ItemMel {
    public BookBase() {
        super();
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
    }

    //Icons
    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(final IIconRegister iconRegister) {
        this.itemIcon = iconRegister.registerIcon(References2.RESOURCESPREFIX + "mellenium/" + getUnlocalizedName().substring(12));
    }

    //Personalize
    private static final String TAG_PLAYER = "player";
    private static final String TAG_MELTAG = "meltag";

    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int index, boolean isCurr)
    {
        String pname = getNBTTag(stack, TAG_PLAYER);
        if (pname.equals("-")){
            setNBTData(stack, (EntityPlayer) entity);
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer p) {
        String pname = getNBTTag(stack, TAG_PLAYER);
        if (pname.equals(p.getCommandSenderName())){}
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
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean extended) {
        String name = getPlayerName(stack);
        list.add(EnumChatFormatting.RED + "Владелец: " + getNBTTag(stack, TAG_PLAYER));
    }
}
