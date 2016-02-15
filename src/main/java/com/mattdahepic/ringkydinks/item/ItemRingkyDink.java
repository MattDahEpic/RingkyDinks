package com.mattdahepic.ringkydinks.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.dink.ability.DinkAbilities;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

import java.util.List;

public class ItemRingkyDink extends Item implements IBauble {
    public long lastUpdate = 0;
    public ItemRingkyDink () {
        this.setUnlocalizedName("ringkydink");
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(RingkyDinks.tab);
    }
    public boolean hasContainerItem (ItemStack stack) {
        return true;
    }
    public ItemStack getContainerItem (ItemStack stack) {
        return DinkNBT.getRingForDink(DinkNBT.getDinkType(stack));
    }
    public String getUnlocalizedName (ItemStack stack) {
        return "item.ringkydink."+ DinkNBT.getDinkType(stack);
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (EnumDink d : EnumDink.values()) {
            if (d == EnumDink.TEMPLATE) continue;
            subItems.add(DinkNBT.getRingkyDinkOfType(d));
        }
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        EnumDink dink = DinkNBT.getDinkType(stack);
        if (dink != null) {
            if (dink == EnumDink.MOBDERPEARL) {
                tooltip.add(DinkAbilities.Mobderpearl.getHasMob(stack) ? "Contains " + DinkAbilities.Mobderpearl.getMobName(stack) : "Empty");
            } else if (!dink.hasUseAbility) {
                tooltip.add(DinkNBT.getEnabled(stack) ? "Enabled" : "Disabled");
            }
        }
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        EnumDink dink = DinkNBT.getDinkType(stack);
        if (dink != null) {
            if (dink == EnumDink.MOBDERPEARL) {
                return DinkAbilities.Mobderpearl.getHasMob(stack);
            } else if (!dink.hasUseAbility) {
                return DinkNBT.getEnabled(stack);
            }
        }
        return false;
    }

    /* DINK ABILITIES */
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if (DinkNBT.getDinkType(stack) == null) return; //update safety
        DinkAbilities.tick(DinkNBT.getDinkType(stack),(EntityPlayer)entityIn,stack);
    }
    @Override
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
        return DinkAbilities.onUse(DinkNBT.getDinkType(stack),player,stack);
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target) {
        if (DinkNBT.getDinkType(stack) == EnumDink.MOBDERPEARL && DinkAbilities.ItemConsume.doesDinkHaveItemsNeededToFunction(EnumDink.MOBDERPEARL,player,false)) {
            return DinkAbilities.Mobderpearl.captureMob(player, stack, target);
        }
        return false;
    }
    @Override
    public boolean onItemUse(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ) {
        return DinkNBT.getDinkType(stack) == EnumDink.MOBDERPEARL && DinkAbilities.Mobderpearl.releaseMob(player,stack,pos,side);
    }

    /* BAUBLES */
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.tick(DinkNBT.getDinkType(stack),(EntityPlayer)player,stack);
    }
    public void onEquipped(ItemStack stack, EntityLivingBase player) {}
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {}
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
    public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
}
