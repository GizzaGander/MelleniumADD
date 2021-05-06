package com.brandon3055.draconicevolution.common.items.tools;

import java.util.List;

import com.brandon3055.draconicevolution.client.render.IRenderTweak;
import com.brandon3055.draconicevolution.common.ModItems2;
import com.brandon3055.draconicevolution.common.handler.BalanceConfigHandler2;
import com.brandon3055.draconicevolution.common.items.tools.baseclasses.MiningTool2;
import com.brandon3055.draconicevolution.common.lib.References2;
import com.brandon3055.draconicevolution.common.lib.Strings2;
import com.brandon3055.draconicevolution.common.utills.IInventoryTool;
import com.brandon3055.draconicevolution.common.utills.IUpgradableItem;
import com.brandon3055.draconicevolution.common.utills.ItemConfigField;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.common.util.EnumHelper;
import org.lwjgl.opengl.GL11;

public class ChaoticPickaxe extends MiningTool2 implements IInventoryTool, IRenderTweak {


    public ChaoticPickaxe() {
        super(ModItems2.CHAOTIC);
        this.setHarvestLevel("pickaxe", 10);
        this.setUnlocalizedName(Strings2.chaoticPickaxeName);
        this.setCapacity(BalanceConfigHandler2.chaoticToolsBaseStorage);
        this.setMaxExtract(BalanceConfigHandler2.chaoticToolsMaxTransfer);
        this.setMaxReceive(BalanceConfigHandler2.chaoticToolsMaxTransfer);
        this.energyPerOperation = BalanceConfigHandler2.chaoticToolsEnergyPerAction;
        ModItems2.register(this);
    }


    @Override
    public List<ItemConfigField> getFields(ItemStack stack, int slot) {
        List<ItemConfigField> list = super.getFields(stack, slot);
        list.add(new ItemConfigField(References2.INT_ID, slot, References2.DIG_AOE).setMinMaxAndIncromente(0, EnumUpgrade.DIG_AOE.getUpgradePoints(stack), 1).readFromItem(stack, 0).setModifier("AOE"));
        list.add(new ItemConfigField(References2.INT_ID, slot, References2.DIG_DEPTH).setMinMaxAndIncromente(1, EnumUpgrade.DIG_DEPTH.getUpgradePoints(stack), 1).readFromItem(stack, 1));
        list.add(new ItemConfigField(References2.BOOLEAN_ID, slot, References2.OBLITERATE).readFromItem(stack, false));
        return list;
    }

    @Override
    public String getInventoryName() {
        return StatCollector.translateToLocal("info.de.toolInventoryOblit.txt");
    }

    @Override
    public int getInventorySlots() {
        return 9;
    }

    @Override
    public boolean isEnchantValid(Enchantment enchant) {
        return enchant.type == EnumEnchantmentType.digger;
    }

    @Override
    public void tweakRender(IItemRenderer.ItemRenderType type) {
        GL11.glTranslated(0.34, 0.69, 0.1);
        GL11.glRotatef(90, 1, 0, 0);
        GL11.glRotatef(140, 0, -1, 0);
        GL11.glRotatef(180, 0, 0, 1);
        GL11.glScaled(0.7, 0.7, 0.7);

        if (type == IItemRenderer.ItemRenderType.INVENTORY) {
            GL11.glScalef(11.8F, 11.8F, 11.8F);
            GL11.glRotatef(180, 0, 1, 0);
            GL11.glTranslated(-1.2, 0, -0.35);
        } else if (type == IItemRenderer.ItemRenderType.ENTITY) {
            GL11.glRotatef(90.5F, 0, 1, 0);
            GL11.glTranslated(0, 0, -0.9);
        }
    }

    @Override
    public int getUpgradeCap(ItemStack itemstack) {
        return BalanceConfigHandler2.chaoticToolsMaxUpgrades;
    }

    @Override
    public int getMaxTier(ItemStack itemstack) {
        return 2;
    }

    @Override
    public List<String> getUpgradeStats(ItemStack stack) {
        return super.getUpgradeStats(stack);
    }

    @Override
    public int getCapacity(ItemStack stack) {
        int points = IUpgradableItem.EnumUpgrade.RF_CAPACITY.getUpgradePoints(stack);
        return BalanceConfigHandler2.chaoticToolsBaseStorage + points * BalanceConfigHandler2.chaoticToolsStoragePerUpgrade;
    }

    @Override
    public int getMaxUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == EnumUpgrade.RF_CAPACITY.index) {
            return BalanceConfigHandler2.chaoticToolsMaxCapacityUpgradePoints;
        }
        if (upgradeIndex == EnumUpgrade.DIG_AOE.index) {
            return BalanceConfigHandler2.chaoticToolsMaxDigAOEUpgradePoints;
        }
        if (upgradeIndex == EnumUpgrade.DIG_DEPTH.index) {
            return BalanceConfigHandler2.chaoticToolsMaxDigDepthUpgradePoints;
        }
        if (upgradeIndex == EnumUpgrade.DIG_SPEED.index) {
            return BalanceConfigHandler2.chaoticToolsMaxDigSpeedUpgradePoints;
        }
        return BalanceConfigHandler2.chaoticToolsMaxUpgradePoints;
    }

    @Override
    public int getBaseUpgradePoints(int upgradeIndex) {
        if (upgradeIndex == EnumUpgrade.DIG_AOE.index) {
            return BalanceConfigHandler2.chaoticToolsMinDigAOEUpgradePoints;
        }
        if (upgradeIndex == EnumUpgrade.DIG_DEPTH.index) {
            return BalanceConfigHandler2.chaoticToolsMinDigDepthUpgradePoints;
        }
        if (upgradeIndex == EnumUpgrade.DIG_SPEED.index) {
            return BalanceConfigHandler2.chaoticToolsMinDigSpeedUpgradePoints;
        }
        return 0;
    }

//	@Override
//	public boolean isItemTool(ItemStack p_77616_1_) {
//		return true;
//	}
//
//	@SideOnly(Side.CLIENT)
//	@SuppressWarnings("unchecked")
//	@Override
//	public void getSubItems(Item item, CreativeTabs tab, List list) {
//		list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", 0));
//		list.add(ItemNBTHelper.setInteger(new ItemStack(item, 1, 0), "Energy", capacity));
//	}
//
//	@Override
//	public String getUnlocalizedName(){
//
//		return String.format("item.%s%s", References2.MODID.toLowerCase() + ":", super.getUnlocalizedName().substring(super.getUnlocalizedName().indexOf(".") + 1));
//	}
//
//	@Override
//	public String getUnlocalizedName(final ItemStack itemStack){
//		return getUnlocalizedName();
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public void registerIcons(final IIconRegister iconRegister)
//	{
//		this.itemIcon0 = iconRegister.registerIcon(References2.RESOURCESPREFIX + "draconic_pick");
//		this.itemIcon1 = iconRegister.registerIcon(References2.RESOURCESPREFIX + "draconic_pick_active");
//		this.itemIcon2 = iconRegister.registerIcon(References2.RESOURCESPREFIX + "draconic_pick_obliterate");
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IIcon getIcon(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
//	{
//		if (ItemNBTHelper.getShort(stack, "size", (short)0) > 0 && ItemNBTHelper.getBoolean(stack, "obliterate", false))
//			return itemIcon2;
//		else if (ItemNBTHelper.getShort(stack, "size", (short)0) > 0)
//			return itemIcon1;
//		else
//			return itemIcon0;
//	}
//
//	@Override
//	@SideOnly(Side.CLIENT)
//	public IIcon getIconIndex(ItemStack stack)
//	{
//		if (ItemNBTHelper.getShort(stack, "size", (short)0) > 0 && ItemNBTHelper.getBoolean(stack, "obliterate", false))
//			return itemIcon2;
//		else if (ItemNBTHelper.getShort(stack, "size", (short)0) > 0)
//			return itemIcon1;
//		else
//			return itemIcon0;
//	}
//
//	@Override
//	public boolean onBlockStartBreak(final ItemStack stack, final int x, final int y, final int z, final EntityPlayer player)
//	{
//		World world = player.worldObj;
//		Block block = world.getBlock(x, y, z);
//		Material mat = block.getMaterial();
//		if (!ToolHandler2.isRightMaterial(mat, ToolHandler2.materialsPick)) {
//			return false;
//		}
//		int fortune = EnchantmentHelper.getFortuneModifier(player);
//		boolean silk = EnchantmentHelper.getSilkTouchModifier(player);
//		ToolHandler2.disSquare(x, y, z, player, world, silk, fortune, ToolHandler2.materialsPick, stack);
//		return false;
//	}
//
//	@Override
//	public ItemStack onItemRightClick(final ItemStack stack, final World world, final EntityPlayer player)
//	{
//		return ToolHandler2.changeMode(stack, player, true, 3);
//	}
//
//	@SuppressWarnings({ "rawtypes", "unchecked" })
//	@Override
//	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean extraInformation)
//	{
//		int size = (ItemNBTHelper.getShort(stack, "size", (short) 0) * 2) + 1;
//		boolean oblit = ItemNBTHelper.getBoolean(stack, "obliterate", false);
//		if (InfoHelper.holdShiftForDetails(list)){
//			InfoHelper.addEnergyInfo(stack, list);
//
//			list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.miningMode.txt") + ": " + InfoHelper.HITC() + size + "x" + size);
//			list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.changeMiningMode.txt"));
//			list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.obliterationMode.txt") + ": " + InfoHelper.HITC() + StatCollector.translateToLocal("info.de.obliterationMode"+oblit+".txt"));
//			list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.toggleOblit.txt"));
//			list.add(InfoHelper.ITC() + StatCollector.translateToLocal("info.de.oblitInfo.txt"));
//
//			InfoHelper.addLore(stack, list);
//		}
//	}
//
private static final EnumRarity EnumRarity2 = EnumHelper.addRarity("legendary", EnumChatFormatting.GOLD, "Legendary");

    @Override
	public EnumRarity getRarity(ItemStack stack)
	{
		return EnumRarity2;
//	}
//
//	@Override
//	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {
//
//		if (container.stackTagCompound == null) {
//			container.stackTagCompound = new NBTTagCompound();
//		}
//		int energy = container.stackTagCompound.getInteger("Energy");
//		int energyReceived = Math.min(capacity - energy, Math.min(this.maxReceive, maxReceive));
//
//		if (!simulate) {
//			energy += energyReceived;
//			container.stackTagCompound.setInteger("Energy", energy);
//		}
//		return energyReceived;
//	}
//
//	@Override
//	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {
//
//		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
//			return 0;
//		}
//		int energy = container.stackTagCompound.getInteger("Energy");
//		int energyExtracted = Math.min(energy, Math.min(this.maxExtract, maxExtract));
//
//		if (!simulate) {
//			energy -= energyExtracted;
//			container.stackTagCompound.setInteger("Energy", energy);
//		}
//		return energyExtracted;
//	}
//
//	@Override
//	public int getEnergyStored(ItemStack container) {
//		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
//			return 0;
//		}
//		return container.stackTagCompound.getInteger("Energy");
//	}
//
//	@Override
//	public int getMaxEnergyStored(ItemStack container) {
//		return capacity;
//	}
//
//	@Override
//	public boolean showDurabilityBar(ItemStack stack) {
//		return !(getEnergyStored(stack) == getMaxEnergyStored(stack));
//	}
//
//	@Override
//	public double getDurabilityForDisplay(ItemStack stack) {
//		return 1D - ((double)getEnergyStored(stack) / (double)getMaxEnergyStored(stack));
//	}
//
//	@Override
//	public float getDigSpeed(ItemStack stack, Block block, int meta) {
//		if ((stack.getItem() instanceof IEnergyContainerItem) && ((IEnergyContainerItem)stack.getItem()).getEnergyStored(stack) >= energyPerOperation)
//			return super.getDigSpeed(stack, block, meta);
//		else
//			return 1F;
//	}
//
//	@Override
//	public boolean hasCustomEntity(ItemStack stack) {
//		return true;
//	}
//
//	@Override
//	public Entity createEntity(World world, Entity location, ItemStack itemstack) {
//		return new EntityPersistentItem(world, location, itemstack);
//	}
}
