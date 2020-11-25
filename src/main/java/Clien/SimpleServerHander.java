package Clien;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

import java.nio.charset.Charset;

public class SimpleServerHander extends ChannelInboundHandlerAdapter {

    /**
     *   读取客户端通道的数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){

//        ByteBuf in = (ByteBuf) msg;
        if (msg instanceof ByteBuf){              //假如传来的数据是一个bytebuf
            System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
        }
//        try {
//            while (in.isReadable()){
//                System.out.println((char)in.readByte());
//                System.out.flush();
//            }
//        }finally {
//            ReferenceCountUtil.release(msg);
//        }

        ctx.channel().writeAndFlush("is ok");                             //告诉客户端我已读到数据
    }

}
