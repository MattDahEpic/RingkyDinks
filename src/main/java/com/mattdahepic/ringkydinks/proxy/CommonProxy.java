package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.item.recipe.RingkyDinkRecipeHandler;
//import com.mattdahepic.ringkydinks.item.ui.RDGUIHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy {
    public void registerTextures () {}
    public void registerUIs () {
        //NetworkRegistry.INSTANCE.registerGuiHandler(RingkyDinks.instance,new RDGUIHandler()); //TODO: renable
    }
    public void registerItems () {
        GameRegistry.registerItem(RingkyDinks.ring,"ring");
        GameRegistry.registerItem(RingkyDinks.rubber_hand,"rubber_hand");
        GameRegistry.registerItem(RingkyDinks.dink,"dink");
        GameRegistry.registerItem(RingkyDinks.ringkydink,"ringkydink");
    }
    public void registerRecipes () {
        //rings
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER1),"ggg","g g","ggg",'g',"nuggetGold"));
        GameRegistry.addShapelessRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER2), new ItemStack(RingkyDinks.ring, 1, 0), Items.diamond);
        GameRegistry.addShapelessRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER3),new ItemStack(RingkyDinks.ring,1,1),Items.nether_star);
        //rubber hand
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.rubber_hand,1,0),"ses","ece","ses",'s',Items.stick,'e',Items.ender_pearl,'c',Blocks.chest);
        //dinks
        ItemStack dinkTemplate = DinkNBT.getDinkOfType(EnumDink.TEMPLATE);
        GameRegistry.addRecipe(new ShapedOreRecipe(dinkTemplate,"sss","sds","sss",'s',Items.stick,'d',"gemDiamond"));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.FLIGHT),"fgf","gtg","fgf",'f',Items.feather,'g',Blocks.glass,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.LAVAWALK),"lsl","sts","lsl",'l',Items.lava_bucket,'s',"stone",'t',dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.WATERWALK),"wiw","iti","wiw",'w',Items.water_bucket,'i',Blocks.ice,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.ANTIPOTION),"mpm","ptp","mpm",'m',Items.milk_bucket,'p',new ItemStack(Items.potionitem,1,16),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.EXTINGUISHER),"wbw","btb","wbw",'w',Blocks.wool,'b',Items.water_bucket,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.MAGNET),"ioi","oto","ioi",'i',"ingotIron",'o',Blocks.obsidian,'t',dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.WATERBREATHING),"bpb","ptp","bpb",'b',Items.bucket,'p',new ItemStack(Items.fish,1,3),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.SATURATION),"cfp","atb","mrs",'c',Items.cooked_chicken,'f',Items.cooked_fish,'p',Items.cooked_porkchop,'a',Items.apple,'b',Items.bread,'m',Items.cooked_mutton,'r',Items.cooked_rabbit,'s',Items.cooked_beef,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.SPEED),"sss","sts","sss",'s',Items.sugar,'t',dinkTemplate);
        //TODO: CHEST
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.ENDERCHEST),"ece","ptp","epe",'e',Items.ender_eye,'p',Items.ender_pearl,'c',Blocks.ender_chest,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.CRAFTINGTABLE),"cpc","ptp","cpc",'c',Blocks.crafting_table,'p',Items.ender_pearl,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.MOBDERPEARL),"epe","ptp","epe",'e',Items.ender_eye,'p',Items.ender_pearl,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.UPHILLSTEPASSIST), "sls", "ltl", "sls", 's', "stair", 'l', "slab", 't', dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.REGENERATION),"gpg","ptp","gpg",'g',Items.ghast_tear,'p',new ItemStack(Items.potionitem,1,8225),'t',dinkTemplate);
        //ringkydinks
        for (EnumDink d : EnumDink.values()) {
            if (d.ability == null) continue; //ignore template, cause it an independent dink who dont need no ring
            GameRegistry.addRecipe(RingkyDinkRecipeHandler.assemble(d)); //ring assembly
            GameRegistry.addRecipe(RingkyDinkRecipeHandler.disassemble(d)); //ring disassembly
        }
    }
}
