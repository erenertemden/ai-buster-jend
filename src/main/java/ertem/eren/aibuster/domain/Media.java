package ertem.eren.aibuster.domain;

//dto
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Media {
  
  private UUID id;
  private MediaType mediaType;
  private String mediaPath;
  private MediaStatus mediaStatus;
  private LocalDateTime createdAt;
}//using in service layer
