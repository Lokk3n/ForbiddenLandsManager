package ForbiddenLandsServer.NettyServer.Handlers;

import Common.protocol.Game.GameRequest;
import Common.protocol.Login.LoginRequest;
import Common.protocol.PingPong.Ping;
import Common.protocol.PingPong.Pong;
import Common.protocol.Request;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

public class PipelineChoiceHandler extends SimpleChannelInboundHandler<Request> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        // wybór drogi w zależności od rodzaju requestu. Ping służy utrzymaniu połączenia i odpowiada tylko pong,
        // Login nie musi zawierać tokenu więc będzie rozpatrywany osobno, Pozostałę requesty osobno
        ChannelPipeline pipe = ctx.pipeline();
        ChannelHandler follower;
        if(request instanceof Ping){
            follower =  new PingPongHandler();
        }
        else if(request instanceof LoginRequest){
            follower = new LoginHandler();
        }
        else if(request instanceof GameRequest){
            follower = new DataHandler();
        }
        else{
            follower = new DiscardHandler();
        }


        pipe.replace("follower", "follower", follower);
        ReferenceCountUtil.retain(request); // aby powstrzymać przed zwolnieniem zasobów i przekazać do następnego handlera
    }
}