package foolserver;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class FoolServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        //没用指定decoder和encoder，就用byteBuf来解析
        ByteBuf in = (ByteBuf) msg;
        try {
            while (in.isReadable()) {
                //将读取到的数据打印出来
                System.out.print((char) in.readByte());
//                System.out.flush();
            }
        } finally {
            ((ByteBuf) msg).release();
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
