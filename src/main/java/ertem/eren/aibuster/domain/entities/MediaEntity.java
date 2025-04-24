package ertem.eren.aibuster.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// entity/MediaEntity.java
@Entity
@Table(name = "medias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MediaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  
  private String mediaPath;
  
  @Enumerated(EnumType.STRING)
  private MediaFormat mediaFormat;
  
  @Enumerated(EnumType.STRING)
  private MediaStatus mediaStatus;
  
  private LocalDateTime createdAt;
}