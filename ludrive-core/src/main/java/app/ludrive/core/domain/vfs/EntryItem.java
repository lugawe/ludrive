package app.ludrive.core.domain.vfs;

import java.util.Objects;

import app.ludrive.core.domain.Identifiable;

public abstract sealed class EntryItem implements Identifiable permits Directory, File {

    public static final String TYPE_DIRECTORY = "DIRECTORY";
    public static final String TYPE_FILE = "FILE";

    private EntryItemId id;

    public EntryItem(EntryItemId id) {
        this.id = Objects.requireNonNull(id);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntryItem entryItem)) return false;
        return Objects.equals(id, entryItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public abstract String getType();

    @Override
    public EntryItemId getId() {
        return id;
    }

    public void setId(EntryItemId id) {
        this.id = id;
    }

    public String getPath() {
        return id.path();
    }
}
