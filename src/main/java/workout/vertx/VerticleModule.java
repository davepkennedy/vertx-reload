package workout.vertx;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;

public class VerticleModule extends AbstractModule {
    @ProvidesIntoSet
    public VerticleDeploymentInfo provideBasicVerticle() {
        return new VerticleDeploymentInfo("guice:basic", BasicVerticle.class);
    }
}
