package api.pictureboxd.hacktoberfest.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.pictureboxd.hacktoberfest.domain.User;
import api.pictureboxd.hacktoberfest.repository.UserRepository;
import api.pictureboxd.hacktoberfest.service.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {

  @Autowired
  private final UserRepository userRepository;

  @Override
  public Optional<User> create(User user) {
    User createdUser = userRepository.save(user);

    if (createdUser.equals(null)) {
      return null;
    }

    return Optional.of(createdUser);
  }
}
