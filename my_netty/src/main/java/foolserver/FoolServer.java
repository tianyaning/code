package foolserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class FoolServer {
    int port;

    public FoolServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        //服务端的应用，需要2个 NioEventLoopGroup
        //第一个经常被叫做boss，用来接收进来的连接。
        //第二个经常被叫做worker，用来处理已经被接收的连接，一旦‘boss’接收到连接，就会把连接信息注册到‘worker’上。
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动 NIO 服务的辅助启动类
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new FoolServerHandler());
                        }
                    })
                    //socket的标准参数，并不是netty自己的
                    //标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //是否启用心跳保活机制
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 绑定端口，开始接收连接
            ChannelFuture f = serverBootstrap.bind(port).sync();

            // 等待服务器  socket 关闭
            f.channel().closeFuture().sync();

        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new FoolServer(port).run();
    }
}
