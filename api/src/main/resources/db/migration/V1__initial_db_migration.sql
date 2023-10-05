CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS "_roles" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "title" VARCHAR(15) NOT NULL,
  "description" TEXT,
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS "_users" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "email_address" VARCHAR(255) UNIQUE NOT NULL,
  "password" TEXT NOT NULL,
  "is_verified" BOOLEAN NOT NULL DEFAULT false,
  "role_id" UUID NOT NULL REFERENCES "_roles" ("id"),
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS "_actors" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(50) UNIQUE NOT NULL,
  "age" INTEGER NULL,
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS "_movies" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "title" TEXT NOT NULL,
  "plot" TEXT NOT NULL,
  "rating" FLOAT NOT NULL,
  "release_date" TEXT NOT NULL,
  "poster_path" TEXT NULL,
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS "_genres" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "name" VARCHAR(150) NOT NULL,
  "created_at" TIMESTAMP NOT NULL DEFAULT current_timestamp
);

CREATE TABLE IF NOT EXISTS "_movie_actors" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "movie_id" UUID NOT NULL,
  "actor_id" UUID NOT NULL,
  FOREIGN KEY ("movie_id") REFERENCES "_movies" ("id"),
  FOREIGN KEY ("actor_id") REFERENCES "_actors" ("id")
);

CREATE TABLE IF NOT EXISTS "_movie_genres" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "movie_id" UUID NOT NULL,
  "genre_id" UUID NOT NULL,
  FOREIGN KEY ("movie_id") REFERENCES "_movies" ("id"),
  FOREIGN KEY ("genre_id") REFERENCES "_genres" ("id")
);

CREATE TABLE IF NOT EXISTS "_actor_movies" (
  "id" UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  "actor_id" UUID NOT NULL,
  "movie_id" UUID NOT NULL,
  FOREIGN KEY ("actor_id") REFERENCES "_actors" ("id"),
  FOREIGN KEY ("movie_id") REFERENCES "_movies" ("id")
);

CREATE INDEX "idx_email" ON "_users" (email_address);
CREATE INDEX "idx_password" ON "_users" (password);
CREATE INDEX "idx_actors_name" ON "_actors" (name);
CREATE INDEX "idx_movies_title" ON "_movies" (title);