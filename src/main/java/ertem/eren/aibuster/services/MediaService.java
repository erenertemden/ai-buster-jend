package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.Media;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaService {

  boolean isMediaAvailable(Media media);
  
  Media save(Media media);
  
  Optional<Media> findById(UUID id);
  
  List<Media> listMedias();
  
  void deleteMediaById(UUID id);
}
