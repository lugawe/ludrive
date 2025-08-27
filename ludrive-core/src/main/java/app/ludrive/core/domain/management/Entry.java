package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class Entry implements Serializable {

    public enum Protocol {
        MEMORY(""),
        FILE(""),
        S3("");

        private final String urlScheme;

        Protocol(String urlScheme) {
            this.urlScheme = urlScheme;
        }

        public String getUrlScheme() {
            return urlScheme;
        }
    }

    private UUID id;
    private String name;
    private String description;

    private Protocol protocol;

    public Entry() {}

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Entry entry)) return false;
        return Objects.equals(id, entry.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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

    public Protocol getProtocol() {
        return protocol;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }
}
