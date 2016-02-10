package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class DinkAbilities {
    public static void tick (DinkValues.EnumDink dink, EntityPlayer player, ItemStack stk) {
        if (DinkValues.getEnabled(stk)) { //if enabled
            if (doesDinkHaveItemsNeededToFunction(dink, player, true)) {
                enable(dink,player);
                doDinkAction(dink,player);
            } else {
                disable(dink,player);
            }
        } else {
            disable(dink,player);
        }
    }
    public static ItemStack onUse (DinkValues.EnumDink dink, EntityPlayer player, ItemStack stk) {
        if (!dink.hasUseAbility) {
            DinkValues.setEnabled(stk,!DinkValues.getEnabled(stk)); //reverse enabled value
        } else {
            if (doesDinkHaveItemsNeededToFunction(dink,player,false)) {
                switch (dink) {
                    /*
                    case CHEST:
                       player.openGui(RingkyDinks.instance,);
                       break; //todo
                    case ENDERCHEST:
                        player.openGui(RingkyDinks.instance,);
                        break; //todo
                    case CRAFTINGTABLE:
                        player.openGui(RingkyDinks.instance,);
                        break; //todo
                    case MOBDERPEARL:
                        player.openGui(RingkyDinks.instance,);
                        break; //todo
                    */
                }
            }
        }
        return stk;
    }

    private static boolean doesDinkHaveItemsNeededToFunction(DinkValues.EnumDink dink, EntityPlayer player, boolean isConstant) {
        if (RDConfig.consumeItems) {
            if (isConstant && dink.constantItemConsumption) {
                if (player.worldObj.getTotalWorldTime() % RDConfig.consumeInterval == 0) {
                    return doConsume(dink,player);
                }
            } else if (!isConstant && !dink.constantItemConsumption) {
                return doConsume(dink,player);
            }
        }
        return true;
    }
    private static boolean doConsume (DinkValues.EnumDink dink, EntityPlayer player) {
        boolean ret = false;
        for (int index = 0; index < player.inventory.getSizeInventory(); index++)  {
            ItemStack i = player.inventory.getStackInSlot(index);
            if (i != null) {
                if (isCorrectConsumeItemForDink(dink,i)) {
                    if (i.stackSize >= getConsumeAmountForDink(dink)) {
                        i.stackSize = i.stackSize - getConsumeAmountForDink(dink);
                        ret = true;
                    }
                }
                if (i.stackSize == 0) player.inventory.setInventorySlotContents(index,null);
            }
        }
        return ret;
    }
    private static boolean isCorrectConsumeItemForDink (DinkValues.EnumDink dink, ItemStack compare) {
        String fullName;
        switch (dink) {
            case FLIGHT:
                fullName = RDConfig.flightConsumeItem;
                break;
            case LAVAWALK:
                fullName = RDConfig.lavawalkConsumeItem;
                break;
            case WATERWALK:
                fullName = RDConfig.waterwalkConsumeItem;
                break;
            case ANTIPOTION:
                fullName = RDConfig.antipotionConsumeItem;
                break;
            case EXTINGUISHER:
                fullName = RDConfig.extinguisherConsumeItem;
                break;
            case MAGNET:
                fullName = RDConfig.magnetConsumeItem;
                break;
            case WATERBREATHING:
                fullName = RDConfig.waterbreathingConsumeItem;
                break;
            case NIGHTVISION:
                fullName = RDConfig.nightvisionConsumeItem;
                break;
            case SATURATION:
                return compare.getItem() instanceof ItemFood;
            case SPEED:
                fullName = RDConfig.speedConusmeItem;
                break;
            default:
                return false;
        }
        return ItemHelper.isSameIgnoreStackSize(compare, ItemHelper.getItemFromName(fullName.substring(0, fullName.indexOf('@')-1), Integer.parseInt(fullName.substring(fullName.indexOf('@')+1))));
    }
    private static int getConsumeAmountForDink (DinkValues.EnumDink dink) {
        switch (dink) {
            case FLIGHT:
                return RDConfig.flightConsumeAmount;
            case LAVAWALK:
                return RDConfig.lavawalkConsumeAmount;
            case WATERWALK:
                return RDConfig.waterwalkConsumeAmount;
            case ANTIPOTION:
                return RDConfig.antipotionConsumeAmount;
            case EXTINGUISHER:
                return RDConfig.extinguisherConsumeAmount;
            case MAGNET:
                return RDConfig.magnetConsumeAmount;
            case WATERBREATHING:
                return RDConfig.waterbreathingConsumeAmount;
            case NIGHTVISION:
                return RDConfig.nightvisionConsumeAmount;
            case SATURATION:
                return RDConfig.saturationConsumeAmount;
            case SPEED:
                return RDConfig.speedConsumeAmount;
        }
        return 0;
    }
    private static void enable (DinkValues.EnumDink dink, EntityPlayer player) {
        switch (dink) {
            case FLIGHT:
                if (!player.capabilities.allowFlying) {
                    player.capabilities.allowFlying = true;
                    player.sendPlayerAbilities();
                }
                break;
        }
    }
    public static void disable (DinkValues.EnumDink dink, EntityPlayer player) {
        switch (dink) {
            case FLIGHT:
                player.capabilities.allowFlying = false;
                player.sendPlayerAbilities();
                break;
            case WATERBREATHING:
                player.removePotionEffect(Potion.waterBreathing.id);
                break;
            case NIGHTVISION:
                player.removePotionEffect(Potion.nightVision.id);
                break;
            case SATURATION:
                player.removePotionEffect(Potion.saturation.id);
                break;
            case SPEED:
                player.removePotionEffect(Potion.moveSpeed.id);
                break;
        }
    }
    private static void doDinkAction (DinkValues.EnumDink dink, EntityPlayer player) {
        switch (dink) {
            case ANTIPOTION:
                player.clearActivePotions();
                break;
            case LAVAWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.lava || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_lava) {
                    player.worldObj.setBlockState(player.getPosition().down(), Blocks.stone.getDefaultState());
                    doesDinkHaveItemsNeededToFunction(dink,player,false);
                }
                break;
            case WATERWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.water || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_water) {
                    player.worldObj.setBlockState(player.getPosition().down(), Blocks.ice.getDefaultState());
                    doesDinkHaveItemsNeededToFunction(dink, player,false);
                }
                break;
            case EXTINGUISHER:
                player.extinguish(); //todo: make use item when extinguish
                break;
            case MAGNET: //thanks EnderIO
                if (!player.isSneaking() && !player.isSpectator()) {
                    List<EntityItem> inArea = player.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(player.posX - 6D, player.posY - 4D, player.posZ - 6D, player.posX + 6D, player.posY + 4D, player.posZ + 6D));
                    for (EntityItem i : inArea) {
                        double x = player.posX + 0.5D - i.posX;
                        double y = player.posY + 1D - i.posY;
                        double z = player.posZ + 0.5D - i.posZ;
                        double distance = x * x + y * y + z * z;
                        if (distance < 1.25 * 1.25) {
                            i.onCollideWithPlayer(player);
                        } else {
                            double distanceSpeed = (0.035 * 4) / distance;
                            i.motionX += x * distanceSpeed;
                            if (y > 0) {
                                i.motionY = 0.12;
                            } else {
                                i.motionY += y * 0.035;
                            }
                            i.motionZ += z * distanceSpeed;
                        }
                    }
                }
                break;
            case WATERBREATHING:
                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 160, 0, true, true));
                break;
            case NIGHTVISION:
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 160, 0, true, true));
                break;
            case SATURATION:
                player.addPotionEffect(new PotionEffect(Potion.saturation.id, 160, 0, true, true));
                break;
            case SPEED:
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 160, 2, true, true));
                break;
        }
    }
}
