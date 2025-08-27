package app.ludrive.core.domain.vfs;

import java.util.UUID;

public abstract class EntryItem {

    public static final String TYPE_DIRECTORY = "DIRECTORY";
    public static final String TYPE_FILE = "FILE";

    public enum Type {
        DIRECTORY(TYPE_DIRECTORY),
        FILE(TYPE_FILE);

        private final String value;

        Type(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private EntryItemId id;

    public EntryItem() {}

    public EntryItem(EntryItemId id) {
        this.id = id;
    }

    public EntryItemId getId() {
        return id;
    }

    public void setId(EntryItemId id) {
        this.id = id;
    }

    public UUID getEntryId() {
        return getId().getEntryId();
    }

    public String getPath() {
        return getId().getPath();
    }
}
