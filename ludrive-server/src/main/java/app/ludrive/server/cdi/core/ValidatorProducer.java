package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.service.validation.Validator;

@ApplicationScoped
public class ValidatorProducer {

    public ValidatorProducer() {}

    @Produces
    public Validator produce() {

        return new Validator();
    }
}
