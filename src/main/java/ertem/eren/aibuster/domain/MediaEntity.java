package ertem.eren.aibuster.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "medias")
public class MediaEntity {
  @Id
  private UUID id;
  private MediaType mediaType;
  private String mediaPath;
  private MediaStatus mediaStatus;
  private LocalDateTime createdAt;
}
