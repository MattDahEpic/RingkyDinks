package com.mattdahepic.ringkydinks.dink.ability;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class DinkAbilityCraftingTable extends IDinkAbility {
    public boolean hasUseAbility () {return true;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer player, ItemStack stack) {}
    public void disable (EntityPlayer player, ItemStack stack) {}
    public ItemStack getConsumeItem (ItemStack i) {
        return null;
    }
    public void onClick (EntityPlayer player, ItemStack stack) {
        player.displayGui(new DinkAbilityCraftingTable.InterfaceFakeCraftingTable(player.worldObj));
    }
    public void tick (EntityPlayer player, ItemStack stack) {}
    public boolean onBlockClick (EntityPlayer player, ItemStack stack, BlockPos pos, EnumFacing side) {return false;}
    public boolean onEntityClick (EntityPlayer player, ItemStack stack, EntityLivingBase target) {return false;}

    public static class InterfaceFakeCraftingTable extends BlockWorkbench.InterfaceCraftingTable {
        private final World world;
        public InterfaceFakeCraftingTable(World world) {
            super(world,null);
            this.world = world;
        }
        @Override
        public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
            return new ContainerFakeWorkbench(playerInventory,world);
        }
    }
    public static class ContainerFakeWorkbench extends ContainerWorkbench {
        public ContainerFakeWorkbench (InventoryPlayer playerInv,World world) {
            super(playerInv,world,null);
        }
        @Override
        public boolean canInteractWith(EntityPlayer playerIn) {
            return true;
        }
    }
}
