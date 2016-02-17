package com.mattdahepic.ringkydinks.dink.ability;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class DinkAbilityEnderchest extends IDinkAbility {
    public boolean hasUseAbility () {return true;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void disable (EntityPlayer player, ItemStack stack) {}
    public ItemStack getConsumeItem (ItemStack i) {
        return null;
    }
    public void onClick (EntityPlayer player, ItemStack stack) {
        InventoryEnderChest enderChest = player.getInventoryEnderChest();
        player.displayGUIChest(enderChest);
    }
    public void tick (EntityPlayer player, ItemStack stack) {}
    public boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {return false;}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target) {return false;}
}
