package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.EnumFacing;

public abstract class IDinkAbility {
    //ability
    public abstract boolean hasUseAbility ();
    abstract void enable (EntityPlayer player, ItemStack stack);
    public abstract void disable (EntityPlayer player, ItemStack stack);
    abstract void tick (EntityPlayer player, ItemStack stack);
    abstract void onClick (EntityPlayer player, ItemStack stack);
    abstract boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target);
    abstract boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side);
    //item consumption
    abstract ItemStack getConsumeItem (ItemStack currentCompareItem);
    public abstract boolean constantItemConsumption ();
    public abstract boolean consumesItems ();

    /* CONSUME METHODS */
    public static void onUpdate (IDinkAbility ability, EntityPlayer player, ItemStack stack) {
        if (!player.worldObj.isRemote) {
            if (DinkNBT.getEnabled(stack)) {
                if (consumeRequiredItemForAbility(ability, player, true)) {
                    DinkNBT.setEnabled(stack, true);
                    ability.enable(player, stack);
                } else {
                    DinkNBT.setEnabled(stack, false);
                    ability.enable(player, stack);
                }
            } else {
                DinkNBT.setEnabled(stack, false);
                ability.disable(player, stack);
            }
        }
    }
    public static ItemStack onItemRightClick (IDinkAbility ability, EntityPlayer player, ItemStack stack) {
        if (!player.worldObj.isRemote) {
            if (!ability.hasUseAbility()) {
                if (player.isSneaking()) {
                    DinkNBT.setEnabled(stack, !DinkNBT.getEnabled(stack)); //reverse enabled value
                }
            } else {
                //TODO: if these abilities ever use items put check here
                ability.onClick(player, stack);
            }
        }
        return stack;
    }
    public static boolean itemInteractionForEntity (IDinkAbility ability, EntityPlayer player, ItemStack stack, EntityLivingBase target) {
        if (!player.worldObj.isRemote) {
            return ability.onEntityClick(player,stack,target);
        }
        return false;
    }
    public static boolean onItemUse (IDinkAbility ability, EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {
        if (!player.worldObj.isRemote) {
            return ability.onBlockClick(player,stack,pos,side);
        }
        return false;
    }
    static boolean consumeRequiredItemForAbility (IDinkAbility ability, EntityPlayer player, boolean isConstant) {
        if (RDConfig.consumeItems && ability.consumesItems() && !player.worldObj.isRemote) { //if item consumption enabled, the ring consumes items, and on server
            if (isConstant && ability.constantItemConsumption() && player.worldObj.getTotalWorldTime() % RDConfig.consumeInterval == 0) {
                return doConsume(ability,player);
            } else if (!isConstant && !ability.constantItemConsumption()) {
                return doConsume(ability,player);
            }
        }
        return true;
    }
    private static boolean doConsume (IDinkAbility ability, EntityPlayer player) {
        for (int index = 0; index < player.inventory.getSizeInventory(); index++) {
            ItemStack i = player.inventory.getStackInSlot(index);
            if (i != null && ItemHelper.isSameIgnoreStackSize(i,ability.getConsumeItem(i))) { //TODO: make ItemHelper.isSameIgnoreStackSize null-safe
                if (i.stackSize >= ability.getConsumeItem(i).stackSize) {
                    i.stackSize -= ability.getConsumeItem(i).stackSize;
                    if (i.stackSize == 0) player.inventory.setInventorySlotContents(index, null); //prevent 0 item stacks
                    if (i.getItem().hasContainerItem(i)) {
                        player.inventory.addItemStackToInventory(i.getItem().getContainerItem(i)); //add back buckets for liquids and bowls for mush-stew
                    }
                    return true;
                }
            }
        }
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_RED + "Not enough items to fuel a ring, it has been disabled."));
        return false;
    }
}
