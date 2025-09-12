package app.ludrive.core.domain.management.auth;

import java.util.UUID;

import app.ludrive.core.util.Lazy;

public final class AnonymousAuthIdentity implements AuthIdentity {

    private static final Lazy<AnonymousAuthIdentity> instance = Lazy.of(AnonymousAuthIdentity::new);

    private AnonymousAuthIdentity() {}

    @Override
    public UUID getId() {
        throw new IllegalStateException("AnonymousAuthIdentity has no id");
    }

    @Override
    public String getName() {
        return "AnonymousAuthIdentity";
    }

    public static AuthIdentity getInstance() {
        return instance.get();
    }
}
