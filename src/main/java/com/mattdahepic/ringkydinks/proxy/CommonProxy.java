package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.item.recipe.RingkyDinkRecipeHandler;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class CommonProxy {
    public void registerTextures () {}
    public void registerUIs () {
        //TODO: NetworkRegistry.INSTANCE.registerGuiHandler(RingkyDinks.instance,new RDGUIHandler());
    }
    public void registerItems () {
        GameRegistry.register(RingkyDinks.ring.setRegistryName("ring"));
        //TODO: GameRegistry.register(RingkyDinks.ringkydink_pouch.setRegistryName("ringkydink_pouch"));
        GameRegistry.register(RingkyDinks.dink.setRegistryName("dink"));
        GameRegistry.register(RingkyDinks.ringkydink.setRegistryName("ringkydink"));
    }
    public void registerRecipes () {
        //rings
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER1),"ggg","g g","ggg",'g',"nuggetGold"));
        GameRegistry.addShapelessRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER2), new ItemStack(RingkyDinks.ring, 1, 0), Items.DIAMOND);
        GameRegistry.addShapelessRecipe(DinkNBT.getRingOfLevel(EnumDink.EnumRing.TIER3),new ItemStack(RingkyDinks.ring,1,1),Items.NETHER_STAR);
        //rubber hand
        GameRegistry.addShapedRecipe(new ItemStack(RingkyDinks.ringkydink_pouch,1,0),"ses","ece","ses",'s',Items.STICK,'e',Items.ENDER_PEARL,'c',Blocks.CHEST);
        //dinks
        ItemStack dinkTemplate = DinkNBT.getDinkOfType(EnumDink.TEMPLATE);
        GameRegistry.addRecipe(new ShapedOreRecipe(dinkTemplate,"sss","sds","sss",'s',Items.STICK,'d',"gemDiamond"));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.FLIGHT),"fgf","gtg","fgf",'f',Items.FEATHER,'g',Blocks.GLASS,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.LAVAWALK),"lsl","sts","lsl",'l',Items.LAVA_BUCKET,'s',"stone",'t',dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.WATERWALK),"wiw","iti","wiw",'w',Items.WATER_BUCKET,'i',Blocks.ICE,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.ANTIPOTION),"mpm","ptp","mpm",'m',Items.MILK_BUCKET,'p',new ItemStack(Items.POTIONITEM,1,16),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.EXTINGUISHER),"wbw","btb","wbw",'w',Blocks.WOOL,'b',Items.WATER_BUCKET,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.MAGNET),"ioi","oto","ioi",'i',"ingotIron",'o',Blocks.OBSIDIAN,'t',dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.WATERBREATHING),"bpb","ptp","bpb",'b',Items.BUCKET,'p',new ItemStack(Items.FISH,1,3),'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.SATURATION),"cfp","atb","mrs",'c',Items.COOKED_CHICKEN,'f',Items.COOKED_FISH,'p',Items.COOKED_PORKCHOP,'a',Items.APPLE,'b',Items.BREAD,'m',Items.COOKED_MUTTON,'r',Items.COOKED_RABBIT,'s',Items.COOKED_BEEF,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.SPEED),"sss","sts","sss",'s',Items.SUGAR,'t',dinkTemplate);
        //TODO: CHEST
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.ENDERCHEST),"ece","ptp","epe",'e',Items.ENDER_EYE,'p',Items.ENDER_PEARL,'c',Blocks.ENDER_CHEST,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.CRAFTINGTABLE),"cpc","ptp","cpc",'c',Blocks.CRAFTING_TABLE,'p',Items.ENDER_PEARL,'t',dinkTemplate);
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.MOBDERPEARL),"epe","ptp","epe",'e',Items.ENDER_EYE,'p',Items.ENDER_PEARL,'t',dinkTemplate);
        GameRegistry.addRecipe(new ShapedOreRecipe(DinkNBT.getDinkOfType(EnumDink.UPHILLSTEPASSIST), "sls", "ltl", "sls", 's', "stair", 'l', "slab", 't', dinkTemplate));
        GameRegistry.addShapedRecipe(DinkNBT.getDinkOfType(EnumDink.REGENERATION),"gpg","ptp","gpg",'g',Items.GHAST_TEAR,'p',new ItemStack(Items.POTIONITEM,1,8225),'t',dinkTemplate);
        //ringkydinks
        for (EnumDink d : EnumDink.values()) {
            if (d.ability == null) continue; //ignore template, cause it an independent dink who dont need no ring
            GameRegistry.addRecipe(RingkyDinkRecipeHandler.assemble(d)); //ring assembly
            GameRegistry.addRecipe(RingkyDinkRecipeHandler.disassemble(d)); //ring disassembly
        }
    }
}
