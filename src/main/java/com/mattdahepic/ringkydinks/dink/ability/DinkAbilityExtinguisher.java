package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class DinkAbilityExtinguisher extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return true;}
    public void tick (EntityPlayer player, ItemStack stack) {
        if (player.isBurning()) {
            if (IDinkAbility.consumeRequiredItemForAbility(this,player,false)) {
                player.extinguish();
            }
        }
    }
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.extinguisherConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@') + 1)));
        ret.stackSize = RDConfig.extinguisherConsumeAmount;
        return ret;
    }
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void disable (EntityPlayer player, ItemStack stack) {}
    public void onClick (EntityPlayer player, ItemStack stack) {}
    public boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {return false;}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target) {return false;}
}
