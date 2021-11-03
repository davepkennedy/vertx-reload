package workout;

import com.google.inject.Guice;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import workout.handler.HandlerModule;
import workout.scope.HotReloadModule;
import workout.vertx.RouteModule;
import workout.vertx.VerticleModule;
import workout.vertx.VertxModule;

public class Entrypoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(Entrypoint.class);

    public static void main (String... args) {
        var injector = Guice.createInjector(
                new VertxModule(),
                new HotReloadModule(),
                new VerticleModule(),
                new RouteModule(),
                new HandlerModule(),
                new ApplicationModule()
        );
        injector.getInstance(Application.class).run();
    }

}
