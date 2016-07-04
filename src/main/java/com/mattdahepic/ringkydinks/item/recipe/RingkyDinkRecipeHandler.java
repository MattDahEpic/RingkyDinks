package com.mattdahepic.ringkydinks.item.recipe;

import com.mattdahepic.mdecore.recipe.NBTRespectingShapelessOreRecipe;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public class RingkyDinkRecipeHandler {
    public static IRecipe assemble (EnumDink d) {
        ItemStack dink = DinkNBT.getDinkOfType(d);
        ItemStack ring = DinkNBT.getRingForDink(d);
        ItemStack ringkydink = DinkNBT.getRingkyDinkOfType(d);
        return new NBTRespectingShapelessOreRecipe(ringkydink,ring,dink);
    }
    public static IRecipe disassemble (EnumDink d) {
        ItemStack ringkydink = DinkNBT.getRingkyDinkOfType(d);
        ItemStack dink = DinkNBT.getDinkOfType(d);
        //ring covered by container item
        return new NBTRespectingShapelessOreRecipe(dink,ringkydink);
    }
}
