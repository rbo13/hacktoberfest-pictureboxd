package app.pictureboxd.api.domain;

import java.io.Serializable;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_movie_actors")
@IdClass(MovieActorId.class)
public class MovieActor implements Serializable {

  @Id
  @Column(name = "movie_id")
  private UUID movieId;

  @Id
  @Column(name = "actor_id")
  private UUID actorId;

  @ManyToOne
  @JoinColumn(name = "movie_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "actor_id", referencedColumnName = "id", insertable = false, updatable = false)
  private Actor actor;
}
