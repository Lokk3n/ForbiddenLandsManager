package Common.serializer;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

public class JSONSerializer implements Serializer{

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        return null;
    }
}
