package rpcserver.common.Protocol;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import rpcserver.common.InputParam;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ServerMessageDecoder extends MessageToMessageDecoder<TcpPackageProtocol> {
    @Override
    protected void decode(ChannelHandlerContext ctx, TcpPackageProtocol msg, List<Object> out) throws Exception {
        byte[] bytes = msg.getMessage();
        ObjectInputStream inputStream = new ObjectInputStream(new ByteArrayInputStream(bytes));
        InputParam inputParam = (InputParam)inputStream.readObject();
        out.add(inputParam);
    }
}
