package workout.vertx;

import io.vertx.core.Verticle;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VerticleDeploymentInfo {
    private final String name;
    private final Class <? extends Verticle> verticle;
}
