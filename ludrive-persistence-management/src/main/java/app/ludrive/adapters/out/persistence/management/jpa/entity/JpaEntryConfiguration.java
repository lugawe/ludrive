package app.ludrive.adapters.out.persistence.management.jpa.entity;

import java.util.Map;

import jakarta.persistence.*;

import app.ludrive.adapters.out.persistence.management.jpa.util.MapAttributeConverter;

@Embeddable
public class JpaEntryConfiguration {

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "root_location", nullable = false)
    private String rootLocation;

    @Convert(converter = MapAttributeConverter.class)
    @Column(name = "credentials")
    private Map<String, String> credentials;

    public JpaEntryConfiguration() {}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRootLocation() {
        return rootLocation;
    }

    public void setRootLocation(String rootLocation) {
        this.rootLocation = rootLocation;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public void setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
    }
}
