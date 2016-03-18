package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.mdecore.helpers.TranslationHelper;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
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
    public String getItemStackDisplayName(ItemStack stack) {
        return TranslationHelper.getTranslatedString(DinkNBT.getNiceDinkNameForTooltip(DinkNBT.getDinkType(stack)))+TranslationHelper.getTranslatedString("item.dink.suffix");
    }
    @Override
    public void getSubItems(Item item, CreativeTabs tab, List<ItemStack> subItems) {
        for (EnumDink d : EnumDink.values()) {
            subItems.add(DinkNBT.getDinkOfType(d));
        }
    }
}
