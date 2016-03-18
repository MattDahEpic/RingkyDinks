package com.mattdahepic.ringkydinks.dink.ability;

import com.mattdahepic.mdecore.helpers.PlayerHelper;
import com.mattdahepic.mdecore.helpers.RandomHelper;
import com.mattdahepic.mdecore.network.PacketHandler;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DinkAbilityEpicBattleMusic extends IDinkAbility {
    public boolean hasUseAbility () {return true;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void disable (EntityPlayer player, ItemStack stack) {}
    public ItemStack getConsumeItem (ItemStack i) {
        return null;
    }
    public void onClick (EntityPlayer player, ItemStack stack) {
        EnumEpicBattleMusics music = musics.get(RandomHelper.randomIntInRange(0,musics.size()));
        player.addChatMessage(new ChatComponentText("Now Playing: "+music.title+" by "+music.artist+(music.album != null ? " on "+music.album+".":".")));
        //PacketHandler.net.sendTo(new PacketPlaySoundFollowPlayer.Message(music.sound), PlayerHelper.getPlayerFromUsername(player.getName())); //TODO: renable
    }
    public void tick (EntityPlayer player, ItemStack stack) {}
    public boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {return false;}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target) {return false;}

    private static final List<EnumEpicBattleMusics> musics = Collections.unmodifiableList(Arrays.asList(EnumEpicBattleMusics.values()));
    public enum EnumEpicBattleMusics {
        ROLLERMOBSTER("ringkydinks:roller_mobster","Roller Mobster","Carpenter Brut","Hacknet OST");

        public final String sound;
        public final String title;
        public final String artist;
        public final String album;
        EnumEpicBattleMusics (String sound, String title, String artist, @Nullable String album) {
            this.sound = sound;
            this.title = title;
            this.artist = artist;
            this.album = album;
        }
    }
}
