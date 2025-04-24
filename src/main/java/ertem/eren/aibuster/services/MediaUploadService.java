package ertem.eren.aibuster.services;

import ertem.eren.aibuster.domain.entities.MediaEntity;
import ertem.eren.aibuster.domain.entities.MediaFormat;
import ertem.eren.aibuster.domain.entities.MediaStatus;
import ertem.eren.aibuster.mappers.MediaMapper;
import ertem.eren.aibuster.repositories.MediaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MediaUploadService {
  
  private final MediaRepository mediaRepository;
  private final MediaMapper mediaMapper;
  
  private final Path rootLocation = Paths.get("media-storage");
  
  public boolean storeFileAndValidate(MultipartFile file, MediaFormat mediaFormat) throws IOException {
    String filename = UUID.randomUUID() + "_" + StringUtils.cleanPath(file.getOriginalFilename());
    Path destinationFile = rootLocation.resolve(filename).normalize().toAbsolutePath();
    
    Files.createDirectories(rootLocation);
    Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
    
    MediaStatus status = notifyValidator(destinationFile.toString());
    
    MediaEntity entity = MediaEntity.builder()
      .mediaPath(destinationFile.toString())
      .mediaFormat(mediaFormat)
      .mediaStatus(status)
      .createdAt(LocalDateTime.now())
      .build();
    
    mediaRepository.save(entity);
    return status == MediaStatus.ORIGINAL;
  }
  
  public void cleanupUnsupportedFormats() {
    mediaRepository.deleteByMediaFormatNotIn(List.of(MediaFormat.IMAGE, MediaFormat.VIDEO));
  }
  
  private MediaStatus notifyValidator(String path) {
    RestTemplate restTemplate = new RestTemplate();
    String validatorUrl = "http://localhost:8082/validate-path";
    
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    Map<String, String> body = Map.of("path", path);
    
    HttpEntity<Map<String, String>> request = new HttpEntity<>(body, headers);
    
    try {
      ResponseEntity<String> response = restTemplate.postForEntity(validatorUrl, request, String.class);
      return MediaStatus.valueOf(response.getBody().toUpperCase());
    } catch (Exception e) {
      e.printStackTrace();
      return MediaStatus.UNDETERMINED;
    }
  }
}
