package ertem.eren.aibuster.controllers;

import ertem.eren.aibuster.domain.Media;
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
  public ResponseEntity<Media> createUpdateMedia(
    @PathVariable final UUID id,
    @RequestBody final Media media) {
    
    media.setId(id);
    final boolean isMediaExist = mediaService.isMediaAvailable(media);
    final Media savedMedia = mediaService.save(media);
    
    if (isMediaExist) {
      return new ResponseEntity<Media>(savedMedia, HttpStatus.OK);
    }
    else {
      return new ResponseEntity<Media>(savedMedia, HttpStatus.CONFLICT);
    }
    
  }
  
  @GetMapping(path = "/media/{id}")
  public ResponseEntity<Media> retrieveMedia(@PathVariable final UUID id) {
    final Optional<Media> foundMedia = mediaService.findById(id);
    
    return foundMedia.map(media -> new ResponseEntity<Media>(media,
      HttpStatus.OK))
      .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }
  
  @GetMapping(path = "/medias")
  public ResponseEntity<List<Media>> retrieveAllMedia() {
    return new ResponseEntity<List<Media>>(mediaService.listMedias(), HttpStatus.OK);
  }
  
  @DeleteMapping(path = "/medias/ {id}")
  public ResponseEntity<Media> deleteMedia(@PathVariable final UUID id) {
    mediaService.deleteMediaById(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
  
}
