package app.ludrive.server.cdi.persistence.tenant;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.TransactionManager;
import jakarta.transaction.TransactionSynchronizationRegistry;

import io.agroal.api.AgroalDataSource;
import io.agroal.api.configuration.supplier.AgroalDataSourceConfigurationSupplier;
import io.agroal.api.security.NamePrincipal;
import io.agroal.api.security.SimplePassword;
import io.agroal.api.transaction.TransactionIntegration;
import io.agroal.narayana.NarayanaTransactionIntegration;
import io.quarkus.hibernate.orm.runtime.customized.QuarkusConnectionProvider;

@Singleton
public class H2InMemoryConnectionProvider extends QuarkusConnectionProvider {

    private static final String DB_NAME = H2TenantResolver.DEFAULT_TENANT;

    @Inject
    public H2InMemoryConnectionProvider(
            TransactionManager transactionManager,
            TransactionSynchronizationRegistry transactionSynchronizationRegistry) {
        super(createDatasource(transactionManager, transactionSynchronizationRegistry));
    }

    private static AgroalDataSource createDatasource(
            TransactionManager transactionManager,
            TransactionSynchronizationRegistry transactionSynchronizationRegistry) {
        try {
            TransactionIntegration transactionIntegration = new NarayanaTransactionIntegration(
                    transactionManager, transactionSynchronizationRegistry, null, false, null);

            AgroalDataSourceConfigurationSupplier dataSourceConfig = new AgroalDataSourceConfigurationSupplier()
                    .connectionPoolConfiguration(pc -> pc.initialSize(2)
                            .maxSize(10)
                            .minSize(2)
                            .maxLifetime(Duration.of(5, ChronoUnit.MINUTES))
                            .acquisitionTimeout(Duration.of(30, ChronoUnit.SECONDS))
                            .transactionIntegration(transactionIntegration)
                            .connectionFactoryConfiguration(cf -> cf.jdbcUrl("jdbc:h2:mem:h2-" + DB_NAME)
                                    .jdbcProperty("DB_CLOSE_DELAY", "-1")
                                    .jdbcProperty("MODE", "PostgreSQL")
                                    .credential(new NamePrincipal(DB_NAME))
                                    .credential(new SimplePassword(DB_NAME))));

            return AgroalDataSource.from(dataSourceConfig.get());
        } catch (Exception e) {
            throw new IllegalStateException("cannot create default h2 datasource", e);
        }
    }
}
