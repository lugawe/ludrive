package app.ludrive.core.domain.vfs;

import java.io.Serializable;

public final class EntryItemMetadata extends Metadata implements Serializable {

    private boolean hidden;

    public EntryItemMetadata() {}

    @Override
    public String getType() {
        return Metadata.TYPE_ENTRY_ITEM;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
}
