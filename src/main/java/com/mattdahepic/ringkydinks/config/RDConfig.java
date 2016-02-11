package com.mattdahepic.ringkydinks.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;

public class RDConfig extends ConfigSyncable {
    public static final String CAT_CONSUMEITEMS = "consume.item";
    public static final String CAT_CONSUMEAMOUNT = "consume.amount";
    @Config @Comment({"Should ringkydinks use items from the user's inventory to function?"}) public static boolean consumeItems = true;
    @Config @Comment({"The time interval (in ticks) at which items should be consumed to maintain ring function."}) @Range(min = 1,max = 12000) public static int consumeInterval = 600;

    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the flight ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int flightConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the lava walking ring consume every time it places a block?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int lavawalkConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the water walking ring consume every time it places a block?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int waterwalkConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the antipotion ring consume every time it removes potion effects?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int antipotionConsumeAmount = 0;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the extinguisher ring consume every time it sets you out?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int extinguisherConsumeAmount = 0;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the magnet consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int magnetConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the water breathing ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int waterbreathingConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the night vision ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int nightvisionConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many food items should the saturation ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int saturationConsumeAmount = 1;
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the speed ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int speedConsumeAmount = 1;
    //TODO: CHEST
    //TODO: ENDERCHEST
    //TODO: CRAFTINGTABLE
    @Config(CAT_CONSUMEAMOUNT) @Comment({"How many items should the mobderpearl consume when it grabs a mob?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int mobderpearlConsumeAmount = 1;

    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the flight ring consume?"}) public static String flightConsumeItem = "minecraft:feather@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the lava walking ring consume?"}) public static String lavawalkConsumeItem = "minecraft:cobblestone@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the water walking ring consume?"}) public static String waterwalkConsumeItem = "minecraft:snowball@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the antipotion ring consume?"}) public static String antipotionConsumeItem = "minecraft:milk_bucket@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the extinguisher ring consume?"}) public static String extinguisherConsumeItem = "minecraft:water_bucket@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the magnet ring consume?"}) public static String magnetConsumeItem = "minecraft:iron_ingot@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the water breathing ring consume?"}) public static String waterbreathingConsumeItem = "minecraft:fish@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the night vision ring consume?"}) public static String nightvisionConsumeItem = "minecraft:torch@0";
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the speed ring consume?"}) public static String speedConusmeItem = "minecraft:sugar@0";
    //TODO: CHEST
    //TODO: ENDERCHEST
    //TODO: CRAFTINGTABLE
    @Config(CAT_CONSUMEITEMS) @Comment({"What item should the mobderpearl consume?"}) public static String mobderpearlConsumeItem = "minecraft:ender_pearl@0";

    private static ConfigSyncable INSTANCE;
    public static ConfigSyncable instance(String configName) {
        if (INSTANCE == null) {
            INSTANCE = new RDConfig(configName);
        }
        return INSTANCE;
    }

    public static ConfigProcessor processor;

    protected RDConfig(String configName) {
        super(configName);
    }
    @Override
    public void init() {
        processor = new ConfigProcessor(getClass(), this.config, this.configFileName);
        processor.process(true);
    }
    @Override
    protected void reloadIngameConfigs() {}
    @Override
    protected void reloadNonIngameConfigs() {}
    @Override
    public String getConfigName() {
        return this.configFileName;
    }
}
