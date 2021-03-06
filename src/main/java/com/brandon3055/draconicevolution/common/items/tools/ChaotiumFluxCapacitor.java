package com.brandon3055.draconicevolution.common.items.tools;

import java.util.ArrayList;
import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import com.Mellenium.Addons.MelleniumAddons;
import com.brandon3055.brandonscore.common.utills.InfoHelper;
import com.brandon3055.brandonscore.common.utills.ItemNBTHelper;
import com.brandon3055.brandonscore.common.utills.Utills;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler2;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.RFItemBase2;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.ToolBase2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraftforge.common.util.EnumHelper;

/**
 * Created by Brandon on 24/11/2014.
 */
public class ChaotiumFluxCapacitor extends RFItemBase2 implements IUpgradableItem {
    IIcon[] icons = new IIcon[2];

    public ChaotiumFluxCapacitor() {
        this.setUnlocalizedName(Strings2.chaotiumFluxCapacitorName);
        this.setCreativeTab(MelleniumAddons.tabMelleniumAds);
        this.setHasSubtypes(true);
        this.setMaxStackSize(1);
        ModItems2.register(this);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public EnumRarity getRarity(ItemStack stack)
    {
        return EnumRarity2;
    }
    @Override
    public void registerIcons(IIconRegister iconRegister) {
        icons[1] = iconRegister.registerIcon(getUnwrappedUnlocalizedName(super.getUnlocalizedName()) + 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIconFromDamage(int damage) {
        return icons[damage];
    }

    @SideOnly(Side.CLIENT)
    @SuppressWarnings("unchecked")
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 1), "Energy", 0));
        list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 1), "Energy", BalanceConfigHandler2.chaoticCapacitorBaseStorage));
    }

    @Override
    public String getUnlocalizedName(ItemStack itemStack) {
        return super.getUnlocalizedName(itemStack) + itemStack.getItemDamage();
    }

    @Override
    public int getCapacity(ItemStack stack) {
        int points = EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorBaseStorage + points * BalanceConfigHandler2.wyvernCapacitorStoragePerUpgrade : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorBaseStorage + points * BalanceConfigHandler2.chaoticCapacitorStoragePerUpgrade : 0;
    }

    @Override
    public int getMaxExtract(ItemStack stack) {
        return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorMaxExtract : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorMaxExtract : 0;
    }

    @Override
    public int getMaxReceive(ItemStack stack) {
        return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorMaxReceive : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorMaxReceive : 0;
    }

    @Override
    public void onUpdate(ItemStack container, World world, Entity entity, int var1, boolean b) {
        if (!(entity instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer) entity;

        int mode = ItemNBTHelper.getShort(container, "Mode", (short) 0);

        if (mode == 1 || mode == 3) { //Charge Hotbar
            for (int i = 0; i < 9; i++) {
                int max = Math.min(getEnergyStored(container), getMaxExtract(container));
                ItemStack stack = player.inventory.getStackInSlot(i);

                if (stack != null && stack.getItem() instanceof IEnergyContainerItem && stack.getItem() != ModItems2.chaotiumFluxCapacitor) {
                    IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                    extractEnergy(container, item.receiveEnergy(stack, max, false), false);
                }
            }
        }

        if (mode == 2 || mode == 3) { //Charge Armor and held item
            for (int i = mode == 3 ? 1 : 0; i < 5; i++) {
                int max = Math.min(getEnergyStored(container), getMaxExtract(container));
                ItemStack stack = player.getEquipmentInSlot(i);

                if (stack != null && stack.getItem() instanceof IEnergyContainerItem && stack.getItem() != ModItems2.chaotiumFluxCapacitor) {
                    IEnergyContainerItem item = (IEnergyContainerItem) stack.getItem();
                    extractEnergy(container, item.receiveEnergy(stack, max, false), false);
                }
            }
        }
    }

    @Override
    public boolean hasEffect(ItemStack stack, int pass) {
        return ItemNBTHelper.getShort(stack, "Mode", (short) 0) > 0;
    }

    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            int mode = ItemNBTHelper.getShort(stack, "Mode", (short) 0);
            int newMode = mode == 3 ? 0 : mode + 1;
            ItemNBTHelper.setShort(stack, "Mode", (short) newMode);
            if (world.isRemote)
                player.addChatComponentMessage(new ChatComponentTranslation(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + InfoHelper.HITC() + StatCollector.translateToLocal("info.de.capacitorMode" + ItemNBTHelper.getShort(stack, "Mode", (short) 0) + ".txt")));
        }
        return stack;
    }

    @Override
    @SideOnly(Side.CLIENT)
    @SuppressWarnings({"rawtypes", "unchecked"})
    public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean extraInformation) {
        if (InfoHelper.holdShiftForDetails(list)) {

            list.add(StatCollector.translateToLocal("info.de.changwMode.txt"));
            list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.capacitorMode.txt") + ": " + InfoHelper.HITC() + StatCollector.translateToLocal("info.de.capacitorMode" + ItemNBTHelper.getShort(stack, "Mode", (short) 0) + ".txt"));
            //InfoHelper.addLore(stack, list);
        }
        ToolBase2.holdCTRLForUpgrades(list, stack);
        InfoHelper.addEnergyInfo(stack, list);
    }

    @Override
    public boolean hasProfiles() {
        return false;
    }

    @Override
    public List<EnumUpgrade> getUpgrades(ItemStack itemstack) {
        return new ArrayList<EnumUpgrade>() {{
            add(EnumUpgrade.RF_CAPACITY);
        }};
    }


    @Override
    public int getUpgradeCap(ItemStack stack) {
        return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorMaxUpgrades : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorMaxUpgrades : 0;
    }

    @Override
    public int getMaxTier(ItemStack stack) {
        return stack.getItemDamage() == 0 ? 1 : 2;
    }

    @Override
    public int getMaxUpgradePoints(int upgradeIndex) {
        return Math.max(BalanceConfigHandler2.wyvernCapacitorMaxUpgradePoints, BalanceConfigHandler2.chaoticCapacitorMaxUpgradePoints);
    }

    @Override
    public int getMaxUpgradePoints(int upgradeIndex, ItemStack stack) {
        if (stack == null) {
            return getMaxUpgradePoints(upgradeIndex);
        }
        if (upgradeIndex == EnumUpgrade.RF_CAPACITY.index) {
            return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorMaxCapacityUpgradePoints : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorMaxCapacityUpgradePoints : getMaxUpgradePoints(upgradeIndex);
        }
        return stack.getItemDamage() == 0 ? BalanceConfigHandler2.wyvernCapacitorMaxUpgradePoints : stack.getItemDamage() == 1 ? BalanceConfigHandler2.chaoticCapacitorMaxUpgradePoints : getMaxUpgradePoints(upgradeIndex);
    }

    @Override
    public int getBaseUpgradePoints(int upgradeIndex) {
        return 0;
    }

    @Override
    public List<String> getUpgradeStats(ItemStack stack) {
        List<String> strings = new ArrayList<String>();
        strings.add(InfoHelper.ITC() + StatCollector.translateToLocal("gui.de.RFCapacity.txt") + ": " + InfoHelper.HITC() + Utills.formatNumber(getMaxEnergyStored(stack)));
        return strings;
    }
}
