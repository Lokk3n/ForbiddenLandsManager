package Common.serializer;

import java.io.IOException;

public interface Serializer {
    /**
     * java Object to binary
     *
     * @param object
     * @return
     */
    byte[] serialize(Object object) throws IOException;

    /**
     * Binary to java object
     *
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes) throws IOException;


    Object deserialize(byte[] bytes) throws IOException;
}
