package app.ludrive.adapters.out.persistence.management.jpa.entity;

import java.util.Map;

import jakarta.persistence.*;

import app.ludrive.adapters.out.persistence.management.jpa.util.MapAttributeConverter;
import app.ludrive.core.domain.management.EntryConfiguration;

@Embeddable
public class JpaEntryConfiguration {

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private EntryConfiguration.Type type;

    @Column(name = "root_path")
    private String rootPath;

    @Convert(converter = MapAttributeConverter.class)
    @Column(name = "credentials")
    private Map<String, String> credentials;

    public JpaEntryConfiguration() {}

    public EntryConfiguration.Type getType() {
        return type;
    }

    public void setType(EntryConfiguration.Type type) {
        this.type = type;
    }

    public String getRootPath() {
        return rootPath;
    }

    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
