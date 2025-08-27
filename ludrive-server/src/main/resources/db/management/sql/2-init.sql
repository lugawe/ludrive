CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE drive_user (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  name TEXT NOT NULL,
  UNIQUE (name)
);

CREATE TABLE entry (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid (),
  owner_id UUID NOT NULL,
  name TEXT NOT NULL,
  description TEXT,
  UNIQUE (owner_id, name),
  FOREIGN KEY (owner_id) REFERENCES drive_user (id)
);
