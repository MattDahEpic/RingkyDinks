package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.List;

public class ItemRubberHand extends Item {
    public ItemRubberHand () {
        this.setUnlocalizedName("rubber_hand");
        this.setCreativeTab(RingkyDinks.tab);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        //TODO
    }
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        //TODO: tick all items in the pack
    }
}
