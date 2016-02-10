package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class DinkValues {
    public static final String NICE_NAME_TRANSLATION_KEY_PREFIX = "ringkydink.name.";

    private static final String TAG_DINK_TYPE = "dinktype"; //todo: move to be dink.type
    private static final String TAG_DINK_ENABLED = "dinkenabled"; //todo: change to dink.enabled
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

    public static boolean getEnabled (ItemStack stk) {
        return stk.getTagCompound().getBoolean(TAG_DINK_ENABLED);
    }
    public static void setEnabled (ItemStack stk, boolean enable) {
        stk.getTagCompound().setBoolean(TAG_DINK_ENABLED,enable);
    }

    public enum EnumDink {
        TEMPLATE(false,false,null),
        FLIGHT(false,true,DinkLevel.TIER3),
        LAVAWALK(false,false,DinkLevel.TIER1),
        WATERWALK(false,false,DinkLevel.TIER1),
        ANTIPOTION(false,true,DinkLevel.TIER2),
        EXTINGUISHER(false,true,DinkLevel.TIER2),
        MAGNET(false,true,DinkLevel.TIER2),
        WATERBREATHING(false,true,DinkLevel.TIER2),
        NIGHTVISION(false,true,DinkLevel.TIER2),
        SATURATION(false,true,DinkLevel.TIER2),
        SPEED(false,true,DinkLevel.TIER2);
        //CHEST(true,false,DinkLevel.TIER1),
        //ENDERCHEST(DinkLevel.TIER1),
        //CRAFTINGTABLE(DinkLevel.TIER1),
        //MOBDERPEARL(DinkLevel.TIER2); //like golden lasso

        public final boolean hasUseAbility;
        public final boolean constantItemConsumption;
        public final DinkLevel level;
        EnumDink (boolean useAbility, boolean constantItemConsumption, DinkLevel level) {
            this.constantItemConsumption = constantItemConsumption;
            this.hasUseAbility = useAbility;
            this.level = level;
        }
        public String getType () {
            return this.name().toLowerCase();
        }
        @Override public String toString () {
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
