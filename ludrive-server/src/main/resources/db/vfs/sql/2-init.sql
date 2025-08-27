
CREATE TABLE entry_item (
  entry_id UUID,
  path TEXT CHECK (is_valid_path (path)),
  parent_path TEXT GENERATED ALWAYS AS (get_parent_path (path)),
  type TEXT NOT NULL CHECK (type IN ('DIRECTORY', 'FILE')),
  PRIMARY KEY (entry_id, path),
  FOREIGN KEY (entry_id, parent_path) REFERENCES entry_item (entry_id, path) ON DELETE CASCADE
);

CREATE INDEX idx_entry_item_entry ON entry_item (entry_id);

CREATE INDEX idx_entry_item_path ON entry_item (path);

CREATE INDEX idx_entry_item_parent_path ON entry_item (parent_path);

CREATE INDEX idx_entry_item_entry_parent_path ON entry_item (entry_id, parent_path);
