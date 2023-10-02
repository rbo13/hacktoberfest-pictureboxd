package api.pictureboxd.hacktoberfest.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PictureboxdAPIException extends RuntimeException {

  private HttpStatus httpStatus;
  private String message;
}
