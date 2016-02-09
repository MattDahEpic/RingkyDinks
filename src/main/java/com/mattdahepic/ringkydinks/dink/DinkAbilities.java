package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class DinkAbilities {
    public static void enable (DinkValues.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case FLIGHT:
                if (!player.capabilities.allowFlying) {
                    player.capabilities.allowFlying = true;
                    player.sendPlayerAbilities();
                }
                break;
        }
    }
    public static void disable (DinkValues.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
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
    public static void tick (DinkValues.EnumDink dinkType, EntityPlayer player) {
        if (consumeItems(dinkType,player)) {
            switch (dinkType) {
                case ANTIPOTION:
                    player.clearActivePotions();
                    break;
                case LAVAWALK:
                    if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.lava || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_lava) {
                        player.worldObj.setBlockState(player.getPosition().down(), Blocks.stone.getDefaultState());
                    }
                    break;
                case WATERWALK:
                    if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.water || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_water) {
                        player.worldObj.setBlockState(player.getPosition().down(), Blocks.ice.getDefaultState());
                    }
                    break;
                case EXTINGUISHER:
                    player.extinguish();
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
        } else {
            disable(dinkType,player);
        }
    }
    public static ItemStack onUse (DinkValues.EnumDink dinkType, EntityPlayer player, ItemStack stack) {
        /*switch (dinkType) {
            case CHEST:
                //player.openGui(RingkyDinks.instance,);
                break; //todo
            case ENDERCHEST:
                //player.openGui(RingkyDinks.instance,);
                break; //todo
        }*/
        return stack;
    }
    private static boolean consumeItems (DinkValues.EnumDink dinkType, EntityPlayer player) {
        if (RDConfig.consumeItems && player.worldObj.getTotalWorldTime() % RDConfig.consumeInterval == 0) {
            boolean canUseDink = false;
            for (ItemStack i : player.inventory.mainInventory) {
                if (i != null) {
                    switch (dinkType) {
                        case FLIGHT:
                            if (i.getItem() == Items.feather) {
                                canUseDink = removeItemsFromInventory(i, RDConfig.flightConsumeAmount);
                            }
                            break;
                        case LAVAWALK:
                            canUseDink = true;
                            break;
                        case WATERWALK:
                            canUseDink = true;
                            break;
                        case ANTIPOTION:
                            canUseDink = true;
                            break;
                        case EXTINGUISHER:
                            canUseDink = true;
                            break;
                        case MAGNET:
                            if (i.getItem() == Items.iron_ingot) {
                                canUseDink = removeItemsFromInventory(i,RDConfig.magnetConsumeAmount);
                            }
                            break;
                        case WATERBREATHING:
                            if (i.getItem() == Items.fish) {
                                canUseDink = removeItemsFromInventory(i,RDConfig.waterbreathingConsumeAmount);
                            }
                            break;
                        case NIGHTVISION:
                            if (i.getItem() == Item.getItemFromBlock(Blocks.torch)) {
                                canUseDink = removeItemsFromInventory(i,RDConfig.nightvisionConsumeAmount);
                            }
                            break;
                        case SATURATION:
                            if (i.getItem() instanceof ItemFood) {
                                canUseDink = removeItemsFromInventory(i,RDConfig.saturationConsumeAmount);
                            }
                            break;
                        case SPEED:
                            if (i.getItem() == Items.sugar) {
                                canUseDink = removeItemsFromInventory(i, RDConfig.speedConsumeAmount);
                            }
                            break;
                    }
                }
            }
            return canUseDink;
        }
        return true;
    }
    private static boolean removeItemsFromInventory (ItemStack stk, int itemsToTake) {
        if (stk.stackSize >= itemsToTake) {
            stk.stackSize = stk.stackSize-itemsToTake;
            if (stk.stackSize == 0) stk = null;
            return true;
        }
        return false;
    }
}
