package ForbiddenLandsServer.NettyServer;

import Common.endecode.Decoder;
import Common.endecode.Encoder;
import Common.protocol.Request;
import Common.protocol.Response;
import Common.serializer.JSONSerializer;
import ForbiddenLandsServer.NettyServer.Handlers.DiscardHandler;
import ForbiddenLandsServer.NettyServer.Handlers.PipelineChoiceHandler;
import ForbiddenLandsServer.NettyServer.Handlers.TimeoutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyServer{
    //private ServerHandler serverHandler;

    private EventLoopGroup boss;
    private EventLoopGroup worker;

    private Integer serverPort;


    public NettyServer(Integer serverPort) {
        //this.serverHandler = serverHandler;
        this.serverPort = serverPort;
    }


    public void start() throws Exception {
        //Thread pool responsible for handling client connections
        boss = new NioEventLoopGroup();
        //Thread pool responsible for processing read and write operations
        worker = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                           .channel(NioServerSocketChannel.class)
                           .option(ChannelOption.SO_BACKLOG, 1024)
                           .childHandler(new ChannelInitializer<SocketChannel>() {
                               @Override
                               protected void initChannel(SocketChannel ch) throws Exception {
                                   ChannelPipeline pipeline = ch.pipeline();
                                   //Add timeout handler
                                   pipeline.addLast(new ReadTimeoutHandler(5));
                                   //Add decoder
                                   pipeline.addLast(new Encoder(Response.class, new JSONSerializer()));
                                   //Add encoder
                                   pipeline.addLast(new Decoder(Request.class, new JSONSerializer()));
                                   //Add request processor
                                   pipeline.addLast(new PipelineChoiceHandler());
                                   //Dodane w celu inicjalizacji
                                   pipeline.addLast("follower", new DiscardHandler());
                                   //Zamknięcie kanału jeśli ReadTimeoutHandler rzuci wyjątkiem
                                   pipeline.addLast("timeout", new TimeoutHandler());

                               }
                           });
            //bind(serverBootstrap, serverPort);
            ChannelFuture future = serverBootstrap.bind(serverPort);
            future.addListener(f -> {
                if (f.isSuccess()) {
                    System.out.println("port binding succeeded: " + serverPort);
                } else {
                    System.out.println("port binding failed" + serverPort);
                }
            });
            future.channel().closeFuture().sync();
        }
        finally {
            close();
        }
    }



    public void close() throws InterruptedException {
        boss.shutdownGracefully().sync();
        worker.shutdownGracefully().sync();
        System.out.println("close Netty");
    }
}
