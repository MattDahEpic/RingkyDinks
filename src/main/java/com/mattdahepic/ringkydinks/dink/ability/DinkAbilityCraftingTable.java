package com.mattdahepic.ringkydinks.dink.ability;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DinkAbilityCraftingTable extends IDinkAbility {
    public boolean hasUseAbility () {return true;}
    public boolean constantItemConsumption () {return false;}
    public boolean consumesItems () {return false;}
    public void enable (EntityPlayer p, ItemStack s) {}
    public void disable (EntityPlayer p, ItemStack s) {}
    public ItemStack getConsumeItem (ItemStack i) {
        return null;
    }
    public void onClick (EntityPlayer player, ItemStack s, EnumHand h) {
        player.displayGui(new DinkAbilityCraftingTable.InterfaceFakeCraftingTable(player.world));
    }
    public void tick (EntityPlayer player, ItemStack stack) {}
    public EnumActionResult onBlockClick (EntityPlayer p, ItemStack s, BlockPos pos, EnumFacing side, EnumHand h) {return EnumActionResult.PASS;}
    public boolean onEntityClick (EntityPlayer p, ItemStack s, EntityLivingBase t, EnumHand h) {return false;}

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
