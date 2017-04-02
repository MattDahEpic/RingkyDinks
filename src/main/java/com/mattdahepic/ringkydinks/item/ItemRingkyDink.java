package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.dink.DinkNBT;
import com.mattdahepic.ringkydinks.dink.EnumDink;
import com.mattdahepic.ringkydinks.dink.ability.DinkAbilityMobderpearl;
import com.mattdahepic.ringkydinks.dink.ability.IDinkAbility;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public class ItemRingkyDink extends Item {
    public long lastUpdate = 0;
    public ItemRingkyDink () {
        this.setUnlocalizedName("ringkydink");
        this.setMaxStackSize(1);
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setCreativeTab(RingkyDinks.tab);
    }
    public boolean hasContainerItem (ItemStack stack) {
        return true;
    }
    public ItemStack getContainerItem (ItemStack stack) {
        return DinkNBT.getRingForDink(DinkNBT.getDinkType(stack));
    }
    public String getItemStackDisplayName(ItemStack stack) {
        return DinkNBT.getNiceDinkNameForTooltip(DinkNBT.getDinkType(stack))+I18n.format("item.ringkydink.suffix");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, NonNullList<ItemStack> subItems) {
        for (EnumDink d : EnumDink.values()) {
            if (d == EnumDink.TEMPLATE) continue;
            subItems.add(DinkNBT.getRingkyDinkOfType(d));
        }
    }
    @Override
    public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
        EnumDink dink = DinkNBT.getDinkType(stack);
        if (dink != null) {
            if (dink == EnumDink.MOBDERPEARL) {
                tooltip.add(DinkAbilityMobderpearl.getHasMob(stack) ? "Contains " + DinkAbilityMobderpearl.getMobName(stack) : "Empty");
            } else if (!dink.ability.hasUseAbility()) {
                tooltip.add(DinkNBT.getEnabled(stack) ? "Enabled" : "Disabled");
            }
        }
    }
    @Override
    public boolean hasEffect(ItemStack stack) {
        EnumDink dink = DinkNBT.getDinkType(stack);
        if (dink != null) {
            if (dink == EnumDink.MOBDERPEARL) {
                return DinkAbilityMobderpearl.getHasMob(stack);
            } else if (!dink.ability.hasUseAbility()) {
                return DinkNBT.getEnabled(stack);
            }
        }
        return false;
    }

    /* DINK ABILITIES */
    @Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entity, int itemSlot, boolean isSelected) {
        if (DinkNBT.getDinkType(stack) == null) return; //update safety
        IDinkAbility.onUpdate(DinkNBT.getDinkType(stack).ability,(EntityPlayer)entity,stack);
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        return IDinkAbility.onItemRightClick(DinkNBT.getDinkType(player.getHeldItem(hand)).ability,player,player.getHeldItem(hand),hand);
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        return IDinkAbility.itemInteractionForEntity(DinkNBT.getDinkType(stack).ability,player,stack,target,hand);
    }
    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return IDinkAbility.onItemUse(DinkNBT.getDinkType(player.getHeldItem(hand)).ability,player,player.getHeldItem(hand),pos,facing,hand);
    }
}
