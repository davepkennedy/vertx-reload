package workout.scope;

import com.google.inject.AbstractModule;

public class HotReloadModule extends AbstractModule {

    private final HotReloadScope scope = new HotReloadScope();

    @Override
    protected void configure() {
        bindScope(HotReload.class, scope);
        bind (HotReloader.class).toInstance(scope::reload);
    }
}
