package api.pictureboxd.hacktoberfest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.pictureboxd.hacktoberfest.domain.Role;
import api.pictureboxd.hacktoberfest.domain.User;
import api.pictureboxd.hacktoberfest.dto.SignupRequest;
import api.pictureboxd.hacktoberfest.service.RoleService;
import api.pictureboxd.hacktoberfest.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationController {

  private final UserService userService;
  private final RoleService roleService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Optional<User>> signup(@RequestBody SignupRequest request) {
    String hashedPassword = passwordEncoder.encode(request.getPassword());
    Role role = roleService.getByTitle("USER").orElseThrow();

    User newUser = User
        .builder()
        .emailAddress(request.getEmail())
        .password(hashedPassword)
        .role(role)
        .build();

    Optional<User> user = userService.create(newUser);

    return new ResponseEntity<>(user, null, HttpStatus.CREATED);
  }
}
