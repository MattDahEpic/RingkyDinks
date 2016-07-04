package com.mattdahepic.ringkydinks.network.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSetStepHeight implements IMessageHandler<PacketSetStepHeight.SetStepHeightMessage,IMessage> {
    @Override
    public IMessage onMessage (SetStepHeightMessage msg, MessageContext ctx) {
        if (ctx.side.isClient()) {
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.stepHeight = msg.value;
            }
        }
        return null;
    }
    public static class SetStepHeightMessage implements IMessage {
        private float value;
        public SetStepHeightMessage () {}
        public SetStepHeightMessage (float value) {
            this.value = value;
        }
        @Override
        public void toBytes (ByteBuf buf) {
            buf.writeFloat(value);
        }
        @Override
        public void fromBytes (ByteBuf buf) {
            value = buf.readFloat();
        }
    }
}
