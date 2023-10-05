package app.pictureboxd.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.pictureboxd.api.domain.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, UUID> {

  Optional<Actor> findByName(String name);
}
