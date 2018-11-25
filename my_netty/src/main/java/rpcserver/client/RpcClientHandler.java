package rpcserver.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcClientHandler extends SimpleChannelInboundHandler {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Object outputParam) throws Exception {
        System.out.println("client接受到的数据是：" + outputParam.toString());
    }
}
