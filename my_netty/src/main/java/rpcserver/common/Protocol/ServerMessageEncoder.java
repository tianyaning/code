package rpcserver.common.Protocol;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import rpcserver.common.OutputParam;

import java.io.ObjectOutputStream;
import java.util.List;

public class ServerMessageEncoder extends MessageToMessageEncoder<OutputParam> {
    @Override
    protected void encode(ChannelHandlerContext ctx, OutputParam msg, List<Object> out) throws Exception {
        TcpPackageProtocol protocol = new TcpPackageProtocol();
        protocol.setHead(TcpPackageProtocolHead.head);

        ByteBuf byteBuf = Unpooled.buffer();
        ObjectOutputStream o = new ObjectOutputStream(new ByteBufOutputStream(byteBuf));
        o.writeObject(msg);

        int size = byteBuf.readableBytes();
        byte[] req = new byte[size];
        byteBuf.readBytes(req);
        protocol.setMessageLength(size);
        protocol.setMessage(req);
        out.add(protocol);
    }
}
