package app.ludrive.core.domain.management;

import java.io.Serializable;
import java.util.UUID;

public class KeyPair implements Serializable {

    private UUID id;
    private String name;

    private byte[] privateKey;
    private byte[] publicKey;

    public KeyPair() {}

    public KeyPair(UUID id, String name, byte[] privateKey, byte[] publicKey) {
        this.id = id;
        this.name = name;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
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

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
