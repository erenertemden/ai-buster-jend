package ertem.eren.aibuster.services.impl;

import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.domain.MediaStatus;
import ertem.eren.aibuster.domain.MediaType;
import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
public class MediaServiceImplTest {
  
  @Mock
  private MediaRepository mediaRepository;
  
  @InjectMocks
  private MediaServiceImpl underTest;
  
  @Test
  public void testThatMediaIsSaved() {
    UUID id = UUID.randomUUID();
    LocalDateTime createdAt = LocalDateTime.now();
    
    Media media = Media.builder()
      .id(id)
      .mediaType(MediaType.PHOTO)
      .mediaPath("/test/t")
      .mediaStatus(MediaStatus.ORIGINAL)
      .createdAt(createdAt)
      .build();
    
    MediaEntity mediaEntity = MediaEntity.builder()
      .id(id)
      .mediaType(MediaType.PHOTO)
      .mediaPath("/test/t")
      .mediaStatus(MediaStatus.ORIGINAL)
      .createdAt(createdAt)
      .build();
    
    when(mediaRepository.save(eq(mediaEntity))).thenReturn(mediaEntity);
    
    Media result = underTest.create(media);
    
    assertEquals(media.getId(), result.getId());
    assertEquals(media.getMediaPath(), result.getMediaPath());
    assertEquals(media.getMediaType(), result.getMediaType());
    assertEquals(media.getMediaStatus(), result.getMediaStatus());
  }
}
