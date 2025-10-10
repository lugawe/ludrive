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

    private String rootPath;

    private Map<String, String> credentials;

    public EntryConfiguration() {}

    public EntryConfiguration(Type type, String rootPath) {
        this.type = type;
        this.rootPath = rootPath;
    }

    public EntryConfiguration(Type type, String rootPath, Map<String, String> credentials) {
        this.type = type;
        this.rootPath = rootPath;
        this.credentials = credentials;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
