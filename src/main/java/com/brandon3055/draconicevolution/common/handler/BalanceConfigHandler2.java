package com.brandon3055.draconicevolution.common.handler;

import java.io.File;

import com.brandon3055.draconicevolution.common.utills.IUpgradableItem.EnumUpgrade;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

public class BalanceConfigHandler2 {
    public static final int chaoticArmorMinShieldRecovery = 5;
    public static final int chaoticToolsMinDigAOEUpgradePoints = 2;
    public static final int chaoticToolsMaxDigAOEUpgradePoints = 4;
    public static final int chaoticToolsMinDigSpeedUpgradePoints = 5;
    public static final int chaoticToolsMaxDigSpeedUpgradePoints = 32;
    public static final int chaoticToolsMinDigDepthUpgradePoints = 1;
    public static final int chaoticToolsMaxDigDepthUpgradePoints = 5;
    public static final int chaoticWeaponsMinAttackAOEUpgradePoints = 2;
    public static final int chaoticWeaponsMaxAttackAOEUpgradePoints = 5;
    public static final int chaoticWeaponsMinAttackDamageUpgradePoints = 0;
    public static final int chaoticWeaponsMaxAttackDamageUpgradePoints = 16;
    public static final int chaoticBowMinDrawSpeedUpgradePoints = 4;
    public static final int chaoticBowMaxDrawSpeedUpgradePoints = 6;
    public static final int chaoticBowMinArrowSpeedUpgradePoints = 3;
    public static final int chaoticBowMaxArrowSpeedUpgradePoints = 10;
    public static final int chaoticBowMinArrowDamageUpgradePoints = 3;
    public static final int chaoticBowMaxArrowDamageUpgradePoints = 20;
    public static final int chaoticStaffMinDigAOEUpgradePoints = 4;
    public static final int chaoticStaffMaxDigAOEUpgradePoints = 7;
    public static final int chaoticStaffMinDigDepthUpgradePoints = 9;
    public static final int chaoticStaffMaxDigDepthUpgradePoints = 13;
    public static final int chaoticStaffMinAttackAOEUpgradePoints = 4;
    public static final int chaoticStaffMaxAttackAOEUpgradePoints = 15;
    public static final int chaoticStaffMinAttackDamageUpgradePoints = 3;
    public static final int chaoticStaffMaxAttackDamageUpgradePoints = 64;
    public static int wyvernCapacitorBaseStorage = 80000000;
    public static int wyvernCapacitorStoragePerUpgrade = 50000000;
    public static int wyvernCapacitorMaxReceive = 1000000;
    public static int wyvernCapacitorMaxExtract = 10000000;
    public static int wyvernCapacitorMaxUpgradePoints = 50;
    public static int wyvernCapacitorMaxCapacityUpgradePoints = 50;
    public static int wyvernCapacitorMaxUpgrades = 3;
    public static int chaoticArmorBaseStorage = 50000000;
    public static int chaoticArmorStoragePerUpgrade = 50000000;
    public static int chaoticArmorMaxTransfer = 1000000;
    public static int chaoticArmorEnergyPerProtectionPoint = 1000;
    public static int chaoticArmorEnergyToRemoveEffects = 5000;
    public static int chaoticToolsBaseStorage = 10000000;
    public static int chaoticToolsStoragePerUpgrade = 5000000;
    public static int chaoticToolsMaxTransfer = 500000;
    public static int chaoticToolsEnergyPerAction = 80;
    public static int chaoticCapacitorBaseStorage = 750000000;
    public static int chaoticCapacitorStoragePerUpgrade = 60000000;
    public static int chaoticCapacitorMaxReceive = 50000000;
    public static int chaoticCapacitorMaxExtract = 500000000;
    public static int chaoticWeaponsBaseStorage = 50000000;
    public static int chaoticWeaponsStoragePerUpgrade = 50000000;
    public static int chaoticWeaponsMaxTransfer = 5000000;
    public static int chaoticWeaponsEnergyPerAttack = 300;
    public static int chaoticBowEnergyPerShot = 100;
    public static int chaoticFireEnergyCostMultiptier = 40;
    public static int draconiumBlockEnergyToChange = 100000000;
    public static int draconiumBlockChargingSpeed = 10000000;
    public static int chaoticArmorMaxCapacityUpgradePoints = 50;
    public static int chaoticArmorMaxUpgrades = 9;
    public static int chaoticArmorMaxUpgradePoints = 50;
    public static int chaoticToolsMaxCapacityUpgradePoints = 50;
    public static int chaoticToolsMaxUpgrades = 9;
    public static int chaoticToolsMaxUpgradePoints = 50;
    public static int chaoticWeaponsMaxCapacityUpgradePoints = 50;
    public static int chaoticWeaponsMaxUpgrades = 9;
    public static int chaoticWeaponsMaxUpgradePoints = 50;
    public static int chaoticBowMaxCapacityUpgradePoints = 50;
    public static int chaoticBowMaxUpgrades = 9;
    public static int chaoticBowMaxUpgradePoints = 50;
    public static int chaoticStaffMaxCapacityUpgradePoints = 50;
    public static int chaoticStaffMaxUpgrades = 15;
    public static int chaoticStaffMaxUpgradePoints = 50;
    public static int chaoticCapacitorMaxUpgradePoints = 50;
    public static int chaoticCapacitorMaxCapacityUpgradePoints = 50;
    public static int chaoticCapacitorMaxUpgrades = 9;
    public static Block energyStorageStructureBlock = null;
    public static int energyStorageStructureBlockMetadata = 0;
    private static Configuration config;

    public static void init(File modConfigurationDirectory) {
        if (config == null) {
            config = new Configuration(new File(modConfigurationDirectory, "Melleniumchaotic.Balance.cfg"));
            config.load();
            config.setCategoryRequiresMcRestart("tweaks", true);
            config.setCategoryComment("tweaks.armor", "Values in this category may be replaced automatically to prevent problems");
            config.setCategoryComment("tweaks.tools", "Values in this category may be replaced automatically to prevent problems");
            config.setCategoryComment("tweaks.weapons", "Values in this category may be replaced automatically to prevent problems");
            syncConfig();
        }
    }

    private static void syncConfig() {
        chaoticArmorBaseStorage = getInteger("energy.armor", "chaotic Armor: Base energy storage (RF)", chaoticArmorBaseStorage);
        chaoticArmorStoragePerUpgrade = getInteger("energy.armor", "chaotic Armor: Additional energy storage per upgrade installed (RF)", chaoticArmorStoragePerUpgrade);
        chaoticArmorMaxTransfer = getInteger("energy.armor", "chaotic Armor: Maximum energy transfer rate (RF/t)", chaoticArmorMaxTransfer);
        chaoticArmorEnergyPerProtectionPoint = getInteger("energy.armor", "chaotic Armor: Amount of energy required to restore protection point (RF)", chaoticArmorEnergyPerProtectionPoint);
        chaoticArmorEnergyToRemoveEffects = getInteger("energy.armor", "chaotic Armor: Amount of energy required to remove negative effects (RF)", chaoticArmorEnergyToRemoveEffects);
        chaoticToolsBaseStorage = getInteger("energy.tools", "chaotic Tools: Base energy storage (RF)", chaoticToolsBaseStorage);
        chaoticToolsStoragePerUpgrade = getInteger("energy.tools", "chaotic Tools: Additional energy storage per upgrade installed (RF)", chaoticToolsStoragePerUpgrade);
        chaoticToolsMaxTransfer = getInteger("energy.tools", "chaotic Tools: Maximum energy transfer rate (RF/t)", chaoticToolsMaxTransfer);
        chaoticToolsEnergyPerAction = getInteger("energy.tools", "chaotic Tools: Amount of energy required to perform action (RF)", chaoticToolsEnergyPerAction);
        chaoticCapacitorBaseStorage = getInteger("energy.tools", "chaotic Flux Capacitor: Base energy storage (RF)", chaoticCapacitorBaseStorage);
        chaoticCapacitorStoragePerUpgrade = getInteger("energy.tools", "chaotic Flux Capacitor: Additional energy storage per upgrade installed (RF)", chaoticCapacitorStoragePerUpgrade);
        chaoticCapacitorMaxReceive = getInteger("energy.tools", "chaotic Flux Capacitor: Maximum energy reception rate (RF/t)", chaoticCapacitorMaxReceive);
        chaoticCapacitorMaxExtract = getInteger("energy.tools", "chaotic Flux Capacitor: Maximum energy extraction rate (RF/t)", chaoticCapacitorMaxExtract);
        chaoticWeaponsBaseStorage = getInteger("energy.weapons", "chaotic Weapons: Base energy storage (RF)", chaoticWeaponsBaseStorage);
        chaoticWeaponsStoragePerUpgrade = getInteger("energy.weapons", "chaotic Weapons: Additional energy storage per upgrade installed (RF)", chaoticWeaponsStoragePerUpgrade);
        chaoticWeaponsMaxTransfer = getInteger("energy.weapons", "chaotic Weapons: Maximum energy transfer rate (RF/t)", chaoticWeaponsMaxTransfer);
        chaoticWeaponsEnergyPerAttack = getInteger("energy.weapons", "chaotic Weapons: Amount of energy required to perform attack (RF)", chaoticWeaponsEnergyPerAttack);
        chaoticBowEnergyPerShot = getInteger("energy.weapons", "chaotic Bow: Amount of energy required to shoot (RF)", chaoticBowEnergyPerShot);
        chaoticFireEnergyCostMultiptier = getInteger("energy.weapons", "Arrow of chaotic Fire: Energy cost multiplier", chaoticFireEnergyCostMultiptier);
        draconiumBlockEnergyToChange = getInteger("energy.misc", "Draconium Block: Amount of energy required to charge (RF)", draconiumBlockEnergyToChange);
        draconiumBlockChargingSpeed = getInteger("energy.misc", "Draconium Block: Maximum charging speed (RF/t)", draconiumBlockChargingSpeed);
        chaoticArmorMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticArmorBaseStorage) / Math.max(chaoticArmorStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticArmorMaxUpgrades = getInteger("tweaks.armor", "chaotic Armor: Maximum amount of upgrades", chaoticArmorMaxUpgrades);
        chaoticArmorMaxUpgradePoints = getInteger("tweaks.armor", "chaotic Armor: Maximum amount of upgrade points", chaoticArmorMaxUpgradePoints, chaoticArmorMaxUpgrades, Integer.MAX_VALUE);
        chaoticArmorMaxCapacityUpgradePoints = Math.max(Math.min(chaoticArmorMaxUpgradePoints, chaoticArmorMaxCapacityUpgradePoints), 0);
        chaoticToolsMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticToolsBaseStorage) / (double) Math.max(chaoticToolsStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticToolsMaxUpgrades = getInteger("tweaks.tools", "chaotic Tools: Maximum amount of upgrades", chaoticToolsMaxUpgrades, 0, (chaoticToolsMaxDigAOEUpgradePoints - chaoticToolsMinDigAOEUpgradePoints) * EnumUpgrade.DIG_AOE.pointConversion +
                        (chaoticToolsMaxDigSpeedUpgradePoints - chaoticToolsMinDigSpeedUpgradePoints) * EnumUpgrade.DIG_SPEED.pointConversion +
                        (chaoticToolsMaxDigDepthUpgradePoints - chaoticToolsMinDigDepthUpgradePoints) * EnumUpgrade.DIG_DEPTH.pointConversion + chaoticToolsMaxCapacityUpgradePoints);
        chaoticToolsMaxUpgradePoints = getInteger("tweaks.tools", "chaotic Tools: Maximum amount of upgrade points", chaoticToolsMaxUpgradePoints, chaoticToolsMaxUpgrades, Integer.MAX_VALUE);
        chaoticToolsMaxCapacityUpgradePoints = Math.max(Math.min(chaoticToolsMaxUpgradePoints, chaoticToolsMaxCapacityUpgradePoints), 0);
        chaoticWeaponsMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticWeaponsBaseStorage) / (double) Math.max(chaoticWeaponsStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticWeaponsMaxUpgrades = getInteger("tweaks.weapons", "chaotic Weapons: Maximum amount of upgrades", chaoticWeaponsMaxUpgrades, 0, (chaoticWeaponsMaxAttackAOEUpgradePoints - chaoticWeaponsMinAttackAOEUpgradePoints) * EnumUpgrade.ATTACK_AOE.pointConversion +
                        (chaoticWeaponsMaxAttackDamageUpgradePoints - chaoticWeaponsMinAttackDamageUpgradePoints) * EnumUpgrade.ARROW_DAMAGE.pointConversion + chaoticWeaponsMaxCapacityUpgradePoints);
        chaoticWeaponsMaxUpgradePoints = getInteger("tweaks.weapons", "chaotic Weapons: Maximum amount of upgrade points", chaoticWeaponsMaxUpgradePoints, chaoticWeaponsMaxUpgrades, Integer.MAX_VALUE);
        chaoticWeaponsMaxCapacityUpgradePoints = Math.max(Math.min(chaoticWeaponsMaxUpgradePoints, chaoticWeaponsMaxCapacityUpgradePoints), 0);
        chaoticBowMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticWeaponsBaseStorage) / (double) Math.max(chaoticWeaponsStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticBowMaxUpgrades = getInteger("tweaks.weapons", "chaotic Bow: Maximum amount of upgrades", chaoticBowMaxUpgrades, 0, (chaoticBowMaxDrawSpeedUpgradePoints - chaoticBowMinDrawSpeedUpgradePoints) * EnumUpgrade.DRAW_SPEED.pointConversion +
                        (chaoticBowMaxArrowSpeedUpgradePoints - chaoticBowMinArrowSpeedUpgradePoints) * EnumUpgrade.ARROW_SPEED.pointConversion +
                        (chaoticBowMaxArrowDamageUpgradePoints - chaoticBowMinArrowDamageUpgradePoints) * EnumUpgrade.ARROW_DAMAGE.pointConversion + chaoticBowMaxCapacityUpgradePoints);
        chaoticBowMaxUpgradePoints = getInteger("tweaks.weapons", "chaotic Bow: Maximum amount of upgrade points", chaoticBowMaxUpgradePoints, chaoticBowMaxUpgrades, Integer.MAX_VALUE);
        chaoticBowMaxCapacityUpgradePoints = Math.max(Math.min(chaoticBowMaxUpgradePoints, chaoticBowMaxCapacityUpgradePoints), 0);
        chaoticStaffMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticToolsBaseStorage * 2 - chaoticWeaponsBaseStorage) / (double) Math.max(chaoticToolsStoragePerUpgrade + chaoticWeaponsStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticStaffMaxUpgrades = chaoticToolsMaxUpgrades + chaoticWeaponsMaxUpgrades;
        chaoticStaffMaxUpgradePoints = chaoticToolsMaxUpgradePoints + chaoticWeaponsMaxUpgradePoints;
        chaoticStaffMaxCapacityUpgradePoints = Math.max(Math.min(chaoticStaffMaxUpgradePoints, chaoticStaffMaxCapacityUpgradePoints), 0);
        chaoticCapacitorMaxCapacityUpgradePoints = (int) Math.floor((double) (Integer.MAX_VALUE - chaoticCapacitorBaseStorage) / (double) Math.max(chaoticCapacitorStoragePerUpgrade, 1)) * EnumUpgrade.RF_CAPACITY.pointConversion;
        chaoticCapacitorMaxUpgrades = getInteger("tweaks.tools", "chaotic Flux Capacitor: Maximum amount of upgrades", chaoticCapacitorMaxUpgrades, 0, chaoticCapacitorMaxCapacityUpgradePoints);
        chaoticCapacitorMaxUpgradePoints = getInteger("tweaks.tools", "chaotic Flux Capacitor: Maximum amount of upgrade points", chaoticCapacitorMaxUpgradePoints, chaoticCapacitorMaxUpgrades, Integer.MAX_VALUE);
        chaoticCapacitorMaxCapacityUpgradePoints = Math.max(Math.min(chaoticCapacitorMaxUpgradePoints, chaoticCapacitorMaxCapacityUpgradePoints), 0);
        if (config.hasChanged()) {
            config.save();
        }
    }

    // This method should be loaded after all mods add blocks => after pre-init
    public static void finishLoading() {
        if (config == null) {
            return;
        }
        energyStorageStructureBlock = getBlock("tweaks.machines", "Multiblock Energy Storage: Main block of structure", Blocks.redstone_block, "WARNING! Changing of this value will replace blocks of all existing Energy Storage Multiblocks!");
        energyStorageStructureBlockMetadata = getInteger("tweaks.machines", "Multiblock Energy Storage: Metadata of main block of structure", energyStorageStructureBlockMetadata, "WARNING! Changing of this value will replace blocks of all existing Energy Storage " + "Multiblocks!");
        if (config.hasChanged()) {
            config.save();
        }
    }

    private static Block getBlock(String category, String propertyName, Block defaultValue, String comment) {
        String defaultName = Block.blockRegistry.getNameForObject(defaultValue);
        Property property = config.get(category, propertyName, defaultName, comment);
        String value = property.getString();
        if (value == null || !value.contains(":")) {
            property.set(defaultName);
            return defaultValue;
        }
        String modId = value.substring(0, value.indexOf(":"));
        String name = value.substring(value.indexOf(":") + 1);
        Block block = GameRegistry.findBlock(modId, name);
        if (block == null || block instanceof ITileEntityProvider) {
            property.set(defaultName);
            return defaultValue;
        }
        return block;
    }

    private static boolean getBoolean(String category, String propertyName, boolean defaultValue) {
        return config.get(category, propertyName, defaultValue).getBoolean(defaultValue);
    }

    private static int getInteger(String categoty, String propertyName, int defaultValue) {
        return config.get(categoty, propertyName, defaultValue).getInt(defaultValue);
    }

    private static int getInteger(String categoty, String propertyName, int defaultValue, String comment) {
        return config.get(categoty, propertyName, defaultValue, comment).getInt(defaultValue);
    }

    private static int getInteger(String category, String propertyName, int defaultValue, int minValue, int maxValue) {
        Property property = config.get(category, propertyName, defaultValue, "", minValue, maxValue);
        int value = property.getInt(defaultValue);
        if (value < minValue) {
            property.set(minValue);
            return minValue;
        }
        if (value > maxValue) {
            property.set(maxValue);
            return maxValue;
        }
        return value;
    }

    private static long getLong(String category, String propertyName, long defaultValue) {
        return (long) config.get(category, propertyName, (double) defaultValue, "", 0D, (double) Long.MAX_VALUE).getDouble((double) defaultValue);
    }
}
