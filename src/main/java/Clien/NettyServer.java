package Clien;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    public static void main(String[] args) throws InterruptedException {
        //创建启动netty辅助类
        ServerBootstrap bootstrap = new ServerBootstrap();

        //1.绑定两个线程组 分别用来处理客户端的读写事件、accpet事件
        EventLoopGroup parentGroup = new NioEventLoopGroup();                     //用来处理accpet事件
        EventLoopGroup childGroup = new NioEventLoopGroup();                      //子线程组 用来处理通过的读写事件
        bootstrap.group(parentGroup,childGroup);

        //2.绑定服务端通道NioServerSocketChannel
        bootstrap.channel(NioServerSocketChannel.class);

        //3.给读写事件的线程通道绑定Handel去真正处理读写                                 //给子线程组绑定Handel
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {         //ChannelInitializer接口用来通道初始化
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(new SimpleServerHander());            //在管道newHandel
            }
        });

        //4.监听端口
        ChannelFuture future = bootstrap.bind(8080).sync();              //绑定端口
        future.channel().closeFuture().sync();                                  //指定通道 当通道关闭了继续走

    }
}
