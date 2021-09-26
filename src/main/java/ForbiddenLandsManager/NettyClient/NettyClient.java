package ForbiddenLandsManager.NettyClient;

import Common.endecode.Decoder;
import Common.endecode.Encoder;
import Common.protocol.PingPong.Ping;
import Common.protocol.Request;
import Common.protocol.Response;
import Common.serializer.SerializableSerializer;
import ForbiddenLandsManager.NettyClient.Handlers.ClientHandler;
import ForbiddenLandsManager.Utilities.WorkExecutor.IWorkExecutor;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class NettyClient {
    private EventLoopGroup eventLoopGroup;
    private Channel channel;
    private ClientHandler clientHandler;
    private String host;
    private Integer port;
    private Thread thread;

    private final Map<String, CompletableFuture<Response>> futureMap = new ConcurrentHashMap<>();
    IWorkExecutor executor;

    public NettyClient(String host, Integer port, IWorkExecutor executor) {
        this.host = host;
        this.port = port;
        this.executor = executor;
    }

    public void connect() throws InterruptedException {
        clientHandler = new ClientHandler(futureMap, executor);
        eventLoopGroup = new NioEventLoopGroup();
        //Startup class
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                 //Specifies the Channel used by the transport
                 .channel(NioSocketChannel.class)
                 .option(ChannelOption.SO_KEEPALIVE, true)
                 .option(ChannelOption.TCP_NODELAY, true)
                 .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                 .handler(new ChannelInitializer<SocketChannel>() {
                     @Override
                     protected void initChannel(SocketChannel ch) {
                         ChannelPipeline pipeline = ch.pipeline();
                         //Add encoder
                         pipeline.addLast(new Encoder(Request.class, new SerializableSerializer()));
                         //Add decoder
                         pipeline.addLast(new Decoder(Response.class, new SerializableSerializer()));
                         //Request processing class
                         pipeline.addLast(clientHandler);
                     }
                 });
        /**
         * Get Netty connections synchronously
         */
        channel = bootstrap.connect(host, port).sync().channel();
        thread = new Thread(() -> {
            while(channel.isActive()) {
                send(new Ping());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Ping thread stopping...");
        });
        thread.start();
    }

    /**
     * send message
     *
     * @param request
     * @return
     */
    public CompletableFuture<Response> send(Request request) {
        CompletableFuture<Response> channelFuture = new CompletableFuture<>();
        try {
            if(request.getRequestId() != null) {
                futureMap.put(request.getRequestId(), channelFuture);
            }
            channel.writeAndFlush(request).await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return channelFuture;
    }

    public void close() {
        eventLoopGroup.shutdownGracefully();
        channel.closeFuture().syncUninterruptibly();
    }
}
