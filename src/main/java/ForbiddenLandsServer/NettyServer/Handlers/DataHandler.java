package ForbiddenLandsServer.NettyServer.Handlers;

import Common.protocol.Game.GameRequest;
import Common.protocol.Login.LoginRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class DataHandler extends SimpleChannelInboundHandler<GameRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, GameRequest gameRequest) throws Exception {

    }
}
