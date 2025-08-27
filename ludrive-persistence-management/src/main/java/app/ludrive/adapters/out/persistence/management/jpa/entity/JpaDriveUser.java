package app.ludrive.adapters.out.persistence.management.jpa.entity;

import java.util.UUID;

import jakarta.persistence.*;

@Entity
@Cacheable
@Table(name = "drive_user")
public class JpaDriveUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public JpaDriveUser() {}

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
}
