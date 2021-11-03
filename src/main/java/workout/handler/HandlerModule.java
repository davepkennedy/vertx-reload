package workout.handler;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;
import workout.Application;
import workout.scope.HotReload;
import workout.scope.HotReloader;

public class HandlerModule extends AbstractModule {
    @ProvidesIntoSet
    @HotReload
    public Handler provideSimpleHandler() {
        return new SimpleHandler();
    }

    @ProvidesIntoSet
    @HotReload
    public Handler provideResetHandler(
            HotReloader hotReloader,
            Application application
    ) {
        return new ResetHandler(hotReloader, application);
    }
}
