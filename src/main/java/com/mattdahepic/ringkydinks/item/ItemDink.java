package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemDink extends Item {
    public ItemDink () {
        this.setUnlocalizedName("dink");
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(RingkyDinks.tab);
    }
    public String getUnlocalizedName(ItemStack stack) {
        return "item.dink."+ DinkValues.getDinkType(stack);
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (DinkValues.EnumDink d : DinkValues.EnumDink.values()) {
            subItems.add(DinkValues.getDinkOfType(d));
        }
    }
}
