package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.PlayerHelper;
import com.mattdahepic.ringkydinks.network.RDNetworkHandler;
import com.mattdahepic.ringkydinks.network.packet.PacketSetStepHeight;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class DinkAbilityUphillStepAssist extends IDinkAbility {
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer player, ItemStack stack) {
        player.stepHeight = 1.1F;
        RDNetworkHandler.net.sendTo(new PacketSetStepHeight.SetStepHeightMessage(1.1F),PlayerHelper.getPlayerFromUsername(player.getName()));
    }
    public void disable (EntityPlayer player, ItemStack stack) {
        player.stepHeight = 0.6F;
        EntityPlayerMP playerMP = PlayerHelper.getPlayerFromUsername(player.getName());
        if (playerMP == null) return; //player has just joined server and does not have entity yet
        RDNetworkHandler.net.sendTo(new PacketSetStepHeight.SetStepHeightMessage(0.6F), playerMP);
    }
    public ItemStack getConsumeItem (ItemStack i) {return null;}
    public void onClick (EntityPlayer p, ItemStack s,EnumHand h) {}
    public void tick (EntityPlayer p, ItemStack s) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side,EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t,EnumHand h) {return false;}
}
