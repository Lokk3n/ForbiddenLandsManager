package ForbiddenLandsManager.Utilities;

import Common.protocol.Login.LoginRequest;
import Common.protocol.Login.LoginResponse;
import Common.protocol.Response;
import ForbiddenLandsManager.NettyClient.NettyClient;
import com.alibaba.fastjson.support.hsf.HSFJSONUtils;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class ServerProxy {
    final NettyClient server;
    final SessionInstance session;

    public ServerProxy(NettyClient server, SessionInstance session) {
        this.server = server;
        this.session = session;
    }

    public CompletableFuture<Response> requestLogin(String login, String password){
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setRequestId(UUID.randomUUID().toString());
        loginRequest.setUsername(login);
        loginRequest.setPassword(password);
        System.out.println(login);
        System.out.println(password);
        CompletableFuture<Response> future = server.send(loginRequest);
//        future.thenApply(response -> {
//            System.out.println("Pierwszy callback haha");
//            LoginResponse loginResponse = (LoginResponse) response;
//            session.setToken(loginResponse.getToken());
//            session.setUserName(login);
//            return response;
//        });
        return future.thenApply(response -> {
            LoginResponse loginResponse = (LoginResponse) response;
            session.setToken(loginResponse.getToken());
            session.setUserName(login);
            System.out.println("Pierwszy callback " + loginResponse.getToken());
            return response;
        });
    }
}
