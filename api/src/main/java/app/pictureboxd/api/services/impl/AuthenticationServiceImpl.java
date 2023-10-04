package app.pictureboxd.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.Role;
import app.pictureboxd.api.domain.User;
import app.pictureboxd.api.dto.AuthenticationResponseDto;
import app.pictureboxd.api.dto.SigninRequestDto;
import app.pictureboxd.api.dto.SignupRequestDto;
import app.pictureboxd.api.repository.RoleRepository;
import app.pictureboxd.api.repository.UserRepository;
import app.pictureboxd.api.services.AuthenticationService;
import app.pictureboxd.api.services.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthenticationServiceImpl implements AuthenticationService {

  private final UserRepository userRepository;
  private final RoleRepository roleRepository;

  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;

  @Override
  public AuthenticationResponseDto signup(SignupRequestDto request) {
    String defaultRole = "USER";
    if (request.getRole() != null) {
      defaultRole = request.getRole();
    }

    Role role = roleRepository.findByTitle(defaultRole).orElseThrow();
    String hashedPassword = passwordEncoder.encode(request.getPassword());

    User user = User.builder()
        .emailAddress(request.getEmailAddress())
        .password(hashedPassword)
        .role(role)
        .build();

    var savedUser = userRepository.save(user);

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            savedUser.getEmailAddress(),
            request.getPassword()));

    var jwtToken = jwtService.generateToken(authentication);
    var refreshToken = jwtService.generateRefreshToken(authentication);

    return AuthenticationResponseDto.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  @Override
  public AuthenticationResponseDto signin(SigninRequestDto request) {
    User user = userRepository.findByEmailAddress(request.getEmailAddress()).orElseThrow();
    if (user.equals(null)) {
      return null;
    }

    Authentication currentAuthentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmailAddress(), request.getPassword()));

    var jwtToken = jwtService.generateToken(currentAuthentication);
    var refreshToken = jwtService.generateRefreshToken(currentAuthentication);

    return AuthenticationResponseDto.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .build();
  }

  // @Override
  // public void refreshToken(HttpServletRequest request, HttpServletResponse
  // response) throws IOException {
  // final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
  // final String refreshToken;

  // final String userEmail;
  // if (authHeader.equals(null) ||!authHeader.startsWith("Bearer ")) {
  // return;
  // }

  // refreshToken = authHeader.substring(7);
  // userEmail = jwtService.extractUsername(refreshToken);
  // if (userEmail != null) {
  // var user = this.userRepository.findByEmailAddress(userEmail)
  // .orElseThrow();

  // Authentication currentAuthentication = authenticationManager.authenticate(
  // new UsernamePasswordAuthenticationToken(user.getEmailAddress(),
  // user.getPassword()));

  // if (jwtService.isTokenValid(refreshToken, user)) {
  // var accessToken = jwtService.generateToken(currentAuthentication);

  // var authResponse = AuthenticationResponseDto.builder()
  // .accessToken(accessToken)
  // .refreshToken(refreshToken)
  // .build();

  // new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
  // }
  // }
  // }
}
