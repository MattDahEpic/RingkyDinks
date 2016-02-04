package com.mattdahepic.ringkydinks.dink.recipe;

import com.mattdahepic.mdecore.helpers.ItemHelper;
import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.RDConstants;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Arrays;

public class RDRecipeHandler {
    public static IRecipe getRingAssembly (RDConstants.EnumDink dinkType) {
        ItemStack ring = new ItemStack(RingkyDinks.ring,1,dinkType.level.meta);
        ItemStack dink = new ItemStack(RingkyDinks.dink,1,dinkType.id);
        ItemStack ret = new ItemStack(RingkyDinks.ringkydink,1,dinkType.id);
        return new ShapelessRecipes(ret,Arrays.asList(ring,dink));
    }
    public static IRecipe getRingDissassembly (RDConstants.EnumDink dinkType) {
        ItemStack ringdink = new ItemStack(RingkyDinks.ringkydink,1,dinkType.id);
        return new ShapelessRecipes(new ItemStack(RingkyDinks.dink,1,dinkType.id), Arrays.asList(ringdink));
    }
}
