package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.client.renderer.ItemMeshDefinition;
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
            ModelLoader.setCustomModelResourceLocation(RingkyDinks.dink,0,new ModelResourceLocation("ringkydinks:dink/dink_"+d.getType(),"inventory")); //dink model
            if (d != DinkValues.EnumDink.TEMPLATE) ModelLoader.setCustomModelResourceLocation(RingkyDinks.ringkydink,0,new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+d.getType(),"inventory")); //ringkydink model
        }
        ModelLoader.setCustomMeshDefinition(RingkyDinks.dink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("ringkydinks:dink/dink_"+DinkValues.getDinkType(stack).getType());
            }
        });
        ModelLoader.setCustomMeshDefinition(RingkyDinks.ringkydink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                return new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+DinkValues.getDinkType(stack).getType());
            }
        });
    }
}
