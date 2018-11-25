package rpcserver.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import rpcserver.common.InputParam;
import rpcserver.common.OutputParam;

public class RpcServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        InputParam inputParam = (InputParam) msg;
        System.out.println("server 接受到的数据是 " + inputParam.toString());

        OutputParam outputParam = new OutputParam();
        outputParam.setStr1("第一个数是："+String.valueOf(inputParam.getNum1()));
        outputParam.setStr2("第二个数是："+String.valueOf(inputParam.getNum2()));
        ctx.writeAndFlush(outputParam);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
