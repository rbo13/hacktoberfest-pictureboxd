CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE ROLES AS ENUM ('SUPER_USER', 'ADMIN', 'USER');

CREATE TABLE IF NOT EXISTS "users" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "email_address" VARCHAR(255) UNIQUE NOT NULL,
  "password" TEXT NOT NULL,
  "role" ROLES NOT NULL DEFAULT 'USER',
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE INDEX "idx_email" ON "users" (email_address);
CREATE INDEX "idx_password" ON "users" (password);