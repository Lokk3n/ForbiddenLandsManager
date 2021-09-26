package ForbiddenLandsManager.NettyClient.Handlers;

import Common.protocol.Login.LoginRequest;
import Common.protocol.Login.LoginResponse;
import Common.protocol.Response;
import ForbiddenLandsManager.Utilities.WorkExecutor.IWorkExecutor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {
    private final Map<String, CompletableFuture<Response>> futureMap;
    private final IWorkExecutor executor;

    public LoginHandler(Map<String, CompletableFuture<Response>> futureMap, IWorkExecutor executor){
        this.futureMap = futureMap;
        this.executor = executor;
    }

    @Override
    public void channelRead0(ChannelHandlerContext ctx, LoginResponse response) throws Exception {
        executor.run(futureMap.get(response.getRequestId()), response);
        futureMap.remove(response.getRequestId());
    }
}

