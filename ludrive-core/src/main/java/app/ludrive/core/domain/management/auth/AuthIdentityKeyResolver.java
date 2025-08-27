package app.ludrive.core.domain.management.auth;

import java.security.PrivateKey;
import java.security.PublicKey;

public interface AuthIdentityKeyResolver {

    PublicKey resolvePublicKey(AuthIdentity identity);

    PrivateKey resolvePrivateKey(AuthIdentity identity, String secret);
}
