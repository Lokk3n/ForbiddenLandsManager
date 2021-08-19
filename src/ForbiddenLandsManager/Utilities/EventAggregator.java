package ForbiddenLandsManager.Utilities;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EventAggregator {
    private static HashMap<Class<? extends Event<?>>, Event<?>> eventMap = new HashMap<>();

    public static <T extends Event<?>> T getEvent(Class<T> key) {
        if(eventMap.containsKey(key)) return (T) eventMap.get(key);
        else{
            try {
                Event<?> ev = key.getDeclaredConstructor().newInstance();
                eventMap.put(key, ev);
                return (T) ev;
            }
            catch(Exception e){
                System.out.println("Unknown event type requested");
                return null;
            }
        }
    }

}
