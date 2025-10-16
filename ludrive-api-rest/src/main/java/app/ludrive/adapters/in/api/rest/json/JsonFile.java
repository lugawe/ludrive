package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

public class JsonFile extends JsonEntryItem {

    public JsonFile() {
        this(null);
    }

    public JsonFile(String path) {
        super(path, EntryItem.TYPE_FILE);
    }
}
