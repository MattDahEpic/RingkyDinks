package com.mattdahepic.ringkydinks.item;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkAbilities;
import com.mattdahepic.ringkydinks.dink.RDConstants;
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
    public ItemStack getContainerItem (ItemStack stack) {
        return new ItemStack(RingkyDinks.ring,1,RDConstants.getDinkByID(stack.getMetadata()).level.meta);
    }
    public String getUnlocalizedName (ItemStack stack) {
        return "item.ringkydink."+RDConstants.getDinkByID(stack.getMetadata()).name;
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (RDConstants.EnumDink d : RDConstants.EnumDink.values()) {
            if (d.id == 0) continue;
            subItems.add(new ItemStack(RingkyDinks.ringkydink, 1, d.id));
        }
    }
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        RDConstants.EnumDink dink = RDConstants.getDinkByID(stack.getMetadata());
        DinkAbilities.enable(dink, (EntityPlayer) entityIn);
        DinkAbilities.tick(dink, (EntityPlayer) entityIn);
    }
    public ItemStack onItemRightClick (ItemStack stack, World world, EntityPlayer player) {
        return DinkAbilities.onUse(RDConstants.getDinkByID(stack.getMetadata()),player,stack);
    }

    /* BAUBLES */
    public BaubleType getBaubleType(ItemStack stack) {
        return BaubleType.RING;
    }
    public void onWornTick(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.tick(RDConstants.getDinkByID(stack.getMetadata()),(EntityPlayer)player);
    }
    public void onEquipped(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.enable(RDConstants.getDinkByID(stack.getMetadata()),(EntityPlayer)player);
    }
    public void onUnequipped(ItemStack stack, EntityLivingBase player) {
        DinkAbilities.disable(RDConstants.getDinkByID(stack.getMetadata()),(EntityPlayer)player);
    }
    public boolean canEquip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
    public boolean canUnequip(ItemStack stack, EntityLivingBase player) {
        return true;
    }
}
