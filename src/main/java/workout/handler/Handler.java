package workout.handler;

import io.vertx.reactivex.ext.web.Router;

public interface Handler {
    void bindTo (Router router);
}
