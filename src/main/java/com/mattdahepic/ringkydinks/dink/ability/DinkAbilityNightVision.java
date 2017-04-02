package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class DinkAbilityNightVision extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return true;}
    public boolean consumesItems () {return true;}
    public void tick (EntityPlayer player, ItemStack stack) {
        player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 160, 0, true, true));
    }
    public void disable (EntityPlayer player, ItemStack stack) {
        player.removePotionEffect(MobEffects.NIGHT_VISION);
    }
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.nightvisionConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@') + 1)));
        ret.setCount(RDConfig.nightvisionConsumeAmount);
        return ret;
    }
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void onClick (EntityPlayer p, ItemStack s,EnumHand h) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side,EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t,EnumHand h) {return false;}
}
