package api.pictureboxd.hacktoberfest.service;

import java.util.Optional;

import api.pictureboxd.hacktoberfest.domain.User;

public interface UserService {

  Optional<User> create(User user);
}
