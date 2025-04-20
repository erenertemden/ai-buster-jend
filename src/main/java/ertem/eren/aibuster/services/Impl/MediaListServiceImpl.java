package ertem.eren.aibuster.services.Impl;

import ertem.eren.aibuster.domain.entities.Media;
import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaListService;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.MediaList;

import java.util.List;
@Service

public class MediaListServiceImpl implements MediaListService {
  
  private final MediaRepository mediaListRepository;
  
  public MediaListServiceImpl(MediaRepository mediaListRepository) {
    this.mediaListRepository = mediaListRepository;
  }
  
  @Override
  public List<Media> listMediaList() {
    return mediaListRepository.findAll();
  }
}
