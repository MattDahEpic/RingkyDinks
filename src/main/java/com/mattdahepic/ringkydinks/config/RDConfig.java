package com.mattdahepic.ringkydinks.config;

import com.mattdahepic.mdecore.config.annot.Comment;
import com.mattdahepic.mdecore.config.annot.Config;
import com.mattdahepic.mdecore.config.annot.Range;
import com.mattdahepic.mdecore.config.sync.ConfigProcessor;
import com.mattdahepic.mdecore.config.sync.ConfigSyncable;

public class RDConfig extends ConfigSyncable {
    @Config @Comment({"Should ringkydinks use items from the user's inventory to function?"}) public static boolean consumeItems = true;
    @Config @Comment({"The time interval (in ticks) at which items should be consumed to maintain ring function."}) @Range(min = 1,max = 12000) public static int consumeInterval = 600;
    @Config @Comment({"How many sugar should the speed ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int speedConsumeAmount = 1;
    @Config @Comment({"How many feathers should the flight ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int flightConsumeAmount = 1;
    @Config @Comment({"How many iron ingots should the magnet ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int magnetConsumeAmount = 1;
    @Config @Comment({"How many raw fish should the magnet ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int waterbreathingConsumeAmount = 1;
    @Config @Comment({"How many torches should the night vision ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int nightvisionConsumeAmount = 1;
    @Config @Comment({"How many items of food should the saturation ring consume every consumption interval?","Set to 0 to disable."}) @Range(min = 0,max = 64) public static int saturationConsumeAmount = 1;

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
