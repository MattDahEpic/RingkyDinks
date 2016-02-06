package com.mattdahepic.ringkydinks.dink;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;

import java.util.List;

public class DinkAbilities {
    public static void enable (RDConstants.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case FLIGHT:
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
                break;
        }
    }
    public static void disable (RDConstants.EnumDink dinkType, EntityPlayer player) {
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
        }
    }
    public static void tick (RDConstants.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case ANTIPOTION:
                player.clearActivePotions();
                break;
            case LAVAWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.lava || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_lava) {
                    player.worldObj.setBlockState(player.getPosition().down(),Blocks.stone.getDefaultState());
                }
                break;
            case WATERWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.water || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_water) {
                    player.worldObj.setBlockState(player.getPosition().down(),Blocks.ice.getDefaultState());
                }
                break;
            case EXTINGUISHER:
                player.extinguish();
                break;
            case MAGNET: //thanks EnderIO
                if (!player.isSneaking()) {
                    List<EntityItem> inArea = player.worldObj.getEntitiesWithinAABB(EntityItem.class,new AxisAlignedBB(player.posX-6D,player.posY-4D,player.posZ-6D,player.posX+6D,player.posY+4D,player.posZ+6D));
                    for (EntityItem i : inArea) {
                        double x = player.posX+0.5D-i.posX;
                        double y = player.posY+1D-i.posY;
                        double z = player.posZ+0.5D-i.posZ;
                        double distance = x*x + y*y + z*z;
                        if (distance < 1.25*1.25) {
                            i.onCollideWithPlayer(player);
                        } else {
                            double distanceSpeed = (0.035*4)/distance;
                            i.motionX += x*distanceSpeed;
                            if (y > 0) {i.motionY = 0.12;} else {i.motionY += y*0.035;}
                            i.motionZ += z*distanceSpeed;
                        }
                    }
                }
                break;
            case WATERBREATHING:
                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id,160,0,true,true));
                break;
            case NIGHTVISION:
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id,160,0,true,true));
                break;
        }
    }
    public static ItemStack onUse (RDConstants.EnumDink dinkType, EntityPlayer player, ItemStack stack) {
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
}
