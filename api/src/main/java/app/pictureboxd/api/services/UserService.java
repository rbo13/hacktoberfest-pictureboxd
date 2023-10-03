package app.pictureboxd.api.services;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import app.pictureboxd.api.domain.User;

public interface UserService extends UserDetailsService {

  User create(User user);

  Optional<User> getByEmailAddress(String emailAddress);
}
