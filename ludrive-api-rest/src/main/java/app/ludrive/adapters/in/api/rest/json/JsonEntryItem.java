package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonDirectory.class, name = EntryItem.TYPE_DIRECTORY),
    @JsonSubTypes.Type(value = JsonFile.class, name = EntryItem.TYPE_FILE)
})
public abstract class JsonEntryItem {

    @JsonProperty("id")
    private JsonEntryItemId id;

    @JsonProperty("type")
    private String type;

    public JsonEntryItem(String path, String type) {
        if (path != null) {
            this.id = new JsonEntryItemId(path);
        }
        this.type = type;
    }

    public JsonEntryItemId getId() {
        return id;
    }

    public void setId(JsonEntryItemId id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
