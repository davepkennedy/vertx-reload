package workout.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.Router;
import workout.handler.Handler;
import workout.scope.HotReload;

import java.util.Set;

public class RouteModule extends AbstractModule {
    @Provides
    @HotReload
    public Router provideRouter (Vertx vertx, Set<Handler> handlers) {
        var router = Router.router(vertx);
        handlers.forEach(handler -> handler.bindTo(router));
        return router;
    }
}
