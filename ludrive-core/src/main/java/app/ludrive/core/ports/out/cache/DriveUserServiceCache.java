package app.ludrive.core.ports.out.cache;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.service.cache.Cache;

public interface DriveUserServiceCache extends Cache<DriveUser, UUID> {}
