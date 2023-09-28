package api.pictureboxd.hacktoberfest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.pictureboxd.hacktoberfest.domain.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

  Optional<Role> findRoleByTitle(String title);
}
