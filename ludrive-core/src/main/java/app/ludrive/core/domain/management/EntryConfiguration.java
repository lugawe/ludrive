package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.Map;

public final class EntryConfiguration implements Serializable {

    private String type;
    private String storagePluginId;
    private String rootLocation;
    private Map<String, String> config;
    private Map<String, String> credentials;

    public EntryConfiguration() {}

    public EntryConfiguration(String type, String storagePluginId, String rootLocation) {
        this.type = type;
        this.storagePluginId = storagePluginId;
        this.rootLocation = rootLocation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStoragePluginId() {
        return storagePluginId;
    }

    public void setStoragePluginId(String storagePluginId) {
        this.storagePluginId = storagePluginId;
    }

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }

    public Map<String, String> getConfig() {
        return config;
    }

    public void setConfig(Map<String, String> config) {
        this.config = config;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
