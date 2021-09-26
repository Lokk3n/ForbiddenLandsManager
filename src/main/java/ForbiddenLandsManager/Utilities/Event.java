package ForbiddenLandsManager.Utilities;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Consumer;

public class Event<T>{

    private final HashMap<UUID, WeakReference<Consumer<T>>> actions = new HashMap<>();

    public void fire(T x){
        for(WeakReference<Consumer<T>> c : actions.values()){
            Objects.requireNonNull(c.get()).accept(x);
        }
    }

    public UUID subscribe(Consumer<T> r){
        UUID id = UUID.randomUUID();
        actions.put(id, new WeakReference<>(r));
        return id;
    }

    public void unsubscribe(UUID id){
        actions.remove(id);
    }
}
