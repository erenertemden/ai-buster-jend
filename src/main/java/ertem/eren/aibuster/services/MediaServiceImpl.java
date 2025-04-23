package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.domain.entities.MediaEntity;
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
  public boolean isMediaAvailable(MediaDto mediaDto) {
    return mediaRepository.existsById(mediaDto.getId());
  }
  
  @Override
  public MediaDto save(final MediaDto mediaDto) {
    final MediaEntity mediaEntity = mediaToMediaEntity(mediaDto);
    final MediaEntity savedMediaEntity = mediaRepository.save(mediaEntity);
    return mediaEntityToMedia(savedMediaEntity);
  }
  
  @Override
  public Optional<MediaDto> findById(UUID id) {
    final Optional<MediaEntity> foundMedia = mediaRepository.findById(id);
    return foundMedia.map(media -> mediaEntityToMedia(media));
  }
  
  @Override
  public List<MediaDto> listMedias() {
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
  
  private MediaEntity mediaToMediaEntity (MediaDto mediaDto) {
   return MediaEntity.builder()
      .id(mediaDto.getId())
        .mediaType(mediaDto.getMediaType())
      .mediaStatus(mediaDto.getMediaStatus())
      
      .mediaPath(mediaDto.getMediaPath())
      .createdAt(mediaDto.getCreatedAt())
     .build();
  }
  
  private MediaDto mediaEntityToMedia (MediaEntity mediaEntity) {
    return MediaDto.builder()
      .id(mediaEntity.getId())
      .mediaType(mediaEntity.getMediaType())
      .mediaStatus(mediaEntity.getMediaStatus())
      
      .mediaPath(mediaEntity.getMediaPath())
      .createdAt(mediaEntity.getCreatedAt())
      .build();
  }
  
}
