package rpcserver.common.MessagePack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;
import rpcserver.common.InputParam;

import java.util.List;

public class ServerMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        //获取要解码的byte数组
        final byte[] array;
        final int length = msg.readableBytes();
        array = new byte[length];
        //调用MessagePack 的read方法将其反序列化为Object对象
        msg.getBytes(msg.readerIndex(), array, 0, length);
        MessagePack msgpack = new MessagePack();
        InputParam inputParam = msgpack.read(array, InputParam.class);
        out.add(inputParam);
    }


}
