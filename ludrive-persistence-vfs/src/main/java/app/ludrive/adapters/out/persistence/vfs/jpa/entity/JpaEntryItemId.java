package app.ludrive.adapters.out.persistence.vfs.jpa.entity;

import java.util.Objects;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import app.ludrive.core.domain.vfs.EntryItemId;

@Embeddable
public class JpaEntryItemId {

    @Column(name = "entry_id")
    private UUID entryId;

    @Column(name = "path")
    private String path;

    public JpaEntryItemId() {}

    public JpaEntryItemId(UUID entryId, String path) {
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
