package app.ludrive.core.domain.vfs;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class EntryItemId implements Serializable {

    private UUID entryId;
    private String path;

    public EntryItemId() {}

    public EntryItemId(UUID entryId, String path) {
        this.entryId = entryId;
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof EntryItemId that)) return false;
        return Objects.equals(getEntryId(), that.getEntryId()) && Objects.equals(getPath(), that.getPath());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEntryId(), getPath());
    }

    @Override
    public String toString() {
        return "EntryItemId[entryId=" + getEntryId() + ", path=" + getPath() + "]";
    }

    public UUID getEntryId() {
        return entryId;
    }

    public void setEntryId(UUID entryId) {
        this.entryId = entryId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
