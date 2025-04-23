package ertem.eren.aibuster.domain.entities;

import jakarta.persistence.*;
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
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", nullable = false, updatable = false)
  private UUID id;
  
  @Column(name = "media_type", nullable = false, updatable = false)
  private MediaType mediaType;
  
  @Column(name = "media_path", nullable = false)
  private String mediaPath;
  
  @Column(name = "media_status", nullable = false)
  private MediaStatus mediaStatus;
  
  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;
}
