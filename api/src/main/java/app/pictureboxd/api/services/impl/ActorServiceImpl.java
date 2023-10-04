package app.pictureboxd.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pictureboxd.api.domain.Actor;
import app.pictureboxd.api.repository.ActorRepository;
import app.pictureboxd.api.services.ActorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
@Transactional
public class ActorServiceImpl implements ActorService {

  private final ActorRepository actorRepository;

  @Override
  public Actor save(Actor actor) {
    return actorRepository.save(actor);
  }
}
