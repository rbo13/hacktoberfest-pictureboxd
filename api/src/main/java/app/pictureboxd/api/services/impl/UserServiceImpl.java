package app.pictureboxd.api.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.User;
import app.pictureboxd.api.repository.UserRepository;
import app.pictureboxd.api.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
    return userRepository.findByEmailAddress(emailAddress).orElseThrow();
  }

  @Override
  public User create(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> getByEmailAddress(String emailAddress) {
    return userRepository.findByEmailAddress(emailAddress);
  }
}
