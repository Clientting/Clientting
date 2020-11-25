package Service;

import Clien.SimpleServerHander;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //启动netty辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //线程组 处理客户端的读写、accent事件
        EventLoopGroup parentGroup = new NioEventLoopGroup();
        EventLoopGroup childGroup = new NioEventLoopGroup();
        bootstrap.group(parentGroup,childGroup);

        //绑定服务端通道
        bootstrap.channel(NioServerSocketChannel.class);

        //给读写线程绑定hander
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SinpleServerHander());
            }
        });

        //监听端口
        ChannelFuture future = bootstrap.bind(8081);
        future.channel().closeFuture().sync();
    }
}
