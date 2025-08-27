package app.ludrive.server.cdi.persistence.tenant;

import jakarta.enterprise.context.RequestScoped;

import io.quarkus.hibernate.orm.PersistenceUnitExtension;
import io.quarkus.hibernate.orm.runtime.tenant.TenantResolver;

@RequestScoped
@PersistenceUnitExtension("vfs")
public class H2TenantResolver implements TenantResolver {

    public static final String DEFAULT_TENANT = "default";

    public H2TenantResolver() {}

    @Override
    public String getDefaultTenantId() {
        return DEFAULT_TENANT;
    }

    @Override
    public String resolveTenantId() {
        return getDefaultTenantId();
    }
}
