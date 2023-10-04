package app.pictureboxd.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.Movie;
import app.pictureboxd.api.repository.MovieRepository;
import app.pictureboxd.api.services.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;

  @Override
  public Movie save(Movie movie) {
    return movieRepository.save(movie);
  }
}
