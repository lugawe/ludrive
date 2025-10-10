package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.Map;

public final class EntryConfiguration implements Serializable {

    public enum Type {
        MEMORY,
        LOCAL,
        FTP,
        S3
    }

    private Type type;

    private String rootLocation;

    private Map<String, String> credentials;

    public EntryConfiguration() {}

    public EntryConfiguration(Type type, String rootLocation) {
        this.type = type;
        this.rootLocation = rootLocation;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
