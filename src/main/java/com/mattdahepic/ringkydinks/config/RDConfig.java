package com.mattdahepic.ringkydinks.config;

import com.mattdahepic.mdecore.config.sync.Config;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;
import com.mattdahepic.ringkydinks.RingkyDinks;

public class RDConfig extends ConfigSyncable {
    public String getConfigVersion () {return "1";}
    public String getConfigName () {return RingkyDinks.MODID;}
    public Class getConfigClass () {return getClass();}

    @Config(comment = "Should ringkydinks use items from the user's inventory to function?") public static boolean consumeItems = true;
    @Config(comment = "The time interval (in ticks) at which items should be consumed to maintain ring function.",range = @Config.Range(min = 1,max = 12000)) public static int consumeInterval = 600;

    @Config(cat = "consume.item",comment = {"How many items should the flight ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int flightConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the lava walking ring consume every time it places a block?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int lavawalkConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the water walking ring consume every time it places a block?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int waterwalkConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the antipotion ring consume every time it removes potion effects?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int antipotionConsumeAmount = 0;
    @Config(cat = "consume.item",comment = {"How many items should the extinguisher ring consume every time it sets you out?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int extinguisherConsumeAmount = 0;
    @Config(cat = "consume.item",comment = {"How many items should the magnet consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int magnetConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the water breathing ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int waterbreathingConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the night vision ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int nightvisionConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many food items should the saturation ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int saturationConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the speed ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int speedConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the mobderpearl consume when it grabs a mob?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int mobderpearlConsumeAmount = 1;
    @Config(cat = "consume.item",comment = {"How many items should the regeneration ring consume every consumption interval?","Set to 0 to disable."},range = @Config.Range(min = 0,max = 64)) public static int regenerationConsumeAmount = 1;

    @Config(cat = "consume.amount",comment = "What item should the flight ring consume?") public static String flightConsumeItem = "minecraft:feather@0";
    @Config(cat = "consume.amount",comment = "What item should the lava walking ring consume?") public static String lavawalkConsumeItem = "minecraft:cobblestone@0";
    @Config(cat = "consume.amount",comment = "What item should the water walking ring consume?") public static String waterwalkConsumeItem = "minecraft:snowball@0";
    @Config(cat = "consume.amount",comment = "What item should the antipotion ring consume?") public static String antipotionConsumeItem = "minecraft:milk_bucket@0";
    @Config(cat = "consume.amount",comment = "What item should the extinguisher ring consume?") public static String extinguisherConsumeItem = "minecraft:water_bucket@0";
    @Config(cat = "consume.amount",comment = "What item should the magnet ring consume?") public static String magnetConsumeItem = "minecraft:iron_ingot@0";
    @Config(cat = "consume.amount",comment = "What item should the water breathing ring consume?") public static String waterbreathingConsumeItem = "minecraft:fish@0";
    @Config(cat = "consume.amount",comment = "What item should the night vision ring consume?") public static String nightvisionConsumeItem = "minecraft:torch@0";
    @Config(cat = "consume.amount",comment = "What item should the speed ring consume?") public static String speedConusmeItem = "minecraft:sugar@0";
    @Config(cat = "consume.amount",comment = "What item should the mobderpearl consume?") public static String mobderpearlConsumeItem = "minecraft:ender_pearl@0";
    @Config(cat = "consume.amount",comment = "What item should the regeneration ring consume?") public static String regenerationConsumeItem = "minecraft:ghast_tear@0";
}
