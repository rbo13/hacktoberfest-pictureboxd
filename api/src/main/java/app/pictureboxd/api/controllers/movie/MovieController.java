package app.pictureboxd.api.controllers.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.pictureboxd.api.domain.Movie;
import app.pictureboxd.api.dto.MovieDto;
import app.pictureboxd.api.mappers.Mapper;
import app.pictureboxd.api.services.MovieService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/movies")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MovieController {

  private final MovieService movieService;
  private final Mapper<Movie, MovieDto> movieMapper;

  @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<MovieDto> create(@RequestBody final MovieDto request) {
    Movie movie = movieMapper.mapFrom(request);
    var createdMovie = movieService.save(movie);

    return new ResponseEntity<>(movieMapper.mapTo(createdMovie), HttpStatus.CREATED);
  }
}
