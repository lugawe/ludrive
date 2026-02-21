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

    @NotNull(message = "This path cannot be null")
    @JsonProperty("path")
    private String path;

    @NotEmpty(message = "This type cannot be empty")
    @JsonProperty("type")
    private String type;

    public JsonEntryItem(String path, String type) {
        this.path = path;
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
