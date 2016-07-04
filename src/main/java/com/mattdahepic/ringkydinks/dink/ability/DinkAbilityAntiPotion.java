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

public class DinkAbilityAntiPotion extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return true;}
    public void tick (EntityPlayer player, ItemStack stack) {
        if (!player.getActivePotionEffects().isEmpty()) { //if player has effects
            if (IDinkAbility.consumeRequiredItemForAbility(this,player,false)) {
                player.clearActivePotions();
            }
        }
    }
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.antipotionConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@') + 1)));
        ret.stackSize = RDConfig.antipotionConsumeAmount;
        return ret;
    }
    public void enable (EntityPlayer p, ItemStack s) {}
    public void disable (EntityPlayer p, ItemStack s) {}
    public void onClick (EntityPlayer p, ItemStack s, EnumHand h) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side, EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t, EnumHand h) {return false;}
}
