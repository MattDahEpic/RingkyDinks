package com.mattdahepic.ringkydinks;

import com.mattdahepic.ringkydinks.config.RDConfig;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.item.ItemDink;
import com.mattdahepic.ringkydinks.item.ItemRing;
import com.mattdahepic.ringkydinks.item.ItemRingkyDink;
import com.mattdahepic.ringkydinks.item.ItemRingkyDinkPouch;
import com.mattdahepic.ringkydinks.network.RDNetworkHandler;
import com.mattdahepic.ringkydinks.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid = RingkyDinks.MODID,name = RingkyDinks.NAME,version = RingkyDinks.VERSION,dependencies = RingkyDinks.DEPENDENCIES,updateJSON = RingkyDinks.UPDATE_JSON)
public class RingkyDinks {
    @Mod.Instance(RingkyDinks.MODID)
    public static RingkyDinks instance;

    public static final String MODID = "ringkydinks";
    public static final String NAME = "Ringky Dinks";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.10.2-1.0,);";
    public static final String UPDATE_JSON = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+MODID+".json";

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(ringkydink);
        }
        @Override
        public ItemStack getIconItemStack() {
            return DinkNBT.getRingkyDinkOfType(EnumDink.FLIGHT);
        }
    };
    public static Item ring = new ItemRing();
    public static Item dink = new ItemDink();
    public static Item ringkydink = new ItemRingkyDink();
    public static Item ringkydink_pouch = new ItemRingkyDinkPouch();

    @SidedProxy(clientSide = "com.mattdahepic.ringkydinks.proxy.ClientProxy",serverSide = "com.mattdahepic.ringkydinks.proxy.CommonProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        new RDConfig().initalize(e);
        RDNetworkHandler.initPackets();
        proxy.registerItems();
        proxy.registerTextures();
        proxy.registerUIs();
    }
    @Mod.EventHandler
    public void init (FMLInitializationEvent e) {
        proxy.registerRecipes();
    }
    @SubscribeEvent
    public void tick (TickEvent.PlayerTickEvent e) {
        for (EnumDink d : EnumDink.values()) { //for each dink type
            if (d.ability == null) continue; //ignore template
            boolean hasRing = false;
            for (ItemStack i : e.player.inventory.mainInventory) { //check every slot
                if (i != null && i.getItem() instanceof ItemRingkyDink) {
                    if (DinkNBT.getDinkType(i) == d) { //if dink of this type
                        hasRing = true;
                        break;
                    }
                }
            }
            if (!hasRing) {
                d.ability.disable(e.player,null);
            }
        }
    }
}
