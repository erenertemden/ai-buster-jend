package ertem.eren.aibuster.services.impl;

import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;
import java.util.UUID;

import static ertem.eren.aibuster.services.TestData.testMedia;
import static ertem.eren.aibuster.services.TestData.testMediaEntity;
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
    final Media media = testMedia();
    
    final MediaEntity mediaEntity = testMediaEntity();
    
    when(mediaRepository.save(eq(mediaEntity))).thenReturn(mediaEntity);
    
    final Media result = underTest.save(media);
    assertEquals(media, result);
  }
  
  @Test
  public void testThatFindByIdReturnsMediaWhenNoMediaFound() {
    final UUID id = UUID.randomUUID();
    when(mediaRepository.findById(eq(id))).thenReturn(Optional.empty());
    
    final Optional<Media> result = underTest.findById(id);
    assertEquals(Optional.empty(), result);
  }
  
  @Test
  public void testThatFindByIdReturnsMediaWhenMediaFound() {
    final UUID id = UUID.randomUUID();
    final Media media = testMedia();
    final MediaEntity mediaEntity = testMediaEntity();
    
    when(mediaRepository.findById(eq(media.getId()))).thenReturn(Optional.of(mediaEntity));
    
    final Optional<Media> result = underTest.findById(media.getId());
    assertEquals(Optional.of(media), result);
  }
}
