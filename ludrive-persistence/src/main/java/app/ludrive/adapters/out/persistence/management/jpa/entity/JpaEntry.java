package app.ludrive.adapters.out.persistence.management.jpa.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Table(name = "entry")
public class JpaEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "owner_id", nullable = false)
    private JpaDriveUser owner;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Embedded
    private JpaEntryConfiguration configuration;

    public JpaEntry() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public JpaDriveUser getOwner() {
        return owner;
    }

    public void setOwner(JpaDriveUser owner) {
        this.owner = owner;
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

    public JpaEntryConfiguration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(JpaEntryConfiguration configuration) {
        this.configuration = configuration;
    }
}
