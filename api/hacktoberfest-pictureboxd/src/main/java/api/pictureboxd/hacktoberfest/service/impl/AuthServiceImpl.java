package api.pictureboxd.hacktoberfest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import api.pictureboxd.hacktoberfest.domain.Role;
import api.pictureboxd.hacktoberfest.domain.User;
import api.pictureboxd.hacktoberfest.dto.RegisterDto;
import api.pictureboxd.hacktoberfest.exception.PictureboxdAPIException;
import api.pictureboxd.hacktoberfest.repository.RoleRepository;
import api.pictureboxd.hacktoberfest.repository.UserRepository;
import api.pictureboxd.hacktoberfest.service.AuthService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private RoleRepository roleRepository;
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
    return userRepository.findByEmailAddress(emailAddress)
        .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
  }

  @Override
  public String register(RegisterDto request) {
    if (userRepository.existsByEmailAddress(request.getEmail())) {
      throw new PictureboxdAPIException(HttpStatus.BAD_REQUEST, "User already exists, please login to continue");
    }

    Role role = roleRepository.findRoleByTitle("USER").orElseThrow();
    String hashedPassword = passwordEncoder.encode(request.getPassword());

    User newUser = User.builder()
        .emailAddress(request.getEmail())
        .password(hashedPassword)
        .role(role)
        .build();

    var createdUser = userRepository.save(newUser);
    if (createdUser.equals(null)) {
      throw new PictureboxdAPIException(HttpStatus.BAD_REQUEST, "Something went wrong creating the user.");
    }

    return "User registered successfully!";
  }

}
