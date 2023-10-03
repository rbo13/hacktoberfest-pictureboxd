package app.pictureboxd.api.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pictureboxd.api.dto.AuthenticationResponseDto;
import app.pictureboxd.api.dto.SigninRequestDto;
import app.pictureboxd.api.dto.SignupRequestDto;
import app.pictureboxd.api.services.AuthenticationService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class AuthController {

  private final AuthenticationService authenticationService;

  @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponseDto> signup(@RequestBody SignupRequestDto request) {
    var createdUser = authenticationService.signup(request);
    return new ResponseEntity<>(createdUser, null, HttpStatus.CREATED);
  }

  @PostMapping(path = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthenticationResponseDto> signin(@RequestBody SigninRequestDto request) {
    return ResponseEntity.ok(authenticationService.signin(request));
  }
}
