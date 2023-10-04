package app.pictureboxd.api.mappers.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.pictureboxd.api.domain.Movie;
import app.pictureboxd.api.dto.MovieDto;
import app.pictureboxd.api.mappers.Mapper;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MovieMapperImpl implements Mapper<Movie, MovieDto> {

  private final ModelMapper modelMapper;

  @Override
  public MovieDto mapTo(Movie movie) {
    return modelMapper.map(movie, MovieDto.class);
  }

  @Override
  public Movie mapFrom(MovieDto dto) {
    return modelMapper.map(dto, Movie.class);
  }
}
