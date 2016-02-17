package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.ringkydinks.network.RDNetworkHandler;
import com.mattdahepic.ringkydinks.network.packet.PacketSetStepHeight;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class DinkAbilityUphillStepAssist extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer player, ItemStack stack) {
        player.stepHeight = 1.1F;
        RDNetworkHandler.net.sendTo(new PacketSetStepHeight.SetStepHeightMessage(1.1F),(EntityPlayerMP)player);
    }
    public void disable (EntityPlayer player, ItemStack stack) {
        player.stepHeight = 0.6F;
        RDNetworkHandler.net.sendTo(new PacketSetStepHeight.SetStepHeightMessage(0.6F),(EntityPlayerMP)player);
    }
    public ItemStack getConsumeItem (ItemStack i) {return null;}
    public void onClick (EntityPlayer player, ItemStack stack) {}
    public void tick (EntityPlayer player, ItemStack stack) {}
    public boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {return false;}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target) {return false;}
}
