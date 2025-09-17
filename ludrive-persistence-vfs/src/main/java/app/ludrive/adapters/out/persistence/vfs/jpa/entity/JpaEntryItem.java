package app.ludrive.adapters.out.persistence.vfs.jpa.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
@Table(
        name = "entry_item",
        indexes = {
            @Index(name = "idx_entry_item_entry", columnList = "entry_id"),
            @Index(name = "idx_entry_item_path", columnList = "path"),
            @Index(name = "idx_entry_item_parent_path", columnList = "parent_path"),
            @Index(name = "idx_entry_item_entry_parent_path", columnList = "entry_id, parent_path")
        })
public class JpaEntryItem {

    @EmbeddedId
    private JpaEntryItemId id;

    // todo check cascade
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "entry_id", referencedColumnName = "entry_id", insertable = false, updatable = false),
        @JoinColumn(name = "parent_path", referencedColumnName = "path", insertable = false, updatable = false)
    })
    private JpaEntryItem parent;

    @Column(name = "parent_path", insertable = false, updatable = false)
    private String parentPath;

    public JpaEntryItem() {}

    public JpaEntryItemId getId() {
        return id;
    }

    public void setId(JpaEntryItemId id) {
        this.id = id;
    }

    public JpaEntryItem getParent() {
        return parent;
    }

    public void setParent(JpaEntryItem parent) {
        this.parent = parent;
    }

    public String getParentPath() {
        return parentPath;
    }

    public void setParentPath(String parentPath) {
        this.parentPath = parentPath;
    }
}
