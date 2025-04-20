package ertem.eren.aibuster.domain.dto;

import ertem.eren.aibuster.domain.entities.MediaStatus;

import java.time.LocalDateTime;
import java.util.UUID;


//records are immutable no setters
public record MediaDto(UUID id,
                       String type,
                       MediaStatus status,
                       LocalDateTime createdAt) {
  
    
}
