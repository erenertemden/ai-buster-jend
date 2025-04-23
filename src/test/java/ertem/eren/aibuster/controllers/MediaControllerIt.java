package ertem.eren.aibuster.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ertem.eren.aibuster.domain.Media;
import ertem.eren.aibuster.domain.MediaEntity;
import ertem.eren.aibuster.repositories.MediaRepository;
import ertem.eren.aibuster.services.MediaService;
import ertem.eren.aibuster.services.TestData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static ertem.eren.aibuster.services.TestData.testMediaEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MediaControllerIt{
  
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private MediaService mediaService;
  @Autowired
  private MediaRepository mediaRepository;
  
  @Test
  public void testMedia() throws Exception {
    final Media media = TestData.testMedia();
    final ObjectMapper objectMapper = new ObjectMapper();
    final String mediaJson = objectMapper.writeValueAsString(media);
    
    mockMvc.perform(put("/medias/" + media.getId())
        .contentType(MediaType.APPLICATION_JSON)
        .content(mediaJson))
      .andExpect(status().isCreated())
      .andExpect(jsonPath("$.id").value(media.getId().toString()))
      .andExpect(jsonPath("$.mediaType").value(media.getMediaType().name()))
      .andExpect(jsonPath("$.mediaPath").value(media.getMediaPath()))
      .andExpect(jsonPath("$.mediaStatus").value(media.getMediaStatus().name()))
      .andExpect(jsonPath("$.createdAt").exists()); // Zaman eşleşmesini direkt .value ile test etmek zor olabilir
  }
  
  @Test
  public void testMediaNotFound404() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/medias/" + UUID.randomUUID()))
      .andExpect(MockMvcResultMatchers.status().isNotFound());
  }
  
  @Test
  public void testMediaRetrieveMediaWhenExists() throws Exception {
    final Media media = TestData.testMedia();
    
    mediaService.save(media);
    
    mockMvc.perform(MockMvcRequestBuilders.get("/medias/" + media.getId()))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.id").value(media.getId().toString()))
      .andExpect(jsonPath("$.mediaType").value(media.getMediaType().name()))
      .andExpect(jsonPath("$.mediaPath").value(media.getMediaPath()))
      .andExpect(jsonPath("$.mediaStatus").value(media.getMediaStatus().name()))
      .andExpect(jsonPath("$.createdAt").exists());
  }
  
  @Test
  public void testListMediasReturnsEmptyListWhenNoMedia() throws Exception {
    when(mediaRepository.findAll()).thenReturn(new ArrayList<MediaEntity>());
    final List<Media> result = underTest.listMedia();
    assertEquals(0, result.size());
    
  }
  
  @Test
  public void testListMediasReturnsMediaList() throws Exception {
    final MediaEntity mediaEntity = testMediaEntity();
    when(mediaRepository.findAll().thenReturn(List.of(mediaEntity)));
    final List<Media> result = underTest.listMedias();
    assertEqual(1, result.size());
  }
  
  @Test
  public void testListMediasReturns200WhenNoMediaList() throws Exception {
   mockMvc.perform(MockMvcRequestBuilders.get("/medias"))
     .andExpect(MockMvcResultMatchers.status().isOk())
     .andExpect(MockMvcResultMatchers.content().string("[]"));
  }
  
  @Test
  public void testThatListMediasReturns404WhenMediaDoesNotExist() throws Exception {
    final Media media = TestData.testMedia();
    mediaService.save(media);
    
    mockMvc.perform(MockMvcRequestBuilders.get("/medias/" )
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(jsonPath("$.[0].id").value(media.getId().toString()))
      .andExpect(jsonPath("$.[0].mediaType").value(media.getMediaType().name()))
      .andExpect(jsonPath("$.[0].mediaPath").value(media.getMediaPath()))
      .andExpect(jsonPath("$.[0].mediaStatus").value(media.getMediaStatus().name())));
    
  }
}
