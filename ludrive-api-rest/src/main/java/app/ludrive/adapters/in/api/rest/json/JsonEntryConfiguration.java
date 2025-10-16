package app.ludrive.adapters.in.api.rest.json;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonEntryConfiguration {

    @JsonProperty("type")
    private String type;

    @JsonProperty("storage_plugin_id")
    private String storagePluginId;

    @JsonProperty("root_location")
    private String rootLocation;

    @JsonProperty("credentials")
    private Map<String, String> credentials;

    public JsonEntryConfiguration() {}

    public JsonEntryConfiguration(String type, String storagePluginId, String rootLocation) {
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

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
