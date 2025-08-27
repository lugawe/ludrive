package app.ludrive.adapters.out.persistence.vfs.jpa.entity;

import jakarta.persistence.*;

import app.ludrive.core.domain.vfs.EntryItem;

@Entity
@DiscriminatorValue(EntryItem.TYPE_DIRECTORY)
public class JpaDirectory extends JpaEntryItem {

    public JpaDirectory() {}
}
