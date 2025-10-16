package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class JsonDirectory extends JsonEntryItem {

    public JsonDirectory() {}

    public JsonDirectory(String path) {
        super(new JsonEntryItemId(path));
    }

    @Override
    public String getType() {
        return EntryItem.TYPE_DIRECTORY;
    }
}
