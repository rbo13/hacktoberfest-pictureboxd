package app.pictureboxd.api.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

  private String title;
  private String plot;

  @JsonProperty("release_year")
  private String releaseYear;

  @JsonProperty("poster_path")
  private String posterPath;

  private Float rating;

  private List<String> casts;
}
