package app.pictureboxd.api.services;

import app.pictureboxd.api.domain.Movie;

public interface MovieService {

  Movie save(Movie movie);

  Movie createMovieWithActors(Movie movie);
}
