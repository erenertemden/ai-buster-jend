package ertem.eren.aibuster.services.impl;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.domain.entities.MediaEntity;
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
public class MediaDtoServiceImplTest {
  
  @Mock
  private MediaRepository mediaRepository;
  
  @InjectMocks
  private MediaServiceImpl underTest;
  
  @Test
  public void testThatMediaIsSaved() {
    final MediaDto mediaDto = testMedia();
    
    final MediaEntity mediaEntity = testMediaEntity();
    
    when(mediaRepository.save(eq(mediaEntity))).thenReturn(mediaEntity);
    
    final MediaDto result = underTest.save(mediaDto);
    assertEquals(mediaDto, result);
  }
  
  @Test
  public void testThatFindByIdReturnsMediaWhenNoMediaFound() {
    final UUID id = UUID.randomUUID();
    when(mediaRepository.findById(eq(id))).thenReturn(Optional.empty());
    
    final Optional<MediaDto> result = underTest.findById(id);
    assertEquals(Optional.empty(), result);
  }
  
  @Test
  public void testThatFindByIdReturnsMediaWhenMediaFound() {
    final UUID id = UUID.randomUUID();
    final MediaDto mediaDto = testMedia();
    final MediaEntity mediaEntity = testMediaEntity();
    
    when(mediaRepository.findById(eq(mediaDto.getId()))).thenReturn(Optional.of(mediaEntity));
    
    final Optional<MediaDto> result = underTest.findById(mediaDto.getId());
    assertEquals(Optional.of(mediaDto), result);
  }
}
