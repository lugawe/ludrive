package app.ludrive.adapters.in.api.rest.json;

import java.util.Map;

import app.ludrive.core.domain.management.EntryConfiguration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JsonEntryConfiguration {

    private EntryConfiguration.Type type;
    private String rootLocation;
    private Map<String, String> credentials;

    public JsonEntryConfiguration() {}

    public JsonEntryConfiguration(EntryConfiguration.Type type, String rootLocation) {
        this.type = type;
        this.rootLocation = rootLocation;
    }

    public EntryConfiguration.Type getType() {
        return type;
    }

    public void setType(EntryConfiguration.Type type) {
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
