package ForbiddenLandsServer.NettyServer.Handlers;

import Common.protocol.Login.LoginRequest;
import Common.protocol.PingPong.Ping;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LoginHandler extends SimpleChannelInboundHandler<LoginRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginRequest loginRequest)
            throws Exception {

    }
}
