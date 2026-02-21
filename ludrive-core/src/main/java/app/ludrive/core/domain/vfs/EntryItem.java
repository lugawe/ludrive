package app.ludrive.core.domain.vfs;

import java.util.Objects;

import app.ludrive.core.domain.Identifiable;

public abstract sealed class EntryItem implements Identifiable permits Directory, File {

    public static final String TYPE_DIRECTORY = "DIRECTORY";
    public static final String TYPE_FILE = "FILE";

    private final String path;

    public EntryItem(String path) {
        this.path = Objects.requireNonNull(path, "path");
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntryItem entryItem)) return false;
        return Objects.equals(path, entryItem.path);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(path);
    }

    @Override
    public String getId() {
        return path;
    }

    public String getPath() {
        return path;
    }

    public abstract String getType();
}
