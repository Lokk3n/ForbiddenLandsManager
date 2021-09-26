package ForbiddenLandsManager.Model;

import ForbiddenLandsManager.NettyClient.NettyClient;
import ForbiddenLandsManager.Utilities.WorkExecutor.WorkExecutor;

public class ModelFactory {
    NettyClient server;

    public ModelFactory(NettyClient server){
        try {
            server.connect();
            System.out.println("connecting...");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
