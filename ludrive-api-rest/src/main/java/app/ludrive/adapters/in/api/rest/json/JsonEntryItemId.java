package app.ludrive.adapters.in.api.rest.json;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonEntryItemId {

    @JsonProperty("path")
    private String path;

    public JsonEntryItemId() {}

    public JsonEntryItemId(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
