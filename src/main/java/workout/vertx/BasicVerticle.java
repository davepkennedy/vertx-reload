package workout.vertx;

import com.google.inject.Inject;
import io.reactivex.Completable;
import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.core.http.HttpServer;
import io.vertx.reactivex.ext.web.Router;

public class BasicVerticle extends AbstractVerticle {
    private final Router router;
    private HttpServer server;

    @Inject
    public BasicVerticle (Router router) {
        this.router = router;
    }

    @Override
    public Completable rxStart() {
        this.server = vertx.createHttpServer();

        return server.requestHandler(router)
                .rxListen(8888)
                .ignoreElement();
    }

    @Override
    public Completable rxStop() {
        return server.rxClose();
    }
}
