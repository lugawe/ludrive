package app.ludrive.adapters.in.api.rest.json;

import app.ludrive.core.domain.vfs.EntryItem;

public class JsonDirectory extends JsonEntryItem {

    public JsonDirectory() {
        this(null);
    }

    public JsonDirectory(String path) {
        super(path, EntryItem.TYPE_DIRECTORY);
    }
}
