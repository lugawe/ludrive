package app.ludrive.core.ports.cache;

import app.ludrive.core.domain.management.auth.AuthIdentity;

public record ServicePortCacheKey<ID>(AuthIdentity identity, ID id) {}
