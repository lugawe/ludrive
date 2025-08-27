package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

import app.ludrive.core.domain.management.auth.AuthIdentity;
import app.ludrive.core.domain.management.auth.Roles;

public class DriveUser implements AuthIdentity, Serializable {

    private UUID id;
    private String name;
    private Set<Roles.Role> roles;

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public DriveUser() {}

    public DriveUser(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public DriveUser(UUID id, String name, Set<Roles.Role> roles, PrivateKey privateKey, PublicKey publicKey) {
        this.id = id;
        this.name = name;
        this.roles = roles;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DriveUser that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return getName();
    }

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

    @Override
    public Set<Roles.Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Roles.Role> roles) {
        this.roles = roles;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}
