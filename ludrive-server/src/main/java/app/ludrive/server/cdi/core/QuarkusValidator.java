package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.service.validation.Validator;

@ApplicationScoped
public class QuarkusValidator {

    public QuarkusValidator() {}

    @Produces
    public Validator produce() {

        return new Validator();
    }
}
