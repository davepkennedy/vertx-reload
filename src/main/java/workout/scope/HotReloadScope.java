package workout.scope;

import com.google.inject.Key;
import com.google.inject.Provider;
import com.google.inject.Scope;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HotReloadScope implements Scope {

    private final Map<Key<?>, Object> cache = new ConcurrentHashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Provider<T> scope(Key<T> key, Provider<T> unscoped) {
        return () -> (T)cache.computeIfAbsent(key, k -> unscoped.get());
    }

    public void reload () {
        cache.clear();
    }
}
