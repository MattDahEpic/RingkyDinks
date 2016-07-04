package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class DinkAbilityMagnet extends IDinkAbility { //Thanks EnderIO
    public boolean hasUseAbility () {return false;}
    public boolean constantItemConsumption () {return true;}
    public boolean consumesItems () {return true;}
    public void tick (EntityPlayer player, ItemStack stack) {
        if (!player.isSneaking() && !player.isSpectator()) {
            List<EntityItem> inArea = player.worldObj.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(player.posX - 6D, player.posY - 4D, player.posZ - 6D, player.posX + 6D, player.posY + 4D, player.posZ + 6D));
            for (EntityItem i : inArea) {
                double x = player.posX + 0.5D - i.posX;
                double y = player.posY + 1D - i.posY;
                double z = player.posZ + 0.5D - i.posZ;
                double distance = x * x + y * y + z * z;
                if (distance < 1.25 * 1.25) {
                    i.onCollideWithPlayer(player);
                } else {
                    double distanceSpeed = (0.035 * 4) / distance;
                    i.motionX += x * distanceSpeed;
                    if (y > 0) {
                        i.motionY = 0.12;
                    } else {
                        i.motionY += y * 0.035;
                    }
                    i.motionZ += z * distanceSpeed;
                }
            }
        }
    }
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.magnetConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@') + 1)));
        ret.stackSize = RDConfig.magnetConsumeAmount;
        return ret;
    }
    public void enable (EntityPlayer p, ItemStack s) {}
    public void disable (EntityPlayer p, ItemStack s) {}
    public void onClick (EntityPlayer p, ItemStack s,EnumHand h) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side,EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t,EnumHand h) {return false;}
}
