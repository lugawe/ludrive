package app.ludrive.server.tooling.migration;

import java.sql.Connection;
import javax.sql.DataSource;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class LiquibaseMigration {

    private static final Logger log = LoggerFactory.getLogger(LiquibaseMigration.class);

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

    private LiquibaseMigration() {}

    public static void migrate(DataSource dataSource, Type type) {

        if (dataSource == null) {
            throw new NullPointerException("dataSource");
        }
        if (type == null) {
            throw new NullPointerException("type");
        }

        try (Connection connection = dataSource.getConnection()) {

            log.info("got connection - start migration");

            Database database =
                    DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));

            Liquibase liquibase = new Liquibase(type.getChangeLogPath(), new ClassLoaderResourceAccessor(), database);

            liquibase.update();

        } catch (Exception e) {
            throw new IllegalStateException("liquibase migration failed", e);
        }
    }
}
