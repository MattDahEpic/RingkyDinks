package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.config.RDConfig;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.IBossDisplayData;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.*;

import java.util.List;

public class DinkAbilities {
    public static void tick (EnumDink dink, EntityPlayer player, ItemStack stk) {
        if (DinkNBT.getEnabled(stk)) { //if enabled
            if (ItemConsume.doesDinkHaveItemsNeededToFunction(dink, player, true)) {
                enable(dink,player,stk);
                doDinkAction(dink,player);
            } else {
                disable(dink,player,stk);
            }
        } else {
            disable(dink,player,stk);
        }
    }
    public static ItemStack onUse (EnumDink dink, EntityPlayer player, ItemStack stk) {
        if (!dink.hasUseAbility) {
            if (player.isSneaking()) {
                DinkNBT.setEnabled(stk, !DinkNBT.getEnabled(stk)); //reverse enabled value
            }
        } else {
            if (!player.worldObj.isRemote) { //is on server (these dinks are free to use)
                switch (dink) {
                    /*case CHEST:
                        player.openGui(RingkyDinks.instance,);
                        break; //todo*/
                    case ENDERCHEST:
                        InventoryEnderChest enderChest = player.getInventoryEnderChest();
                        player.displayGUIChest(enderChest);
                        break;
                    case CRAFTINGTABLE:
                        player.displayGui(new DinkAbilityCraftingTable.InterfaceFakeCraftingTable(player.worldObj));
                        break;
                }
            }
        }
        return stk;
    }

    public static class Mobderpearl {
        private static final String TAG_MOBDERPEARL_HAS_MOB = "dink.mobderpearl.hasmob";
        private static final String TAG_MOBDERPREAL_MOB_NAME = "dink.mobderpearl.mob.name";
        private static final String TAG_MOBDERPEARL_MOB = "dink.mobderpearl.mob";
        public static boolean captureMob (EntityPlayer player, ItemStack stack, EntityLivingBase target) {
            if (!getHasMob(stack) && target instanceof EntityLiving && !player.worldObj.isRemote) { //is empty and an actual living thing and you're on the server
                if (((EntityLiving)target).getAttackTarget() != null) return false; //can't be targeting anything
                if (target instanceof IBossDisplayData) return false; //can't be a boss
                if (((EntityLiving)target).getMaxHealth() > player.getMaxHealth()) return false; //can't be stronger than the player
                /* BEGIN MOB CAPTURE */
                stack.getTagCompound().setBoolean(TAG_MOBDERPEARL_HAS_MOB, true);
                stack.getTagCompound().setTag(TAG_MOBDERPEARL_MOB, target.serializeNBT());
                stack.getTagCompound().setString(TAG_MOBDERPREAL_MOB_NAME, target.getName());
                player.inventory.setInventorySlotContents(player.inventory.currentItem,stack);
                target.setDead();
                return true;
            }
            return false;
        }
        public static boolean releaseMob (EntityPlayer player, ItemStack stack, BlockPos blockClicked, EnumFacing sideClicked) {
            if (getHasMob(stack)  && !player.worldObj.isRemote) { //has mob to release and is on server
                BlockPos releasePos = blockClicked.offset(sideClicked);
                EntityLiving mob = (EntityLiving)EntityList.createEntityFromNBT(stack.getTagCompound().getCompoundTag(TAG_MOBDERPEARL_MOB),player.worldObj);
                mob.setPosition(releasePos.getX()+0.5, releasePos.getY(), releasePos.getZ()+0.5);
                mob.motionX = 0f;
                mob.motionY = 0f;
                mob.motionZ = 0f;
                mob.fallDistance = 0f;
                mob.dimension = player.dimension;
                player.worldObj.spawnEntityInWorld(mob);
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
    public static class ItemConsume {
        public static boolean doesDinkHaveItemsNeededToFunction(EnumDink dink, EntityPlayer player, boolean isConstant) {
            if (RDConfig.consumeItems) {
                if (isConstant && dink.constantItemConsumption) {
                    if (player.worldObj.getTotalWorldTime() % RDConfig.consumeInterval == 0) {
                        return doConsume(dink,player);
                    }
                } else if (!isConstant && !dink.constantItemConsumption) {
                    return doConsume(dink,player);
                }
            }
            return true;
        }
        private static boolean doConsume (EnumDink dink, EntityPlayer player) {
            boolean ret = false;
            if (!player.worldObj.isRemote) {
                for (int index = 0; index < player.inventory.getSizeInventory(); index++) {
                    ItemStack i = player.inventory.getStackInSlot(index);
                    if (i != null) {
                        if (isCorrectConsumeItemForDink(dink, i)) {
                            if (i.stackSize >= getConsumeAmountForDink(dink)) {
                                i.stackSize = i.stackSize - getConsumeAmountForDink(dink);
                                if (i.getItem().hasContainerItem(i)) {
                                    player.inventory.addItemStackToInventory(i.getItem().getContainerItem(i)); //todo: dupes rings for dinks which have no cost
                                }
                                ret = true;
                            }
                        }
                        if (i.stackSize == 0) player.inventory.setInventorySlotContents(index, null);
                    }
                }
                if (!ret) player.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Not enough items to fuel the " + dink.getType() + " ring!"));
            }
            return ret;
        }
        private static boolean isCorrectConsumeItemForDink (EnumDink dink, ItemStack compare) {
            String fullName;
            switch (dink) {
                case FLIGHT:
                    fullName = RDConfig.flightConsumeItem;
                    break;
                case LAVAWALK:
                    fullName = RDConfig.lavawalkConsumeItem;
                    break;
                case WATERWALK:
                    fullName = RDConfig.waterwalkConsumeItem;
                    break;
                case ANTIPOTION:
                    fullName = RDConfig.antipotionConsumeItem;
                    break;
                case EXTINGUISHER:
                    fullName = RDConfig.extinguisherConsumeItem;
                    break;
                case MAGNET:
                    fullName = RDConfig.magnetConsumeItem;
                    break;
                case WATERBREATHING:
                    fullName = RDConfig.waterbreathingConsumeItem;
                    break;
                case NIGHTVISION:
                    fullName = RDConfig.nightvisionConsumeItem;
                    break;
                case SATURATION:
                    return compare.getItem() instanceof ItemFood;
                case SPEED:
                    fullName = RDConfig.speedConusmeItem;
                    break;
                //TODO: CHEST
                case ENDERCHEST:
                case CRAFTINGTABLE:
                    return true;
                case MOBDERPEARL:
                    fullName = RDConfig.mobderpearlConsumeItem;
                    break;
                default:
                    return false;
            }
            return ItemHelper.isSameIgnoreStackSize(compare, ItemHelper.getItemFromName(fullName.substring(0, fullName.indexOf('@')), Integer.parseInt(fullName.substring(fullName.indexOf('@')+1))));
        }
        private static int getConsumeAmountForDink (EnumDink dink) {
            switch (dink) {
                case FLIGHT:
                    return RDConfig.flightConsumeAmount;
                case LAVAWALK:
                    return RDConfig.lavawalkConsumeAmount;
                case WATERWALK:
                    return RDConfig.waterwalkConsumeAmount;
                case ANTIPOTION:
                    return RDConfig.antipotionConsumeAmount;
                case EXTINGUISHER:
                    return RDConfig.extinguisherConsumeAmount;
                case MAGNET:
                    return RDConfig.magnetConsumeAmount;
                case WATERBREATHING:
                    return RDConfig.waterbreathingConsumeAmount;
                case NIGHTVISION:
                    return RDConfig.nightvisionConsumeAmount;
                case SATURATION:
                    return RDConfig.saturationConsumeAmount;
                case SPEED:
                    return RDConfig.speedConsumeAmount;
                //TODO: CHEST
                case ENDERCHEST:
                case CRAFTINGTABLE:
                    return 0;
                case MOBDERPEARL:
                    return RDConfig.mobderpearlConsumeAmount;
            }
            return 0;
        }
    }

    private static void enable (EnumDink dink, EntityPlayer player, ItemStack stack) {
        DinkNBT.setEnabled(stack,true);
        switch (dink) {
            case FLIGHT:
                if (!player.capabilities.allowFlying && player.capabilities.isCreativeMode) {
                    player.capabilities.allowFlying = true;
                    player.sendPlayerAbilities();
                }
                break;
        }
    }
    public static void disable (EnumDink dink, EntityPlayer player, ItemStack stack) { //TODO: make this private
        if (stack != null) DinkNBT.setEnabled(stack,false);
        switch (dink) {
            case FLIGHT:
                if (!player.capabilities.isCreativeMode) {
                    player.capabilities.allowFlying = false;
                    player.sendPlayerAbilities();
                }
                break;
            case WATERBREATHING:
                player.removePotionEffect(Potion.waterBreathing.id);
                break;
            case NIGHTVISION:
                player.removePotionEffect(Potion.nightVision.id);
                break;
            case SATURATION:
                player.removePotionEffect(Potion.saturation.id);
                break;
            case SPEED:
                player.removePotionEffect(Potion.moveSpeed.id);
                break;
        }
    }
    private static void doDinkAction (EnumDink dink, EntityPlayer player) {
        switch (dink) {
            case ANTIPOTION:
                player.clearActivePotions();
                break;
            case LAVAWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.lava || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_lava) {
                    player.worldObj.setBlockState(player.getPosition().down(), Blocks.stone.getDefaultState());
                    ItemConsume.doesDinkHaveItemsNeededToFunction(dink, player, false);
                }
                break;
            case WATERWALK:
                if (player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.water || player.worldObj.getBlockState(player.getPosition().down()).getBlock() == Blocks.flowing_water) {
                    player.worldObj.setBlockState(player.getPosition().down(), Blocks.ice.getDefaultState());
                    ItemConsume.doesDinkHaveItemsNeededToFunction(dink, player, false);
                }
                break;
            case EXTINGUISHER:
                if (player.isBurning()) {
                    player.extinguish();
                    ItemConsume.doesDinkHaveItemsNeededToFunction(dink, player, false);
                }
                break;
            case MAGNET: //thanks EnderIO
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
                break;
            case WATERBREATHING:
                player.addPotionEffect(new PotionEffect(Potion.waterBreathing.id, 160, 0, true, true));
                break;
            case NIGHTVISION:
                player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 160, 0, true, true));
                break;
            case SATURATION:
                player.addPotionEffect(new PotionEffect(Potion.saturation.id, 160, 0, true, true));
                break;
            case SPEED:
                player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 160, 2, true, true));
                break;
        }
    }
}
