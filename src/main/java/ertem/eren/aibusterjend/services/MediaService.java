package ertem.eren.aibusterjend.services;

import org.springframework.stereotype.Service;
import ertem.eren.aibusterjend.dtos.MediaRequest;
import ertem.eren.aibusterjend.entities.MediaEntity;
import ertem.eren.aibusterjend.repositories.MediaRepository;

@Service
public class MediaService {
  
  private final MediaRepository repository;
  
  public MediaService(MediaRepository repository) {
    this.repository = repository;
  }
  
  
  //dto to entity
  public void saveData(MediaRequest request) {
    MediaEntity entity = new MediaEntity();
    entity.setPath(request.getPath());
    entity.setRate(request.getRate());
    entity.setFlag(request.isFlag());
    repository.save(entity); //send to the jpa
  }
  

  
  
}
