package app.ludrive.adapters.out.persistence.vfs.jpa.entity;

import jakarta.persistence.*;

import app.ludrive.core.domain.vfs.EntryItem;

@Entity
@DiscriminatorValue(EntryItem.TYPE_FILE)
public class JpaFile extends JpaEntryItem {

    public JpaFile() {}
}
