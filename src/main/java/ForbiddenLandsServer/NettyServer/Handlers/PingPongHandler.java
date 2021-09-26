package ForbiddenLandsServer.NettyServer.Handlers;

import Common.protocol.PingPong.Ping;
import Common.protocol.PingPong.Pong;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class PingPongHandler extends SimpleChannelInboundHandler<Ping> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Ping ping) throws Exception {
        ctx.writeAndFlush(new Pong(ping.getRequestId()));
    }
}
