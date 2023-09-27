package api.pictureboxd.hacktoberfest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class HomeController {

  // @GetMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces
  // = MediaType.APPLICATION_JSON_VALUE)
  @GetMapping(path = "/")
  public ResponseEntity<String> index() {
    String message = "Hello, World!";

    return ResponseEntity.ok(message);
  }
}
