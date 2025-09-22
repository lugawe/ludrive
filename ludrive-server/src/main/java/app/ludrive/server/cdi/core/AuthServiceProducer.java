package app.ludrive.server.cdi.core;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

import app.ludrive.core.service.auth.AuthService;
import app.ludrive.core.service.auth.DefaultAuthService;

@ApplicationScoped
public class AuthServiceProducer {

    public AuthServiceProducer() {}

    @Produces
    public AuthService produce() {

        return new DefaultAuthService();
    }
}
