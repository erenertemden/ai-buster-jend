package ertem.eren.aibuster.domain.dto;

import ertem.eren.aibuster.domain.entities.MediaFormat;
import ertem.eren.aibuster.domain.entities.MediaStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// dto/MediaDto.java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediaDto {
  private Long id;
  private String mediaPath;
  private MediaFormat mediaFormat;
  private MediaStatus mediaStatus;
  private LocalDateTime createdAt;
}