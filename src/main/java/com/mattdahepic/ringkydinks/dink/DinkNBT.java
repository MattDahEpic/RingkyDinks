package com.mattdahepic.ringkydinks.dink;

import com.mattdahepic.mdecore.helpers.TranslationHelper;
import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.Constants;

public class DinkNBT {
    private static final String NICE_NAME_TRANSLATION_KEY_PREFIX = "ringkydink.name.";
    private static final String TAG_DINK_TYPE = "dink.type";
    private static final String TAG_DINK_ENABLED = "dink.enabled";

    public static EnumDink getDinkType (ItemStack stack) {
        return (stack.hasTagCompound() && stack.getTagCompound().hasKey(TAG_DINK_TYPE, Constants.NBT.TAG_STRING)) ? EnumDink.valueOf(EnumDink.class, stack.getTagCompound().getString(TAG_DINK_TYPE).toUpperCase()) : null;
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
    public static ItemStack getRingOfLevel (EnumDink.EnumRing lvl) {
        return new ItemStack(RingkyDinks.ring,1,lvl.meta);
    }
    public static ItemStack getRingForDink (EnumDink dink) {
        return getRingOfLevel(dink.ring);
    }

    public static boolean getEnabled (ItemStack stk) {
        return stk.getTagCompound().getBoolean(TAG_DINK_ENABLED);
    }
    public static void setEnabled (ItemStack stk, boolean enable) {
        stk.getTagCompound().setBoolean(TAG_DINK_ENABLED,enable);
    }

    public static String getNiceDinkNameForTooltip (EnumDink dink) {
        return TranslationHelper.getTranslatedString(NICE_NAME_TRANSLATION_KEY_PREFIX+dink.getType());
    }
}
