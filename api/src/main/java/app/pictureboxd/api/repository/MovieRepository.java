package app.pictureboxd.api.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.pictureboxd.api.domain.Movie;

@Repository
public interface MovieRepository extends JpaRepository<Movie, UUID> {

}
