package app.ludrive.core.domain.vfs;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import app.ludrive.core.domain.Identifiable;

public abstract sealed class Metadata implements Identifiable permits SystemMetadata, EntryItemMetadata {

    public static final String TYPE_SYSTEM = "SYSTEM";
    public static final String TYPE_ENTRY_ITEM = "ENTRY_ITEM";

    private UUID id;
    private String name;

    private Map<String, String> data;

    private LocalDateTime createdAt;
    private LocalDateTime lastModified;
    private LocalDateTime lastAccessed;

    public Metadata() {}

    public abstract String getType();

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }

    public LocalDateTime getLastAccessed() {
        return lastAccessed;
    }

    public void setLastAccessed(LocalDateTime lastAccessed) {
        this.lastAccessed = lastAccessed;
    }
}
