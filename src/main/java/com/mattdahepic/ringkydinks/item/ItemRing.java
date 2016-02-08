package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemRing extends Item {
    public ItemRing () {
        this.setUnlocalizedName("ring");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(RingkyDinks.tab);
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if (stack.getMetadata() == DinkValues.DinkLevel.TIER1.meta) {
            tooltip.add("Tier 1");
        } else if (stack.getMetadata() == DinkValues.DinkLevel.TIER2.meta) {
            tooltip.add("Tier 2");
        } else if (stack.getMetadata() == DinkValues.DinkLevel.TIER3.meta) {
            tooltip.add("Tier 3");
        }
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (DinkValues.DinkLevel l : DinkValues.DinkLevel.values()) {
            subItems.add(new ItemStack(RingkyDinks.ring,1,l.meta));
        }
    }
}
