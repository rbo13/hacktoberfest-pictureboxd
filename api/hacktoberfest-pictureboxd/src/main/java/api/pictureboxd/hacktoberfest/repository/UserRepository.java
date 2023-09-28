package api.pictureboxd.hacktoberfest.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import api.pictureboxd.hacktoberfest.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

  Optional<User> findByEmailAddress(String emailAddress);

  Boolean existsByEmailAddress(String emailAddress);

  Optional<User> findByEmailAddressOrPassword(String emailAddress, String password);
}
