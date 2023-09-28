package api.pictureboxd.hacktoberfest.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import api.pictureboxd.hacktoberfest.repository.UserRepository;
import api.pictureboxd.hacktoberfest.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class CustomUserDetailService implements AuthService {

  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
    return userRepository.findByEmailAddress(emailAddress)
        .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));
  }
}
