package app.ludrive.adapters.in.api.rest.json;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JsonFile extends JsonEntryItem {

    public JsonFile() {}

    public JsonFile(UUID entryId, String path) {
        super(new JsonEntryItemId(entryId, path));
    }
}
