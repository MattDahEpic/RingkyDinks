package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class DinkAbilityFlight extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return true;}
    public boolean consumesItems () {return true;}
    public void enable (EntityPlayer player, ItemStack stack) {
        if (!player.capabilities.allowFlying && !player.capabilities.isCreativeMode) {
            player.capabilities.allowFlying = true;
            player.sendPlayerAbilities();
        }
    }
    public void disable (EntityPlayer player, ItemStack stack) {
        if (!player.capabilities.isCreativeMode) {
            player.capabilities.allowFlying = false;
            player.sendPlayerAbilities();
        }
    }
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.flightConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@')+1)));
        ret.stackSize = RDConfig.flightConsumeAmount;
        return ret;
    }
    public void onClick (EntityPlayer p, ItemStack s, EnumHand h) {}
    public void tick (EntityPlayer p, ItemStack s) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side, EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t,EnumHand h) {return false;}
}
