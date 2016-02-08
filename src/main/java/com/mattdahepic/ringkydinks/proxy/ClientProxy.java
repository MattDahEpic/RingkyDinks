package com.mattdahepic.ringkydinks.proxy;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkValues;
import net.minecraft.client.resources.model.ModelResourceLocation;
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
            //dinks
            ModelLoader.setCustomModelResourceLocation(RingkyDinks.dink,d.id,new ModelResourceLocation("ringkydinks:dink/dink_"+d.name,"inventory")); //todo
            //ringkydinks
            if (d.level == null) continue;
            ModelLoader.setCustomModelResourceLocation(RingkyDinks.ringkydink,d.id,new ModelResourceLocation("ringkydinks:ringkydink/ringkydink_"+d.name,"inventory")); //todo
        }
    }
}
