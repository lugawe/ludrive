package app.ludrive.adapters.in.api.rest.json;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JsonEntry {

    private UUID id;
    private String name;
    private String description;
    private JsonEntryConfiguration configuration;

    public JsonEntry() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JsonEntryConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(JsonEntryConfiguration configuration) {
        this.configuration = configuration;
    }
}
