package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class DinkAbilitySaturation extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return true;}
    public boolean consumesItems () {return true;}
    public void tick (EntityPlayer player, ItemStack stack) {
        player.addPotionEffect(new PotionEffect(MobEffects.SATURATION, 160, 0, true, true));
    }
    public void disable (EntityPlayer player, ItemStack stack) {
        player.removePotionEffect(MobEffects.SATURATION);
    }
    public ItemStack getConsumeItem (ItemStack item) {
        if (item.getItem() instanceof ItemFood) {
            ItemStack ret = item.copy();
            ret.setCount(RDConfig.saturationConsumeAmount);
            return ret;
        }
        return new ItemStack(Blocks.AIR);
    }
    public void enable (EntityPlayer p, ItemStack s) {}
    public void onClick (EntityPlayer p, ItemStack s,EnumHand h) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side, EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t,EnumHand h) {return false;}
}
