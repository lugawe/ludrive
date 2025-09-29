package app.ludrive.core.service.cache;

import app.ludrive.core.domain.management.auth.AuthIdentity;

public record CacheKey<ID>(AuthIdentity identity, ID id) {}
