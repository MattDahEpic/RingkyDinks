package com.mattdahepic.ringkydinks.dink;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

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
            case ANTIFIRE:
                player.extinguish();
                break;
        }
    }
}
