package app.ludrive.server.cdi.persistence.tenant;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import app.ludrive.server.tooling.migration.LiquibaseMigration;

import io.agroal.pool.DataSource;
import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantConnectionResolver;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;

@ApplicationScoped
@PersistenceUnitExtension("vfs")
public class H2TenantConnectionResolver implements TenantConnectionResolver {

    private final H2InMemoryConnectionProvider h2InMemoryConnectionProvider;

    @Inject
    public H2TenantConnectionResolver(H2InMemoryConnectionProvider h2InMemoryConnectionProvider) {
        this.h2InMemoryConnectionProvider = h2InMemoryConnectionProvider;
    }

    protected ConnectionProvider getConnectionProvider(String tenantId) {

        if (H2TenantResolver.DEFAULT_TENANT.equals(tenantId)) {
            return h2InMemoryConnectionProvider;
        }

        return null;
    }

    @Override
    public ConnectionProvider resolve(String tenantId) {

        ConnectionProvider connectionProvider = getConnectionProvider(tenantId);

        try {
            DataSource dataSource = connectionProvider.unwrap(DataSource.class);

            LiquibaseMigration.migrate(dataSource, LiquibaseMigration.Type.VFS);

        } catch (Exception e) {
            throw new IllegalStateException("cannot resolve connection provider", e);
        }

        return connectionProvider;
    }
}
