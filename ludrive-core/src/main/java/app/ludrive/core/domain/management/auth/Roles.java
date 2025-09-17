package app.ludrive.core.domain.management.auth;

public final class Roles {

    public static final String ROLE_ANONYMOUS = "anonymous";
    public static final String ROLE_DRIVE_USER = "drive_user";
    public static final String ROLE_DRIVE_ADMIN = "drive_admin";

    public enum Role {
        ANONYMOUS(ROLE_ANONYMOUS),
        DRIVE_USER(ROLE_DRIVE_USER),
        DRIVE_ADMIN(ROLE_DRIVE_ADMIN);

        private final String value;

        Role(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private Roles() {}
}
