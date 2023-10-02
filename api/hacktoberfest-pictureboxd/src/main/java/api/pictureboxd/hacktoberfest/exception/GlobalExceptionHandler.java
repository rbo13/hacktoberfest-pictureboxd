package api.pictureboxd.hacktoberfest.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(PictureboxdAPIException.class)
  public ResponseEntity<ErrorDetails> handlePictureboxdAPIException(PictureboxdAPIException exception,
      WebRequest webRequest) {

    ErrorDetails errorDetails = ErrorDetails.builder()
        .timeStamp(LocalDateTime.now())
        .message(exception.getMessage())
        .details(webRequest.getDescription(false))
        .build();

    return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
  }
}
