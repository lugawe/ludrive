package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class SystemMetadata extends Metadata implements Serializable {

    public enum Status {
        READY,
        NOT_READY,
        ACTION_REQUIRED
    }

    private Status status;

    public SystemMetadata() {}

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
