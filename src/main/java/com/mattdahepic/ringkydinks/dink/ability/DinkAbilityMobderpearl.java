package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BossInfo;

import java.lang.reflect.Field;

public class DinkAbilityMobderpearl extends IDinkAbility {
    private static final String TAG_MOBDERPEARL_HAS_MOB = "dink.mobderpearl.hasmob";
    private static final String TAG_MOBDERPREAL_MOB_NAME = "dink.mobderpearl.mob.name";
    private static final String TAG_MOBDERPEARL_MOB = "dink.mobderpearl.mob";

    public boolean hasUseAbility () {return true;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return true;}
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void disable (EntityPlayer player, ItemStack stack) {}
    public ItemStack getConsumeItem (ItemStack i) {
        String name = RDConfig.mobderpearlConsumeItem;
        ItemStack ret = ItemHelper.getItemFromName(name.substring(0, name.indexOf('@')), Integer.parseInt(name.substring(name.indexOf('@') + 1)));
        ret.setCount(RDConfig.mobderpearlConsumeAmount);
        return ret;
    }
    public void onClick (EntityPlayer p, ItemStack s,EnumHand h) {}
    public void tick (EntityPlayer player, ItemStack stack) {}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target,EnumHand h) {
        if (!getHasMob(stack) && h != EnumHand.OFF_HAND) {
            if (IDinkAbility.consumeRequiredItemForAbility(this,player,false)) {
                return captureMob(player,stack,target);
            }
        }
        return false;
    }
    public EnumActionResult onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side, EnumHand hand) {
        if (hand == EnumHand.MAIN_HAND && releaseMob(player,stack,pos,side)) {
            return EnumActionResult.SUCCESS;
        }
        return EnumActionResult.PASS;
    }

    private static boolean captureMob (EntityPlayer player, ItemStack stack, EntityLivingBase target) {
        if (!getHasMob(stack) && target instanceof EntityLiving && !player.world.isRemote) { //is empty and an actual living thing and you're on the server
            if (((EntityLiving)target).getAttackTarget() != null) return false; //can't be targeting anything
            for (Field f : target.getClass().getFields()) { if (f.getType().isAssignableFrom(BossInfo.class)) return false; } //can't be a boss
            if (((EntityLiving)target).getMaxHealth() > player.getMaxHealth()) return false; //can't be stronger than the player
            /* BEGIN MOB CAPTURE */
            stack.getTagCompound().setBoolean(TAG_MOBDERPEARL_HAS_MOB, true);
            stack.getTagCompound().setTag(TAG_MOBDERPEARL_MOB, target.serializeNBT());
            stack.getTagCompound().setString(TAG_MOBDERPREAL_MOB_NAME,target.hasCustomName() ? target.getCustomNameTag() : target.getName());
            player.inventory.setInventorySlotContents(player.inventory.currentItem,stack);
            target.setDead();
            return true;
        }
        return false;
    }
    private static boolean releaseMob (EntityPlayer player, ItemStack stack, BlockPos blockClicked, EnumFacing sideClicked) {
        if (getHasMob(stack)  && !player.world.isRemote) { //has mob to release and is on server
            BlockPos releasePos = blockClicked.offset(sideClicked);
            EntityLiving mob = (EntityLiving) EntityList.createEntityFromNBT(stack.getTagCompound().getCompoundTag(TAG_MOBDERPEARL_MOB), player.world);
            mob.setPosition(releasePos.getX()+0.5, releasePos.getY(), releasePos.getZ()+0.5);
            mob.motionX = 0f;
            mob.motionY = 0f;
            mob.motionZ = 0f;
            mob.fallDistance = 0f;
            mob.dimension = player.dimension;
            player.world.spawnEntity(mob);
            stack.getTagCompound().setBoolean(TAG_MOBDERPEARL_HAS_MOB, false);
            stack.getTagCompound().removeTag(TAG_MOBDERPREAL_MOB_NAME);
            stack.getTagCompound().removeTag(TAG_MOBDERPEARL_MOB);
            return true;
        }
        return false;
    }
    public static boolean getHasMob (ItemStack stack) {
        return stack.getTagCompound().getBoolean(TAG_MOBDERPEARL_HAS_MOB);
    }
    public static String getMobName (ItemStack stack) {
        return getHasMob(stack) ? stack.getTagCompound().getString(TAG_MOBDERPREAL_MOB_NAME) : null;
    }
}
