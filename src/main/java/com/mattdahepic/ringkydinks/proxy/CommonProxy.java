package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.RDConstants;
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
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.ring,1,RDConstants.DinkLevel.TIER1.meta),"ggg","g g","ggg",'g', Items.gold_nugget);
        GameRegistry.addShapelessRecipe(new ItemStack(RingkyDinks.ring,1,RDConstants.DinkLevel.TIER2.meta), new ItemStack(RingkyDinks.ring, 1, 0), Items.diamond);
        GameRegistry.addShapelessRecipe(new ItemStack(RingkyDinks.ring,1,RDConstants.DinkLevel.TIER3.meta),new ItemStack(RingkyDinks.ring,1,1),Items.nether_star);
        //dinks
        ItemStack dinkTemplate = new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.TEMPLATE.id);
        GameRegistry.addShapedRecipe(dinkTemplate,"sss","sds","sss",'s',Items.stick,'d',Items.diamond);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.FLIGHT.id),"fgf","gtg","fgf",'f',Items.feather,'g',Blocks.glass,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.LAVAWALK.id),"lsl","sts","lsl",'l',Items.lava_bucket,'s', Blocks.stone,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.WATERWALK.id),"wiw","iti","wiw",'w',Items.water_bucket,'i',Blocks.ice,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.ANTIPOTION.id),"mpm","ptp","mpm",'m',Items.milk_bucket,'p',new ItemStack(Items.potionitem,1,16),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.EXTINGUISHER.id),"wbw","btb","wbw",'w',Blocks.wool,'b',Items.water_bucket,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.dink,1,RDConstants.EnumDink.MAGNET.id),"ioi","oto","ioi",'i',Items.iron_ingot,'o',Blocks.obsidian,'t',dinkTemplate);
        //ringkydinks
        for (RDConstants.EnumDink d : RDConstants.EnumDink.values()) {
            if (d.level == null) continue;
            GameRegistry.addRecipe(RDRecipeHandler.getRingAssembly(d));
            GameRegistry.addRecipe(RDRecipeHandler.getRingDissassembly(d));
        }
    }
}
