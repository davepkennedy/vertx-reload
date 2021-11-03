package workout.handler;

import com.google.inject.Inject;
import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;
import workout.Application;
import workout.scope.HotReloader;

public class ResetHandler implements Handler {
    private final HotReloader hotReloader;
    private final Application application;

    @Inject
    public ResetHandler (
            HotReloader hotReloader,
            Application application
    ) {
        this.hotReloader = hotReloader;
        this.application = application;
    }

    @Override
    public void bindTo(Router router) {
        router.post("/reset")
                .handler(this::reset);
    }

    private void reset(RoutingContext routingContext) {
        hotReloader.reload();
        application.reset();
        routingContext.response()
                .setStatusCode(204)
                .end();
    }
}
