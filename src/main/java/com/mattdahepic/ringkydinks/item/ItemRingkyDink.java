package com.mattdahepic.ringkydinks.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkAbilities;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
        return DinkValues.getRingForDink(DinkValues.getDinkType(stack));
    }
    public String getUnlocalizedName (ItemStack stack) {
        return "item.ringkydink."+ DinkValues.getDinkType(stack);
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (DinkValues.EnumDink d : DinkValues.EnumDink.values()) {
            if (d == DinkValues.EnumDink.TEMPLATE) continue;
            subItems.add(DinkValues.getRingkyDinkOfType(d));
        }
    }
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        DinkValues.EnumDink dink = DinkValues.getDinkType(stack);
        DinkAbilities.enable(dink, (EntityPlayer) entityIn);
        DinkAbilities.tick(dink, (EntityPlayer) entityIn);
    }
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
        return DinkAbilities.onUse(DinkValues.getDinkType(stack),player,stack);
    }

    /* BAUBLES */
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.tick(DinkValues.getDinkType(stack),(EntityPlayer)player);
    }
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.enable(DinkValues.getDinkType(stack),(EntityPlayer)player);
    }
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.disable(DinkValues.getDinkType(stack),(EntityPlayer)player);
    }
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
    public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
}
