package workout.vertx;

import com.google.inject.*;
import io.vertx.core.Promise;
import io.vertx.core.Verticle;
import io.vertx.core.spi.VerticleFactory;
import io.vertx.reactivex.core.Vertx;

import java.util.Set;
import java.util.concurrent.Callable;

public class VertxModule extends AbstractModule {
    @Singleton
    @Provides
    public Vertx providesVertx (GuiceVerticleFactory verticleFactory) {
        var vertx = Vertx.vertx();
        vertx.registerVerticleFactory(verticleFactory);
        return vertx;
    }

    private static class GuiceVerticleFactory implements VerticleFactory {

        private final Injector injector;
        private final Set<VerticleDeploymentInfo> verticleDeployments;

        @Inject
        GuiceVerticleFactory (
                Injector injector,
                Set<VerticleDeploymentInfo> verticleDeployments) {
            this.injector = injector;
            this.verticleDeployments = verticleDeployments;
        }

        @Override
        public String prefix() {
            return "guice";
        }

        @Override
        public void createVerticle(String verticleName, ClassLoader classLoader, Promise<Callable<Verticle>> promise) {
            for (var info : verticleDeployments) {
                if (info.getName().equals(verticleName)) {
                    promise.complete(() -> injector.getInstance(info.getVerticle()));
                    return;
                }
            }
            promise.fail("No such verticle");
        }
    }
}
