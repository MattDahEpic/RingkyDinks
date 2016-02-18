package com.mattdahepic.ringkydinks;

import com.mattdahepic.mdecore.update.UpdateChecker;
import com.mattdahepic.ringkydinks.config.RDConfig;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.item.ItemDink;
import com.mattdahepic.ringkydinks.item.ItemRing;
import com.mattdahepic.ringkydinks.item.ItemRingkyDink;
import com.mattdahepic.ringkydinks.item.ItemRubberHand;
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
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

@Mod(modid = RingkyDinks.MODID,name = RingkyDinks.NAME,version = RingkyDinks.VERSION,dependencies = RingkyDinks.DEPENDENCIES)
public class RingkyDinks {
    @Mod.Instance(RingkyDinks.MODID)
    public static RingkyDinks instance;

    public static final String MODID = "ringkydinks";
    public static final String NAME = "Ringky Dinks";
    public static final String VERSION = "@VERSION@";
    public static final String DEPENDENCIES = "required-after:mdecore@[1.8.9-1.1.1,);required-after:Baubles;";
    public static final String UPDATE_URL = "https://raw.githubusercontent.com/MattDahEpic/Version/master/"+ MinecraftForge.MC_VERSION+"/"+MODID+".txt";

    public static CreativeTabs tab = new CreativeTabs(MODID) {
        @Override
        public Item getTabIconItem() {
            return ringkydink;
        }
        @Override
        public ItemStack getIconItemStack() {
            return DinkNBT.getRingkyDinkOfType(EnumDink.FLIGHT);
        }
    };
    public static Item ring = new ItemRing();
    public static Item dink = new ItemDink();
    public static Item ringkydink = new ItemRingkyDink();
    public static Item rubber_hand = new ItemRubberHand();

    @SidedProxy(clientSide = "com.mattdahepic.ringkydinks.proxy.ClientProxy",serverSide = "com.mattdahepic.ringkydinks.proxy.CommonProxy")
    private static CommonProxy proxy;

    @Mod.EventHandler
    public void preInit (FMLPreInitializationEvent e) {
        MinecraftForge.EVENT_BUS.register(this);
        RDConfig.instance(MODID).initialize(e);
        RDNetworkHandler.initPackets();
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
        UpdateChecker.printMessageToPlayer(MODID, e.player);
    }
    /*@SubscribeEvent
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
    }*/
}
