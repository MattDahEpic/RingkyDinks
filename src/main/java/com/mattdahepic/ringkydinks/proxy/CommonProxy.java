package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import com.mattdahepic.ringkydinks.dink.recipe.RDRecipeHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    public void registerTextures () {}
    public void registerItems () {
        GameRegistry.registerItem(RingkyDinks.ring,"ring");
        GameRegistry.registerItem(RingkyDinks.dink,"dink");
        GameRegistry.registerItem(RingkyDinks.ringkydink,"ringkydink");
    }
    public void registerRecipes () {
        //rings
        GameRegistry.addShapedRecipe(DinkValues.getRingOfLevel(DinkValues.DinkLevel.TIER1),"ggg","g g","ggg",'g', Items.gold_nugget);
        GameRegistry.addShapelessRecipe(DinkValues.getRingOfLevel(DinkValues.DinkLevel.TIER2), new ItemStack(RingkyDinks.ring, 1, 0), Items.diamond);
        GameRegistry.addShapelessRecipe(DinkValues.getRingOfLevel(DinkValues.DinkLevel.TIER3),new ItemStack(RingkyDinks.ring,1,1),Items.nether_star);
        //dinks
        ItemStack dinkTemplate = DinkValues.getDinkOfType(DinkValues.EnumDink.TEMPLATE);
        GameRegistry.addShapedRecipe(dinkTemplate,"sss","sds","sss",'s',Items.stick,'d',Items.diamond);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.FLIGHT),"fgf","gtg","fgf",'f',Items.feather,'g',Blocks.glass,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.LAVAWALK),"lsl","sts","lsl",'l',Items.lava_bucket,'s', Blocks.stone,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.WATERWALK),"wiw","iti","wiw",'w',Items.water_bucket,'i',Blocks.ice,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.ANTIPOTION),"mpm","ptp","mpm",'m',Items.milk_bucket,'p',new ItemStack(Items.potionitem,1,16),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.EXTINGUISHER),"wbw","btb","wbw",'w',Blocks.wool,'b',Items.water_bucket,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.MAGNET),"ioi","oto","ioi",'i',Items.iron_ingot,'o',Blocks.obsidian,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.WATERBREATHING),"bpb","ptp","bpb",'b',Items.bucket,'p',new ItemStack(Items.fish,1,3),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkValues.getDinkOfType(DinkValues.EnumDink.SATURATION),"cfp","atb","mrs",'c',Items.cooked_chicken,'f',Items.cooked_fish,'p',Items.cooked_porkchop,'a',Items.apple,'b',Items.bread,'m',Items.cooked_mutton,'r',Items.cooked_rabbit,'s',Items.cooked_beef,'t',dinkTemplate);
        //ringkydinks
        for (DinkValues.EnumDink d : DinkValues.EnumDink.values()) {
            if (d.level == null) continue;
            GameRegistry.addRecipe(RDRecipeHandler.getRingAssembly(d));
            GameRegistry.addRecipe(RDRecipeHandler.getRingDissassembly(d));
        }
    }
}
