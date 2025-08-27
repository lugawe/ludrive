package app.ludrive.adapters.in.api.rest.json;

import java.util.UUID;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JsonEntryItemId {

    private UUID entryId;
    private String path;

    public JsonEntryItemId() {}

    public JsonEntryItemId(UUID entryId, String path) {
        this.entryId = entryId;
        this.path = path;
    }

    public UUID getEntryId() {
        return entryId;
    }

    public void setEntryId(UUID entryId) {
        this.entryId = entryId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
