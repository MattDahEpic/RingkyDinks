package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.mdecore.helpers.TranslationHelper;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class ItemRubberHand extends Item {
    ItemStackHandler itemHandler = new ItemStackHandler(5) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }
    };
    public ItemRubberHand () {
        this.setUnlocalizedName("rubber_hand");
        this.setCreativeTab(RingkyDinks.tab);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }
    /*@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) { //TODO: renable
        if () { //has rings
            tooltip.add(TranslationHelper.getTranslatedString("ringkydink.hand.full"));
            for () { //all dinks
                tooltip.add(DinkNBT.getNiceDinkNameForTooltip(DinkNBT.getDinkType(stack)));
            }
        } else {
            tooltip.add(TranslationHelper.getTranslatedString("ringkydink.hand.empty"));
        }
    }*/
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        //TODO: tick all items in the pack
    }
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
        if (player.isSneaking()) {
            player.openGui(RingkyDinks.instance,0,world,player.serverPosX,player.serverPosY,player.serverPosZ);
        }
        return stack;
    }
}
