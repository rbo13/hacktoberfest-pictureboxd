package app.pictureboxd.api.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import app.pictureboxd.api.domain.Role;

public interface RoleRepository extends JpaRepository<Role, UUID> {

  Optional<Role> findByTitle(String title);
}
