package ertem.eren.aibuster.controllers;

import ertem.eren.aibuster.domain.entities.MediaFormat;
import ertem.eren.aibuster.services.MediaUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

// controller/MediaController.java
@RestController
@RequestMapping("/api/v1/media")
@RequiredArgsConstructor
public class MediaController {
  
  private final MediaUploadService mediaUploadService;
  
  @PostMapping("/upload")
  public ResponseEntity<Boolean> upload(@RequestParam("file") MultipartFile file,
                                        @RequestParam("mediaFormat") MediaFormat mediaFormat) {
    try {
      boolean result = mediaUploadService.storeFileAndValidate(file, mediaFormat);
      return ResponseEntity.ok(result);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
    }
  }
  
  @DeleteMapping("/cleanup")
  public ResponseEntity<Void> cleanupUnsupported() {
    mediaUploadService.cleanupUnsupportedFormats();
    return ResponseEntity.noContent().build();
  }
}