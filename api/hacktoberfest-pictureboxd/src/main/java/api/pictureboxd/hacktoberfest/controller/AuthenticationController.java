package api.pictureboxd.hacktoberfest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import api.pictureboxd.hacktoberfest.dto.RegisterDto;
import api.pictureboxd.hacktoberfest.service.AuthService;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

  private final AuthService authService;

  @PostMapping(path = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> signup(@RequestBody RegisterDto request) {
    String response = authService.register(request);
    return new ResponseEntity<>(response, null, HttpStatus.CREATED);
  }
}
