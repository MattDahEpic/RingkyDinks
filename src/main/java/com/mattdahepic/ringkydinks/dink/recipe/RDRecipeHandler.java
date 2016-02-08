package com.mattdahepic.ringkydinks.dink.recipe;

import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;

import java.util.Arrays;
import java.util.Collections;

public class RDRecipeHandler {
    public static IRecipe getRingAssembly (DinkValues.EnumDink dinkType) {
        ItemStack ring = DinkValues.getRingForDink(dinkType);
        ItemStack dink = DinkValues.getDinkOfType(dinkType);
        ItemStack ret = DinkValues.getRingkyDinkOfType(dinkType);
        return new ShapelessRecipes(ret,Arrays.asList(ring,dink));
    }
    public static IRecipe getRingDissassembly (DinkValues.EnumDink dinkType) {
        ItemStack ringdink = DinkValues.getRingkyDinkOfType(dinkType);
        return new ShapelessRecipes(DinkValues.getDinkOfType(dinkType), Collections.singletonList(ringdink)); //todo: doesnt give back ring or correct dink
    }
}
