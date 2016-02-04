package com.mattdahepic.ringkydinks;

import com.mattdahepic.mdecore.update.UpdateChecker;
import com.mattdahepic.ringkydinks.dink.RDConstants;
import com.mattdahepic.ringkydinks.item.ItemDink;
import com.mattdahepic.ringkydinks.item.ItemRing;
import com.mattdahepic.ringkydinks.item.ItemRingkyDink;
import com.mattdahepic.ringkydinks.proxy.CommonProxy;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = RingkyDinks.MODID,name = RingkyDinks.NAME,version = RingkyDinks.VERSION,dependencies = RingkyDinks.DEPENDENCIES)
public class RingkyDinks {
    public static final String MODID = "ringkydinks";
    public static final String NAME = "Ringky Dinks";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.8.9-1.0,);";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+ MinecraftForge.MC_VERSION+"/"+MODID+".txt";

    public static final Logger log = LogManager.getLogger(MODID);

    public static Item ring = new ItemRing();
    public static Item dink = new ItemDink();
    public static Item ringkydink = new ItemRingkyDink();
    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return ringkydink;
        }
        @Override
        public int getIconItemDamage() {
            return RDConstants.EnumDink.FLIGHT.id;
        }
    };

    @SidedProxy(clientSide = "com.mattdahepic.ringkydinks.proxy.ClientProxy",serverSide = "com.mattdahepic.ringkydinks.proxy.CommonProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        proxy.registerItems();
        proxy.registerTextures();
    }
    @Mod.EventHandler
    public void init (FMLInitializationEvent e) {
        proxy.registerRecipes();
        UpdateChecker.checkRemote(MODID, UPDATE_URL);
    }
    @SubscribeEvent
    public void joinServer (PlayerEvent.PlayerLoggedInEvent e) {
        UpdateChecker.printMessageToPlayer(MODID,e.player);
    }
}