package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class DinkValues {
    public static final String TAG_DINK_TYPE = "dinktype";
    public static String getDinkType (ItemStack stack) {
        return (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_DINK_TYPE, Constants.NBT.TAG_STRING)) ? stack.getTagCompound().getString(TAG_DINK_TYPE) : null;
    }
    public static ItemStack getDinkOfType (EnumDink dink) {
        ItemStack ret = new ItemStack(RingkyDinks.dink,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(TAG_DINK_TYPE, dink.type);
        ret.setTagCompound(tag);
        return ret;
    }
    public static ItemStack getRingkyDinkOfType (EnumDink dink) {
        ItemStack ret = new ItemStack(RingkyDinks.ringkydink,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(TAG_DINK_TYPE,dink.type);
        ret.setTagCompound(tag);
        return ret;
    }
    public static ItemStack getRingOfLevel (DinkLevel lvl) {
        return new ItemStack(RingkyDinks.ring,1,lvl.meta);
    }
    public static ItemStack getRingForDink (EnumDink dink) {
        return getRingOfLevel(dink.level);
    }

    public enum EnumDink {
        TEMPLATE("template",null),
        FLIGHT("flight",DinkLevel.TIER3),
        LAVAWALK("lavawalk",DinkLevel.TIER1),
        WATERWALK("waterwalk",DinkLevel.TIER1),
        ANTIPOTION("antipotion",DinkLevel.TIER2),
        EXTINGUISHER("extinguisher",DinkLevel.TIER2),
        MAGNET("magnet",DinkLevel.TIER2),
        WATERBREATHING("waterbreathing",DinkLevel.TIER2),
        NIGHTVISION("nightvision",DinkLevel.TIER2),
        SATURATION("saturation",DinkLevel.TIER2);
        //CHEST(?,"chest",DinkLevel.TIER1),
        //ENDERCHEST(?,"enderchest",DinkLevel.TIER1);

        public final String type;
        public final DinkLevel level;
        EnumDink (String type,DinkLevel level) {
            this.type = type;
            this.level = level;
        }
    }
    public enum DinkLevel {
        TIER1(0),
        TIER2(1),
        TIER3(2);

        public final int meta;
        DinkLevel (int meta) {
            this.meta = meta;
        }
    }
}
