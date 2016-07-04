/*package com.mattdahepic.ringkydinks.item.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;

public class ContainerRubberHand extends Container {
    public ContainerRubberHand (InventoryPlayer player,) {
        //TODO
        addSlotToContainer(new Slot(inv,0,49,93)); //thumb
        addSlotToContainer(new Slot(inv,1,53,56)); //index
        addSlotToContainer(new Slot(inv,2,68,36)); //middle
        addSlotToContainer(new Slot(inv,3,90,43)); //ring (kydink)
        addSlotToContainer(new Slot(inv,4,111,52)); //pinkie
        bindPlayerInventory(player);
    }
    @Override
    public boolean canInteractWith (EntityPlayer p) {return true;}
    protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                addSlotToContainer(new Slot(inventoryPlayer, j + i * 9 + 9,
                        8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; i++) {
            addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
        }
    }
}8=*/
