package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.dto.MediaDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MediaService {

  boolean isMediaAvailable(MediaDto mediaDto);
  
  MediaDto save(MediaDto mediaDto);
  
  Optional<MediaDto> findById(UUID id);
  
  List<MediaDto> listMedias();
  
  void deleteMediaById(UUID id);
}
