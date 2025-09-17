package app.ludrive.core.service.cache;

import java.util.UUID;

import app.ludrive.core.domain.management.auth.DriveUser;

public interface DriveUserCache extends Cache<DriveUser, UUID> {}
