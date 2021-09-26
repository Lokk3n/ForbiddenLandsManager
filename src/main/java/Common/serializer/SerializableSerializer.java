package Common.serializer;


import org.apache.commons.lang3.SerializationUtils;

import java.io.IOException;
import java.io.Serializable;

public class SerializableSerializer implements Serializer {
    @Override
    public byte[] serialize(Object object) throws IOException {
        return SerializationUtils.serialize((Serializable) object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException {
        return null;
    }

    @Override
    public Object deserialize(byte[] bytes) throws IOException {
        return SerializationUtils.deserialize(bytes);
    }
}
