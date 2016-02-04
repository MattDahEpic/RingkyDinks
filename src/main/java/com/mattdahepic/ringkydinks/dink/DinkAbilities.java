package com.mattdahepic.ringkydinks.dink;

import net.minecraft.entity.player.EntityPlayer;

public class DinkAbilities {
    public static void enable (RDConstants.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case FLIGHT:
                player.capabilities.allowFlying = true;
                player.sendPlayerAbilities();
                break;
            case LAVAWALK:
                break; //todo
            case WATERWALK:
                break; //todo
        }
    }
    public static void disable (RDConstants.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case FLIGHT:
                player.capabilities.allowFlying = false;
                player.sendPlayerAbilities();
                break;
            case LAVAWALK:
                break; //todo
            case WATERWALK:
                break; //todo
        }
    }
    public static void tick (RDConstants.EnumDink dinkType, EntityPlayer player) {
        switch (dinkType) {
            case ANTIPOTION:
                player.clearActivePotions();
                break;
            case LAVAWALK:
                break; //todo
            case WATERWALK:
                break; //todo
        }
    }
}
