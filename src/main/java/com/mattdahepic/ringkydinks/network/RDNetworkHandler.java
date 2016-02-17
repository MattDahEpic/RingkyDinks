package com.mattdahepic.ringkydinks.network;

import com.mattdahepic.ringkydinks.RingkyDinks;
import com.mattdahepic.ringkydinks.network.packet.PacketSetStepHeight;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class RDNetworkHandler {
    public static SimpleNetworkWrapper net;
    public static void initPackets () {
        net = NetworkRegistry.INSTANCE.newSimpleChannel(RingkyDinks.MODID.toUpperCase());
        registerPacket(PacketSetStepHeight.class,PacketSetStepHeight.SetStepHeightMessage.class,Side.CLIENT);
    }
    private static int packetId = 0;
    private static void registerPacket (Class packet,Class message,Side recieving) {
        net.registerMessage(packet,message,packetId,recieving);
        packetId++;
    }
}
