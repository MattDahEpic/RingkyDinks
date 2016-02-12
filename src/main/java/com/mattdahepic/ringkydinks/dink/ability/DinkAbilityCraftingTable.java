package com.mattdahepic.ringkydinks.dink.ability;

import net.minecraft.block.BlockWorkbench;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.world.World;

public class DinkAbilityCraftingTable {
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
