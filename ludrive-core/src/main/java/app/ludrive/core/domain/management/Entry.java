package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import app.ludrive.core.domain.Identifiable;

public final class Entry implements Identifiable, Serializable {

    private UUID id;
    private String name;

    private String description;

    private EntryConfiguration configuration;

    public Entry() {}

    public Entry(UUID id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) return false;
        return Objects.equals(id, entry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntryConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(EntryConfiguration configuration) {
        this.configuration = configuration;
    }
}
