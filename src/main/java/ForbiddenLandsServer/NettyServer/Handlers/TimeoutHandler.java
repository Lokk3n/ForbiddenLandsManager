package ForbiddenLandsServer.NettyServer.Handlers;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.ReadTimeoutException;

public class TimeoutHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception{
        if(cause instanceof ReadTimeoutException){
            System.out.println("Timeout reached");
            ctx.pipeline().close();
            ctx.close();
        }
    }
}
