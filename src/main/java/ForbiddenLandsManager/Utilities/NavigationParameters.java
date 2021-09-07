package ForbiddenLandsManager.Utilities;

import java.util.HashMap;

public class NavigationParameters {
    private HashMap<String, Object> parameters;

    public Object get(String key){
        return parameters.get(key);
    }
}
