package com.Mellenium.Addons.common.network;

import com.Mellenium.Addons.common.container.ContainerDecrypter;
import com.Mellenium.Addons.common.tileentity.TileDecrypter;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;

public class ButtonPacket2 implements IMessage {
    public static final byte ID_DECRYPTER = 0;
    byte buttonId = 0;
    boolean state = false;

    public ButtonPacket2() {
    }

    public ButtonPacket2(byte buttonId, boolean state) {
        this.buttonId = buttonId;
        this.state = state;
    }

    @Override
    public void fromBytes(ByteBuf bytes) {
        this.buttonId = bytes.readByte();
        this.state = bytes.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf bytes) {
        bytes.writeByte(buttonId);
        bytes.writeBoolean(state);
    }

    public static class Handler2 implements IMessageHandler<ButtonPacket2, IMessage> {

        @Override
        public IMessage onMessage(ButtonPacket2 message, MessageContext ctx) {
            switch (message.buttonId) {
                case ID_DECRYPTER: {
                    Container container = ctx.getServerHandler().playerEntity.openContainer;
                    if (container != null && container instanceof ContainerDecrypter) {
                        TileDecrypter tile = ((ContainerDecrypter) container).getTile();
                        tile.buttonClick(ctx.getServerHandler().playerEntity);
                    }
                    break;
                }
                default:
                    break;
            }
            return null;
        }
    }
}