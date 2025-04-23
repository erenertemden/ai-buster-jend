package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MediaServiceImpl implements MediaService {
  
  private final MediaRepository mediaRepository;
  
  @Autowired
  public MediaServiceImpl(final MediaRepository mediaRepository) {
    this.mediaRepository = mediaRepository;
  }
  
  @Override
  public Media create(final Media media) {
    final MediaEntity mediaEntity = mediaToMediaEntity(media);
    final MediaEntity savedMediaEntity = mediaRepository.save(mediaEntity);
    return mediaEntityToMedia(savedMediaEntity);
  }
  
  private MediaEntity mediaToMediaEntity (Media media) {
   return MediaEntity.builder()
      .id(media.getId())
        .mediaType(media.getMediaType())
      .mediaStatus(media.getMediaStatus())
      
      .mediaPath(media.getMediaPath())
      .createdAt(media.getCreatedAt())
     .build();
  }
  
  private Media mediaEntityToMedia (MediaEntity mediaEntity) {
    return Media.builder()
      .id(mediaEntity.getId())
      .mediaType(mediaEntity.getMediaType())
      .mediaStatus(mediaEntity.getMediaStatus())
      
      .mediaPath(mediaEntity.getMediaPath())
      .createdAt(mediaEntity.getCreatedAt())
      .build();
  }
  
}
