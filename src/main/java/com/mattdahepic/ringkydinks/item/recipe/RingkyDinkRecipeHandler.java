package com.mattdahepic.ringkydinks.item.recipe;

import com.google.common.collect.Lists;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import net.minecraftforge.oredict.OreDictionary;

import java.util.Collections;
import java.util.List;

public class RingkyDinkRecipeHandler {
    public static IRecipe assemble (EnumDink d) {
        ItemStack dink = DinkNBT.getDinkOfType(d);
        ItemStack ring = DinkNBT.getRingForDink(d);
        ItemStack ringkydink = DinkNBT.getRingkyDinkOfType(d);
        return new NBTRespectingShaplessRecipe(ringkydink, Lists.newArrayList(dink,ring));
    }
    public static IRecipe disassemble (EnumDink d) {
        ItemStack ringkydink = DinkNBT.getRingkyDinkOfType(d);
        ItemStack dink = DinkNBT.getDinkOfType(d);
        //ring covered by container item
        return new NBTRespectingShaplessRecipe(dink, Collections.singletonList(ringkydink));
    }
    public static class NBTRespectingShaplessRecipe extends ShapelessRecipes {
        public NBTRespectingShaplessRecipe (ItemStack output, List<ItemStack> ingreeds) {
            super(output,ingreeds);
        }
        @Override
        public boolean matches(InventoryCrafting inv, World worldIn) {
            List<ItemStack> list = Lists.newArrayList(this.recipeItems);
            for (int i = 0; i < inv.getHeight(); ++i) {
                for (int j = 0; j < inv.getWidth(); ++j) {
                    ItemStack compare = inv.getStackInRowAndColumn(j, i);
                    if (compare != null) {
                        boolean flag = false;
                        for (ItemStack template : list) {
                            if (!template.hasTagCompound() || !compare.hasTagCompound()) {
                                if (compare.getItem() == template.getItem() && (template.getMetadata() == OreDictionary.WILDCARD_VALUE || compare.getMetadata() == template.getMetadata())) {
                                    flag = true;
                                    list.remove(template);
                                    break;
                                }
                            } else {
                                if (compare.getItem() == template.getItem() && (template.getMetadata() == OreDictionary.WILDCARD_VALUE || compare.getMetadata() == template.getMetadata()) && compare.getTagCompound().equals(template.getTagCompound())) {
                                    flag = true;
                                    list.remove(template);
                                    break;
                                }
                            }
                        }
                        if (!flag) {
                            return false;
                        }
                    }
                }
            }

            return list.isEmpty();
        }
    }
}
