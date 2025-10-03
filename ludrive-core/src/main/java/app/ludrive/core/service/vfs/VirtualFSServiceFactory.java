package app.ludrive.core.service.vfs;

import app.ludrive.core.exception.VFSException;
import app.ludrive.core.service.context.AuthIdentityEntryKey;

public interface VirtualFSServiceFactory {

    VirtualFSService create(AuthIdentityEntryKey key) throws VFSException;
}
