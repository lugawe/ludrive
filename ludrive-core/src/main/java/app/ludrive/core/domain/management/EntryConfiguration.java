package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import app.ludrive.core.domain.Identifiable;

public final class EntryConfiguration implements Identifiable, Serializable {

    public enum Type {
        MEMORY,
        LOCAL,
        FTP,
        S3
    }

    private UUID id;
    private String name;

    private Type type;
    private String rootPath;

    private Map<String, String> credentials;

    public EntryConfiguration() {}

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
