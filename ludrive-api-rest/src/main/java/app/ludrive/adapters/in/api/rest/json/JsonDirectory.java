package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonDirectory extends JsonEntryItem {

    public JsonDirectory() {}

    public JsonDirectory(String path) {
        super(new JsonEntryItemId(path));
    }

    @Override
    @JsonProperty("type")
    public String getType() {
        return EntryItem.TYPE_DIRECTORY;
    }
}
