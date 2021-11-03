package workout;

import com.google.inject.Inject;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.reactivex.core.Vertx;
import workout.vertx.VerticleDeploymentInfo;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final Vertx vertx;
    private final Set<VerticleDeploymentInfo> deploymentInfo;

    private final ExecutorService executor = Executors.newFixedThreadPool(1);

    @Inject
    public Application(Vertx vertx, Set<VerticleDeploymentInfo> deploymentInfo) {
        this.vertx = vertx;
        this.deploymentInfo = deploymentInfo;
    }

    public void run() {
        Flowable.fromIterable(deploymentInfo)
                .map(VerticleDeploymentInfo::getName)
                .flatMapSingle(this::deployVerticle)
                .subscribe(
                        s -> LOGGER.info("Deploy Complete"),
                        t -> LOGGER.error("Deploy Failed", t)
                );
    }

    public void reset() {
        Flowable.fromIterable(vertx.deploymentIDs())
                .flatMapSingle(s -> vertx.rxUndeploy(s).toSingle(() -> s))
                .subscribe(
                        s -> {
                            LOGGER.info("Undeploy Complete");
                            schedule(this::run);
                        },
                        t -> LOGGER.error("Undeploy Failed", t)
                );
    }

    private void schedule(Runnable runnable) {
        executor.submit(runnable);
    }

    private Single<String> deployVerticle(String verticleName) {
        return vertx.rxDeployVerticle(verticleName);
    }
}
