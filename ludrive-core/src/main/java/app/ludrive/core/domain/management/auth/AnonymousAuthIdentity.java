package app.ludrive.core.domain.management.auth;

import java.util.Collections;
import java.util.Set;
import java.util.UUID;

import app.ludrive.core.util.Lazy;

public final class AnonymousAuthIdentity implements AuthIdentity {

    private static final Lazy<AnonymousAuthIdentity> instance = Lazy.of(AnonymousAuthIdentity::new);

    private AnonymousAuthIdentity() {}

    @Override
    public UUID getId() {
        throw new RuntimeException("anonymous auth identity has no id");
    }

    @Override
    public String getName() {
        return "anonymous auth identity";
    }

    @Override
    public Set<Roles.Role> getRoles() {
        return Collections.singleton(Roles.Role.ANONYMOUS);
    }

    public static AuthIdentity getInstance() {
        return instance.get();
    }
}
