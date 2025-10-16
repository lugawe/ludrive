package app.ludrive.adapters.in.api.rest.json;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

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

    @NotNull(message = "This id cannot be null")
    @JsonProperty("id")
    private JsonEntryItemId id;

    @NotEmpty(message = "This type cannot be empty")
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
