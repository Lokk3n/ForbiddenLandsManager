package Common.endecode;

import Common.serializer.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class Decoder extends ByteToMessageDecoder {
    private Class<?> clazz;
    private Serializer serializer;

    public Decoder(Class<?> clazz, Serializer serializer) {
        this.clazz = clazz;
        this.serializer = serializer;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        //Because before encoding, write an Int type, 4 bytes to indicate the length
        if (byteBuf.readableBytes() < 4) {
            return;
        }

        //Mark the current read position
        byteBuf.markReaderIndex();
        int dataLength = byteBuf.readInt();
        if (byteBuf.readableBytes() < dataLength) {
            byteBuf.resetReaderIndex();
            return;
        }

        byte[] data = new byte[dataLength];
        //Read the data in byteBuf into the data byte array
        byteBuf.readBytes(data);
        Object obj = serializer.deserialize(data);
        list.add(obj);
    }
}
