package ertem.eren.aibuster.controllers;

import ertem.eren.aibuster.domain.dto.MediaDto;
import ertem.eren.aibuster.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class MediaController {
  
  private final MediaService mediaService;
  
  @Autowired
  public MediaController(final MediaService mediaService) {
    this.mediaService = mediaService;
    
  }
  
  @PutMapping(path = "/medias/{id}")
  public ResponseEntity<MediaDto> createUpdateMedia(
    @PathVariable final UUID id,
    @RequestBody final MediaDto mediaDto) {
    
    mediaDto.setId(id);
    final boolean isMediaExist = mediaService.isMediaAvailable(mediaDto);
    final MediaDto savedMediaDto = mediaService.save(mediaDto);
    
    if (isMediaExist) {
      return new ResponseEntity<MediaDto>(savedMediaDto, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<MediaDto>(savedMediaDto, HttpStatus.CONFLICT);
    }
    
  }
  
  @GetMapping(path = "/media/{id}")
  public ResponseEntity<MediaDto> retrieveMedia(@PathVariable final UUID id) {
    final Optional<MediaDto> foundMedia = mediaService.findById(id);
    
    return foundMedia.map(media -> new ResponseEntity<MediaDto>(media,
      HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  
  @GetMapping(path = "/medias")
  public ResponseEntity<List<MediaDto>> retrieveAllMedia() {
    return new ResponseEntity<List<MediaDto>>(mediaService.listMedias(), HttpStatus.OK);
  }
  
  @DeleteMapping(path = "/medias/ {id}")
  public ResponseEntity<MediaDto> deleteMedia(@PathVariable final UUID id) {
    mediaService.deleteMediaById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}
