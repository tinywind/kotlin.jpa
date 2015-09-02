CREATE TYPE "role_type" AS ENUM ('Admin', 'User');

CREATE TABLE "member"
(
  id          BIGINT PRIMARY KEY      NOT NULL,
  username    VARCHAR(256),
  name        VARCHAR(256),
  age         INTEGER,
  role        role_type,
  created_at  TIMESTAMP,
  description TEXT
);
