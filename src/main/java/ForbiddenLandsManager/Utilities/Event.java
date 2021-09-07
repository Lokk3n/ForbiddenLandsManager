package ForbiddenLandsManager.Utilities;

import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

public class Event<T>{

    private final HashMap<UUID, Consumer<?>> actions = new HashMap<>();

    public void fire(T x){
        for(Consumer c : actions.values()){
            c.accept(x);
        }
    }

    public UUID subscribe(Consumer<?> r){
        UUID id = UUID.randomUUID();
        actions.put(id, r);
        return id;
    }

    public void unsubscribe(UUID id){
        actions.remove(id);
    }
}
