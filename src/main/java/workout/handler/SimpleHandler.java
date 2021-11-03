package workout.handler;

import io.vertx.reactivex.ext.web.Router;
import io.vertx.reactivex.ext.web.RoutingContext;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SimpleHandler implements Handler {

    private final String todayAsString;

    public SimpleHandler() {
        String pattern = "MM/dd/yyyy HH:mm:ss";
        var df = new SimpleDateFormat(pattern);
        var today = Calendar.getInstance().getTime();
        todayAsString = df.format(today);
    }

    @Override
    public void bindTo(Router router) {
        router.get("/").handler(this::get);
    }

    private void get(RoutingContext routingContext) {
        routingContext.response().end("Hello, World (" + todayAsString + ")");
    }
}
