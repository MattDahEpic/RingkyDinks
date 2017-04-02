package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public abstract class IDinkAbility {
    //ability
    public abstract boolean hasUseAbility ();
    abstract void enable (EntityPlayer player, ItemStack stack);
    public abstract void disable (EntityPlayer player, ItemStack stack);
    abstract void tick (EntityPlayer player, ItemStack stack);
    abstract void onClick (EntityPlayer player, ItemStack stack, EnumHand hand);
    abstract boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target, EnumHand hand);
    abstract EnumActionResult onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side, EnumHand hand);
    //item consumption
    abstract ItemStack getConsumeItem (ItemStack currentCompareItem);
    public abstract boolean constantItemConsumption ();
    public abstract boolean consumesItems ();

    /* CONSUME METHODS */
    public static void onUpdate (IDinkAbility ability, EntityPlayer player, ItemStack stack) {
        if (!player.world.isRemote) {
            if (DinkNBT.getEnabled(stack)) {
                if (consumeRequiredItemForAbility(ability, player, true)) {
                    DinkNBT.setEnabled(stack, true);
                    ability.enable(player, stack);
                    ability.tick(player, stack);
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
    public static ActionResult<ItemStack> onItemRightClick (IDinkAbility ability, EntityPlayer player, ItemStack stack, EnumHand hand) {
        if (!player.world.isRemote) {
            if (!ability.hasUseAbility()) {
                if (player.isSneaking()) {
                    DinkNBT.setEnabled(stack, !DinkNBT.getEnabled(stack)); //reverse enabled value
                    return new ActionResult(EnumActionResult.SUCCESS,stack);
                }
            } else {
                //TODO: if these abilities ever use items put check here
                ability.onClick(player,stack,hand);
                return new ActionResult(EnumActionResult.SUCCESS,stack);
            }
        }
        return new ActionResult(EnumActionResult.PASS,stack);
    }
    public static boolean itemInteractionForEntity (IDinkAbility ability, EntityPlayer player, ItemStack stack, EntityLivingBase target, EnumHand hand) {
        return ability.onEntityClick(player,stack,target,hand);
    }
    public static EnumActionResult onItemUse (IDinkAbility ability, EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side, EnumHand hand) {
        return ability.onBlockClick(player,stack,pos,side,hand);
    }
    static boolean consumeRequiredItemForAbility (IDinkAbility ability, EntityPlayer player, boolean isConstant) {
        if (RDConfig.consumeItems && ability.consumesItems() && !player.world.isRemote) { //if item consumption enabled, the ring consumes items, and on server
            if (isConstant && ability.constantItemConsumption() && player.world.getTotalWorldTime() % RDConfig.consumeInterval == 0) {
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
            if (ItemHelper.isSameIgnoreStackSize(i,ability.getConsumeItem(i),false)) {
                if (i.getCount() >= ability.getConsumeItem(i).getCount()) {
                    i.setCount(i.getCount()-ability.getConsumeItem(i).getCount());
                    player.inventory.setInventorySlotContents(index,i);
                    if (i.getCount() == 0) player.inventory.setInventorySlotContents(index, ItemStack.EMPTY); //prevent 0 item stacks
                    if (i.getItem().hasContainerItem(i)) {
                        player.inventory.addItemStackToInventory(i.getItem().getContainerItem(i)); //add back buckets for liquids and bowls for mush-stew
                    }
                    return true;
                }
            }
        }
        player.sendMessage(new TextComponentString(TextFormatting.DARK_RED + "Not enough items to fuel a ring, it has been disabled."));
        return false;
    }
}
