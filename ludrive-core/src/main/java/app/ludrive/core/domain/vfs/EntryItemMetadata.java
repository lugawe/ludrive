package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class EntryItemMetadata extends Metadata implements Serializable {

    private boolean hidden;

    public EntryItemMetadata() {}

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
