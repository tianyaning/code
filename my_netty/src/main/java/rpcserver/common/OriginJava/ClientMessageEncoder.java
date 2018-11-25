package rpcserver.common.OriginJava;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import rpcserver.common.InputParam;

import java.io.ObjectOutputStream;

public class ClientMessageEncoder extends MessageToByteEncoder<InputParam> {

    @Override
    protected void encode(ChannelHandlerContext ctx, InputParam msg, ByteBuf out) throws Exception {
        ObjectOutputStream o = new ObjectOutputStream(new ByteBufOutputStream(out));
        o.writeObject(msg);
    }
}
