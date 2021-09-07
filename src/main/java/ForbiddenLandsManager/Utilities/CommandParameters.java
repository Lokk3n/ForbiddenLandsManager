package ForbiddenLandsManager.Utilities;

import java.util.HashMap;

public class CommandParameters {
    HashMap<String, Object> parameters = new HashMap<>();

    public void put(String key, Object parameter){
        parameters.put(key, parameter);
    }

    public Object get(String key){
        return parameters.get(key);
    }
}
