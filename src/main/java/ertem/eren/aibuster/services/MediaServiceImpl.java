package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.repositories.MediaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j //loglama icin
public class MediaServiceImpl implements MediaService {
  
  private final MediaRepository mediaRepository;
  
  @Autowired
  public MediaServiceImpl(final MediaRepository mediaRepository) {
    this.mediaRepository = mediaRepository;
  }
  
  @Override
  public boolean isMediaAvailable(Media media) {
    return mediaRepository.existsById(media.getId());
  }
  
  @Override
  public Media save(final Media media) {
    final MediaEntity mediaEntity = mediaToMediaEntity(media);
    final MediaEntity savedMediaEntity = mediaRepository.save(mediaEntity);
    return mediaEntityToMedia(savedMediaEntity);
  }
  
  @Override
  public Optional<Media> findById(UUID id) {
    final Optional<MediaEntity> foundMedia = mediaRepository.findById(id);
    return foundMedia.map(media -> mediaEntityToMedia(media));
  }
  
  @Override
  public List<Media> listMedias() {
    final List<MediaEntity> foundMedias = mediaRepository.findAll();
    return foundMedias.stream().map(media -> mediaEntityToMedia(media)).collect(Collectors.toList());
  }
  
  @Override
  public void deleteMediaById(UUID id) {
   try {
     mediaRepository.deleteById(id);
   } catch (final EmptyResultDataAccessException e) {
     log.debug("attempt to delete for non exist media");
   }
    
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
