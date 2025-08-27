package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = JsonDirectory.class, name = EntryItem.TYPE_DIRECTORY),
    @JsonSubTypes.Type(value = JsonFile.class, name = EntryItem.TYPE_FILE)
})
public abstract class JsonEntryItem {

    private JsonEntryItemId id;

    public JsonEntryItem() {}

    public JsonEntryItem(JsonEntryItemId id) {
        this.id = id;
    }

    public JsonEntryItemId getId() {
        return id;
    }

    public void setId(JsonEntryItemId id) {
        this.id = id;
    }
}
