package ertem.eren.aibuster.domain.entities;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table( name = "medias")

public class Media {
  @Id
  @GeneratedValue( strategy = GenerationType.UUID)
  @Column( name = "id", nullable = false, updatable = false)
  private UUID id;
  
  @Column( name = "type", nullable = false, updatable = false)
  private String type;
  
  @Column( name = "description")
  private String description;
  
  @Column( name = "status", nullable = false)
  private MediaStatus status;
  
  @Column( name = "created", nullable = false, updatable = false)
  private LocalDateTime createdAt;
  
  @Column( name = "updated", nullable = false, updatable = false)
  private LocalDateTime updatedAt;
}
