package com.mattdahepic.ringkydinks.item;

import com.mattdahepic.ringkydinks.RingkyDinks;
import net.minecraft.item.Item;

public class ItemRingkyDinkPouch extends Item {
    public ItemRingkyDinkPouch () {
        this.setUnlocalizedName("ringkydink_pouch");
        this.setCreativeTab(RingkyDinks.tab);
        this.setMaxStackSize(1);
        this.setMaxDamage(0);
    }
    /*@Override
    public void addInformation(ItemStack stack, EntityPlayer player, List<String> tooltip, boolean advanced) {
        if () { //has rings
            tooltip.add(I18n.format("ringkydink.pouch.full"));
            for () { //all dinks
                tooltip.add(DinkNBT.getNiceDinkNameForTooltip(DinkNBT.getDinkType(stack)));
            }
        } else {
            tooltip.add(I18n.format("ringkydink.pouch.empty"));
        }
    }
    @Override
    public void onUpdate(ItemStack stack, World world, Entity entity, int slot, boolean isSelected) {
        //TODO: tick all items in the pack
    }
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand) {
        if (player.isSneaking()) {
            player.openGui(RingkyDinks.instance,0,world,player.serverPosX,player.serverPosY,player.serverPosZ);
        }
        return stack;
    }*/
}
