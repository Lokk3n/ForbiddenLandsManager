package ForbiddenLandsManager.NettyClient.Handlers;

import Common.protocol.Response;
import ForbiddenLandsManager.Utilities.WorkExecutor.IWorkExecutor;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ClientHandler extends ChannelDuplexHandler {
    /**
     * Using Map to maintain the mapping relationship between request object ID and response result Future
     */
    private final Map<String, CompletableFuture<Response>> futureMap;
    private final IWorkExecutor executor;

    public ClientHandler(Map<String, CompletableFuture<Response>> futureMap, IWorkExecutor executor){
        this.futureMap = futureMap;
        this.executor = executor;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Response) {
            //Get response object
            Response response = (Response) msg;
            executor.run(futureMap.get(response.getRequestId()), response);
            futureMap.remove(response.getRequestId());
        }
        super.channelRead(ctx, msg);
    }



    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }


}