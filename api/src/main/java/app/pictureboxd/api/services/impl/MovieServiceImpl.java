package app.pictureboxd.api.services.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.Actor;
import app.pictureboxd.api.domain.Movie;
import app.pictureboxd.api.repository.ActorRepository;
import app.pictureboxd.api.repository.MovieRepository;
import app.pictureboxd.api.services.MovieService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class MovieServiceImpl implements MovieService {

  private final MovieRepository movieRepository;
  private final ActorRepository actorRepository;

  @Override
  public Movie save(Movie movie) {
    return movieRepository.save(movie);
  }

  @Override
  public Movie createMovieWithActors(Movie movie) {
    Set<Actor> actors = new HashSet<>();

    for (Actor actor : movie.getCasts()) {
      Actor foundActor = actorRepository.findByName(actor.getName()).orElseGet(() -> {
        Actor newActor = Actor.builder().name(actor.getName()).build();

        return actorRepository.save(newActor);
      });

      actors.add(foundActor);
    }

    Movie newMovie = Movie.builder()
        .title(movie.getTitle())
        .plot(movie.getPlot())
        .releaseDate(movie.getReleaseDate())
        .posterPath(movie.getPosterPath())
        .rating(movie.getRating())
        .casts(actors)
        .build();

    return movieRepository.save(newMovie);
  }
}
