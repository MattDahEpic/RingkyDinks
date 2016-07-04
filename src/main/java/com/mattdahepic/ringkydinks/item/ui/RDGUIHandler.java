/*package com.mattdahepic.ringkydinks.item.ui;

import com.mattdahepic.ringkydinks.item.container.ContainerRubberHand;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class RDGUIHandler implements IGuiHandler {
    @Override
    public Object getServerGuiElement (int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 0: //rubber hand
                return new ContainerRubberHand(player.inventory);
                break;
            case 1: //chest dink
                //todo
                break;
            default:
                return null;
        }
    }
    @Override
    public Object getClientGuiElement (int id, EntityPlayer player, World world, int x, int y, int z) {
        switch (id) {
            case 0: //rubber hand
                return new GUIRubberHand();
                break;
            case 1: //chest dink
                //todo
                break;
            default:
                return null;
        }
    }
}*/