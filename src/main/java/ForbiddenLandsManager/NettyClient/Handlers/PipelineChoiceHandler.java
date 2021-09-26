package ForbiddenLandsManager.NettyClient.Handlers;

import Common.protocol.Game.GameResponse;
import Common.protocol.Login.LoginResponse;
import Common.protocol.Response;
import ForbiddenLandsManager.Utilities.WorkExecutor.IWorkExecutor;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.ReferenceCountUtil;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class PipelineChoiceHandler extends SimpleChannelInboundHandler<Response> {
    private final Map<String, CompletableFuture<Response>> futureMap;
    private final IWorkExecutor executor;

    public PipelineChoiceHandler(
            Map<String, CompletableFuture<Response>> futureMap,
            IWorkExecutor executor) {
        this.futureMap = futureMap;
        this.executor = executor;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Response response) throws Exception {
        // wybór drogi w zależności od rodzaju requestu. Ping służy utrzymaniu połączenia i odpowiada tylko pong,
        // Login nie musi zawierać tokenu więc będzie rozpatrywany osobno, Pozostałę requesty osobno
        ChannelPipeline pipe = ctx.pipeline();
        ChannelHandler follower;
        if(response instanceof LoginResponse){
            follower = new LoginHandler(futureMap, executor);
        }
        else if(response instanceof GameResponse){
            follower = new ClientHandler(futureMap, executor);
        }
        else{
            follower = new DiscardHandler();
        }


        pipe.replace("follower", "follower", follower);
        ReferenceCountUtil.retain(response); // aby powstrzymać przed zwolnieniem zasobów i przekazać do następnego handlera
        ctx.fireChannelRead(response);
    }
}
