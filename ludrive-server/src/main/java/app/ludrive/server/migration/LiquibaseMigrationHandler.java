package app.ludrive.server.migration;

import javax.sql.DataSource;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import app.ludrive.core.exception.MigrationException;
import app.ludrive.core.logging.Logger;
import app.ludrive.core.ports.out.migration.MigrationHandler;
import app.ludrive.server.cdi.util.ClassNamed;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

@RequestScoped
public class LiquibaseMigrationHandler implements MigrationHandler {

    public enum Type {
        MANAGEMENT("db/management/managementChangeLog.xml"),
        VFS("db/vfs/vfsChangeLog.xml");

        private final String changeLogPath;

        Type(String changeLogPath) {
            this.changeLogPath = changeLogPath;
        }

        public String getChangeLogPath() {
            return changeLogPath;
        }
    }

    @Inject
    @ClassNamed(LiquibaseMigrationHandler.class)
    private Logger logger;

    public LiquibaseMigrationHandler() {}

    public void migrate(DataSource dataSource, Type type) {

        if (dataSource == null) {
            throw new NullPointerException("dataSource");
        }
        if (type == null) {
            throw new NullPointerException("type");
        }

        try (JdbcConnection connection = new JdbcConnection(dataSource.getConnection())) {

            logger.info("start liquibase migration");

            Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(connection);

            Liquibase liquibase = new Liquibase(type.getChangeLogPath(), new ClassLoaderResourceAccessor(), database);

            liquibase.update();

            logger.info("liquibase migration done");

        } catch (Exception e) {
            throw new MigrationException("liquibase migration failed", e);
        }
    }

    @Override
    public boolean needsMigration() {
        return false;
    }

    @Override
    public void migrate() {}
}
