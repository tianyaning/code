package rpcserver.common.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;
import rpcserver.common.OutputParam;

public class ServerMessageEncoder extends MessageToByteEncoder<OutputParam> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OutputParam msg, ByteBuf out) throws Exception {
        MessagePack msgpack = new MessagePack();
        out.writeBytes(msgpack.write(msg));
    }
}
