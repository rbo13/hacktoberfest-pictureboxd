package app.pictureboxd.api.domain;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieActorId implements Serializable {

  private UUID actorId;
  private UUID movieId;
}
