package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
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
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, EnumDink.EnumRing.TIER1.meta,new ModelResourceLocation("ringkydinks:ring/ring1","inventory"));
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, EnumDink.EnumRing.TIER2.meta,new ModelResourceLocation("ringkydinks:ring/ring2","inventory"));
        ModelLoader.setCustomModelResourceLocation(RingkyDinks.ring, EnumDink.EnumRing.TIER3.meta,new ModelResourceLocation("ringkydinks:ring/ring3","inventory"));
        for (EnumDink d : EnumDink.values()) {
            ModelBakery.registerItemVariants(RingkyDinks.dink,new ModelResourceLocation("ringkydinks:dink/dink_"+d.getType(),"inventory")); //dink model
            if (d != EnumDink.TEMPLATE) ModelBakery.registerItemVariants(RingkyDinks.ringkydink,new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+d.getType(),"inventory")); //ringkydink model
        }
        ModelLoader.setCustomMeshDefinition(RingkyDinks.dink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                EnumDink dink = DinkNBT.getDinkType(stack);
                if (dink == null) return new ModelResourceLocation(TextureMap.LOCATION_MISSING_TEXTURE,"inventory");
                return new ModelResourceLocation("ringkydinks:dink/dink_"+dink.getType(),"inventory");
            }
        });
        ModelLoader.setCustomMeshDefinition(RingkyDinks.ringkydink, new ItemMeshDefinition() {
            @Override
            public ModelResourceLocation getModelLocation(ItemStack stack) {
                EnumDink dink = DinkNBT.getDinkType(stack);
                if (dink == null || dink == EnumDink.TEMPLATE) return new ModelResourceLocation(TextureMap.LOCATION_MISSING_TEXTURE,"inventory");
                return new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+DinkNBT.getDinkType(stack).getType(),"inventory");
            }
        });
    }
}
