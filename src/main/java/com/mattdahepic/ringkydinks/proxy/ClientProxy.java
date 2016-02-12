package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    @Override
    public void registerTextures () {
        //rings
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, DinkValues.DinkLevel.TIER1.meta,new ModelResourceLocation("ringkydinks:ring/ring1","inventory"));
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, DinkValues.DinkLevel.TIER2.meta,new ModelResourceLocation("ringkydinks:ring/ring2","inventory"));
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, DinkValues.DinkLevel.TIER3.meta,new ModelResourceLocation("ringkydinks:ring/ring3","inventory"));
        for (DinkValues.EnumDink d : DinkValues.EnumDink.values()) {
            ModelBakery.registerItemVariants(RingkyDinks.dink,new ModelResourceLocation("ringkydinks:dink/dink_"+d.getType(),"inventory")); //dink model
            if (d != DinkValues.EnumDink.TEMPLATE) ModelBakery.registerItemVariants(RingkyDinks.ringkydink,new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+d.getType(),"inventory")); //ringkydink model
        }
        ModelLoader.setCustomMeshDefinition(RingkyDinks.dink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                DinkValues.EnumDink dink = DinkValues.getDinkType(stack);
                if (dink == null)  return (ModelResourceLocation)TextureMap.LOCATION_MISSING_TEXTURE;
                return new ModelResourceLocation("ringkydinks:dink/dink_"+dink.getType(),"inventory");
            }
        });
        ModelLoader.setCustomMeshDefinition(RingkyDinks.ringkydink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                DinkValues.EnumDink dink = DinkValues.getDinkType(stack);
                if (dink == null || dink == DinkValues.EnumDink.TEMPLATE) return (ModelResourceLocation)TextureMap.LOCATION_MISSING_TEXTURE;
                return new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+DinkValues.getDinkType(stack).getType(),"inventory");
            }
        });
    }
}
