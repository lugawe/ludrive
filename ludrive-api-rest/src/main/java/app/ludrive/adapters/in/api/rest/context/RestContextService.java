package app.ludrive.adapters.in.api.rest.context;

import java.util.UUID;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.adapters.in.api.rest.auth.Jwts;
import app.ludrive.core.domain.management.Entry;
import app.ludrive.core.domain.management.auth.DriveUser;
import app.ludrive.core.ports.out.DriveUserServicePortOut;
import app.ludrive.core.ports.out.EntryServicePortOut;
import app.ludrive.core.service.context.ContextService;

import io.vertx.ext.web.RoutingContext;
import org.eclipse.microprofile.jwt.JsonWebToken;

// TODO make it better + cached
@RequestScoped
public class RestContextService implements ContextService {

    @Inject
    private RoutingContext routingContext;

    @Inject
    private JsonWebToken jsonWebToken;

    @Inject
    private DriveUserServicePortOut driveUserServicePortOut;

    @Inject
    private EntryServicePortOut entryServicePortOut;

    public RestContextService() {}

    @Override
    public DriveUser getAuthIdentity() {
        UUID driveUserId = UUID.fromString(jsonWebToken.getClaim(Jwts.IDENTITY_ID_CLAIM));
        return driveUserServicePortOut.getDriveUser(driveUserId);
    }

    @Override
    public Entry getEntry() {

        String path = routingContext.request().path();
        String[] parts = path.split("/");

        UUID entryId = UUID.fromString(parts[3]);

        return entryServicePortOut.getEntry(getAuthIdentity(), entryId);
    }
}
