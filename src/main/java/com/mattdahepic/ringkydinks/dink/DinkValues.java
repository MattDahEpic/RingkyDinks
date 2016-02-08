package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class DinkValues {
    public static final String TAG_DINK_TYPE = "dinktype";
    public static EnumDink getDinkType (ItemStack stack) {
        return (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_DINK_TYPE, Constants.NBT.TAG_STRING)) ? EnumDink.valueOf(EnumDink.class,stack.getTagCompound().getString(TAG_DINK_TYPE).toUpperCase()) : null;
    }
    public static ItemStack getDinkOfType (EnumDink dink) {
        ItemStack ret = new ItemStack(RingkyDinks.dink,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(TAG_DINK_TYPE, dink.getType());
        ret.setTagCompound(tag);
        return ret;
    }
    public static ItemStack getRingkyDinkOfType (EnumDink dink) {
        ItemStack ret = new ItemStack(RingkyDinks.ringkydink,1,0);
        NBTTagCompound tag = new NBTTagCompound();
        tag.setString(TAG_DINK_TYPE, dink.getType());
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
        TEMPLATE(null),
        FLIGHT(DinkLevel.TIER3),
        LAVAWALK(DinkLevel.TIER1),
        WATERWALK(DinkLevel.TIER1),
        ANTIPOTION(DinkLevel.TIER2),
        EXTINGUISHER(DinkLevel.TIER2),
        MAGNET(DinkLevel.TIER2),
        WATERBREATHING(DinkLevel.TIER2),
        NIGHTVISION(DinkLevel.TIER2),
        SATURATION(DinkLevel.TIER2);
        //CHEST(?,"chest",DinkLevel.TIER1),
        //ENDERCHEST(?,"enderchest",DinkLevel.TIER1);

        public final DinkLevel level;
        EnumDink (DinkLevel level) {
            this.level = level;
        }
        public String getType () {
            return this.name().toLowerCase();
        }
        @Override
        public String toString () {
            return this.getType();
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
